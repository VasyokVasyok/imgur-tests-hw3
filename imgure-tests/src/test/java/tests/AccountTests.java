package tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTest{
    @Test
    void getAccountPositiveTest() {
        given()
                .header("Authorization", token)
                .log()
                .all()
                .when()
                .get("account/{username}" + username)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.url", CoreMatchers.equalTo(username));
    }
}
