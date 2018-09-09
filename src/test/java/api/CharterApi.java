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

    public static Response postCharter(String jsonAsString) {
        return given().contentType(ContentType.JSON)
                .body(jsonAsString)
                .log().all()
                .accept(ContentType.JSON)
                .when().post("/charters")
                .prettyPeek();
    }

    public static Response getCharterList() {
        return given()
                .when().get("/charters").prettyPeek();
    }

    public static Response deleteAllCharters() {
        return given()
                .when().delete("/charters");
    }

    public static Response putCharter(String uniqueId, File updateWithPutFile) {
        return given()
                .contentType(ContentType.JSON)
                .body(updateWithPutFile)
                .accept(ContentType.JSON)
                .pathParam("id", uniqueId)
                .log().all()
                .when()
                .put("/charters/{id}")
                .prettyPeek();
    }

    public static Response getSingleCharter(String uniqueId) {
        return given()
                .pathParam("id", uniqueId)
                .when().get("/charters/{id}");
    }

    public static Response deleteSingleCharter(String id) {
        return given()
                .pathParam("id", id)
                .when().delete("/charters/{id}");
    }
}
