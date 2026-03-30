package client;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {
  private final String accessToken = "YOUR_ACCESS_TOKEN";

  public Response getUsers() {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .when()
        .get("/users");
  }

  public Response getUsers(String status) {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .queryParams("status", status)
        .when()
        .get("/users");
  }

  public Response getUsers(int userId) {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .when()
        .get("/users/" + userId);
  }

  public Response createUser(Object request) {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .body(request)
        .when()
        .post("/users");
  }

  public Response updateUser(int userId, Object request) {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .body(request)
        .when()
        .put("/users/" + userId);
  }

  public Response deleteUser(int userId) {
    return given()
        .headers("Authorization", "Bearer " + accessToken)
        .headers("Content-Type", "application/json")
        .when()
        .delete("/users/" + userId);
  }
}
