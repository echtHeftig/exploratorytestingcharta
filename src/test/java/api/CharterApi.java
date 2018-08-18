package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CharterApi {

    public static Response postCharter(File jsonFile) {
        return given()
                .contentType(ContentType.JSON)
                .body(jsonFile).accept(ContentType.JSON)
                .log().all()
                .when().post("/charters")
                .prettyPeek();
    }

    public static Response getCharterList() {
        return given()
                .when().get("/charters");
    }

    public static Response deleteAllCharters() {
        return given()
                .when().delete("/charters");
    }
}
