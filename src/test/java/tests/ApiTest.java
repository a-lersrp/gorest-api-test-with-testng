package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import client.UserClient;
import model.UserModel;
import assertions.UserAssertion;

public class ApiTest extends BaseTest {
  UserClient userClient = new UserClient();

  @Test(description = "Get all users success & verify Active user count")
  public void testGetUsers() {
    Response response = userClient.getUsers("active");
    Assert.assertEquals(response.statusCode(), 200);

    // verify all elements have specific keys
    List<Map<String, Object>> users = response.jsonPath().getList("");
    for (Map<String, Object> user : users) {
      Assert.assertTrue(user.containsKey("id"));
      Assert.assertTrue(user.containsKey("name"));
      Assert.assertTrue(user.containsKey("email"));
    }

    // verify how many active user
    System.out.println("All Active user count:" + response.jsonPath().getList("$").size());
  }

  @Test(description = "Create new user")
  public void testCreateUser() {
    UserModel request = new UserModel();
    request.name = "John Doe";
    request.email = "test" + System.currentTimeMillis() + "@test.com";
    request.gender = "male";
    request.status = "inactive";

    Response response = userClient.createUser(request);
    Assert.assertEquals(response.statusCode(), 201);
    UserAssertion.assertUserResponse(response, request); // verify response match payload value

    int userId = response.jsonPath().get("id");

    Response getRes = userClient.getUsers(userId);
    Assert.assertEquals(getRes.statusCode(), 200);
    UserAssertion.assertUserResponse(response, request, userId); // verify response match payload of created user
  }

  @Test(description = "Update user name & email success")
  public void testPutUser() {
    UserModel request = new UserModel();
    request.name = "John Doe";
    request.email = "test" + System.currentTimeMillis() + "@test.com";
    request.gender = "male";
    request.status = "active";

    Response response = userClient.createUser(request);
    Assert.assertEquals(response.statusCode(), 201);

    int userId = response.jsonPath().getInt("id");

    // set user's name & email for updating
    request.name = "new John Doe";
    request.email = "test" + System.currentTimeMillis() + "@test.com";

    Response updateResponse = userClient.updateUser(userId, request);
    Assert.assertEquals(updateResponse.statusCode(), 200);
    UserAssertion.assertUserResponse(updateResponse, request, userId); // verify response match payload of updated user

    Response getRes = userClient.getUsers("active");
    Assert.assertEquals(getRes.statusCode(), 200);
    UserAssertion.assertUserResponse(getRes, request, userId, "[0]"); // verify response match payload of updated user
  }

  @Test(description = "Delete user success")
  public void testDeleteUser() {
    UserModel request = new UserModel();
    request.name = "John Doe";
    request.email = "test" + System.currentTimeMillis() + "@test.com";
    request.gender = "male";
    request.status = "active";

    Response response = userClient.createUser(request);
    Assert.assertEquals(response.statusCode(), 201);

    int userId = response.jsonPath().getInt("id");

    Response delResponse = userClient.deleteUser(userId);
    Assert.assertEquals(delResponse.statusCode(), 204);

    // delete the same userId again
    Response notFoundResp = userClient.deleteUser(userId);
    Assert.assertEquals(notFoundResp.statusCode(), 404);
  }
}