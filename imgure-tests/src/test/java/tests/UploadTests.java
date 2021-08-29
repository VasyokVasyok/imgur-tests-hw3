package tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadTests extends BaseTest {
    String imageDeleteHash;

    @Test
    void uploadImageFileJPGTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/im.jpg"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.width", CoreMatchers.equalTo(900))
                .body("data.height", CoreMatchers.equalTo(520))
                .body("data.type", CoreMatchers.equalTo("image/jpeg"))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }

    @Test
    void uploadImageFilePNGTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/im1.png"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.width", CoreMatchers.equalTo(512))
                .body("data.height", CoreMatchers.equalTo(512))
                .body("data.type", CoreMatchers.equalTo("image/png"))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }

    @Test
    void uploadImageFileGifTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/hump-day-icegif-6.gif"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.width", CoreMatchers.equalTo(396))
                .body("data.height", CoreMatchers.equalTo(504))
                .body("data.type", CoreMatchers.equalTo("image/gif"))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }

    @Test
    void uploadImageFileTiffTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/im2.tiff"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.width", CoreMatchers.equalTo(800))
                .body("data.height", CoreMatchers.equalTo(800))
                .body("data.type", CoreMatchers.equalTo("image/png"))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }

    @Test
    void uploadImageFileSVGTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/im3.svg"))
                .when()
                .post("/image")
                .then()
                .statusCode(400)
                .body("success", CoreMatchers.is(false));
    }

   /* @Test
    void uploadImageFileVideoMP4Test() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Star-6962.mp4"))
                .when()
                .post("/image")
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.equalTo("video/mp4"));
    }

    @Test
    void uploadImageFileVideoMEGTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Star-6962.mpeg"))
                .when()
                .post("/image")
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.equalTo("video/mpeg"));
    }*/

    @AfterEach
    void tearDown() {
        if (imageDeleteHash != null) {
            given()
                    .header("Authorization", token)
                    .when()
                    .delete("image/{imageDeleteHash}", imageDeleteHash)
                    .then()
                    .statusCode(200);
        } else {
            System.out.println("There is no image to delete!");
        }
    }
}
