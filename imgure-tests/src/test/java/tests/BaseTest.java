package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    static Properties properties;
    static String host;
    static String username;
    static String token;
    static String actoken;

    @BeforeAll
    static void beforeAll() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        host = properties.getProperty("host", "https://api.imgur.com/3/");
        username = properties.getProperty("username");
        token = properties.getProperty("token");
        actoken = properties.getProperty("actoken");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host;
    }
}
