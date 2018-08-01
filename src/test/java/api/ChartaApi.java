package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ChartaApi {

    public static Response postCharta(File jsonFile) {
        return given()
                .contentType(ContentType.JSON)
                .body(jsonFile).accept(ContentType.JSON)
                .when().post("http://localhost:8080/charta");
    }
}
