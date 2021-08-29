package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GetImageTests extends BaseTest {
    String imageHash;

    public String createImage(String nameOfIm) {
        imageHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/" + nameOfIm))
                .when()
                .post("/image")
                .jsonPath()
                .get("data.id");
        return imageHash;
    }

    @Test
    void getExistingImageJPEG() {
       given()
                .headers("Authorization", token)
                .when()
                .get("image/{imageHash}", imageHash = createImage("im.jpg"))
                .then()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    void getExistingImagePNG() {
        given()
                .headers("Authorization", token)
                .when()
                .get("image/{imageHash}", imageHash = createImage("im1.png"))
                .then()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    void getExistingImageGif() {
        given()
                .headers("Authorization", token)
                .when()
                .get("image/{imageHash}", imageHash = createImage("hump-day-icegif-6.gif"))
                .then()
                .statusCode(200)
                .contentType("application/json");
    }
}
