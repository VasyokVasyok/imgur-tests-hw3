package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DeleteImageTest extends BaseTest{
    String imageDeleteHash;

    public String createImage(String nameOfIm) {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/" + nameOfIm))
                .when()
                .post("/image")
                .jsonPath()
                .get("data.deletehash");
        return imageDeleteHash;
    }

    @Test
    void deleteExistingImageJPEGTest() {
        given()
                .header("Authorization", token)
                .when()
                .delete("image/{imageDeleteHash}",  imageDeleteHash = createImage("im.jpg"))
                .then()
                .statusCode(200);
    }

    @Test
    void deleteExistingImagePNGTest() {
        given()
                .header("Authorization", token)
                .when()
                .delete("image/{imageDeleteHash}",  imageDeleteHash = createImage("im1.png"))
                .then()
                .statusCode(200);
    }

    @Test
    void deleteExistingImageGifTest() {
        given()
                .header("Authorization", token)
                .when()
                .delete("image/{imageDeleteHash}",  imageDeleteHash = createImage("hump-day-icegif-6.gif"))
                .then()
                .statusCode(200);
    }

    @Test
    void deleteExistingImageTiffTest() {
        given()
                .header("Authorization", token)
                .when()
                .delete("image/{imageDeleteHash}",  imageDeleteHash = createImage("im2.tiff"))
                .then()
                .statusCode(200);
    }
}
