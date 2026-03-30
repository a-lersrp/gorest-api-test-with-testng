package assertions;

import io.restassured.response.Response;
import org.testng.Assert;

import model.UserModel;

public class UserAssertion {
  public static void assertUserResponse(Response response, UserModel payload) {
    Assert.assertEquals(response.jsonPath().getString("name"), payload.name);
    Assert.assertEquals(response.jsonPath().getString("email"), payload.email);
    Assert.assertEquals(response.jsonPath().getString("gender"), payload.gender);
    Assert.assertEquals(response.jsonPath().getString("status"), payload.status);
  }

  public static void assertUserResponse(Response response, UserModel payload, int userId) {
    Assert.assertEquals(response.jsonPath().getInt("id"), userId);
    Assert.assertEquals(response.jsonPath().getString("name"), payload.name);
    Assert.assertEquals(response.jsonPath().getString("email"), payload.email);
    Assert.assertEquals(response.jsonPath().getString("gender"), payload.gender);
    Assert.assertEquals(response.jsonPath().getString("status"), payload.status);
  }

  public static void assertUserResponse(Response response, UserModel payload, int userId, String customJsonPath) {
    String prefix = customJsonPath + ".";

    Assert.assertEquals(response.jsonPath().getInt(prefix + "id"), userId);
    Assert.assertEquals(response.jsonPath().getString(prefix + "name"), payload.name);
    Assert.assertEquals(response.jsonPath().getString(prefix + "email"), payload.email);
    Assert.assertEquals(response.jsonPath().getString(prefix + "gender"), payload.gender);
    Assert.assertEquals(response.jsonPath().getString(prefix + "status"), payload.status);
  }
}
