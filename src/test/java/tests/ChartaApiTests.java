package tests;

import api.ChartaApi;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

public class ChartaApiTests {

    @Test
    void createChartaTestReturnsCorrectPayload() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("post/PostReturnsCorrectPayload.json"))
                .getFile());
        Response response = ChartaApi.postCharta(file);

        Approvals.verify(response.getBody().prettyPrint());
    }
}
