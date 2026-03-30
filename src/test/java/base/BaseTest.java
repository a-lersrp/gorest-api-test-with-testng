package base;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;

public class BaseTest {
  @BeforeClass
  public void setup() {
    RestAssured.baseURI = "https://gorest.co.in/public/v2";
  }
}
