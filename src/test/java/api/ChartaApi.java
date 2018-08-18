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
                .log().all()
                .when().post("/charta")
                .prettyPeek();
    }

    public static Response getChartaList() {
        return given()
                .when().get("/charta");
    }

    public static Response deleteAllChartas() {
        return given()
                .when().delete("/charta");
    }
}
