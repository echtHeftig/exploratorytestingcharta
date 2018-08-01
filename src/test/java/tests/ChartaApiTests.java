package tests;

import api.ChartaApi;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.Test;
import java.io.File;
import java.util.Objects;

public class ChartaApiTests {

    @Test
    public void createChartaTestReturnsCorrectPayload() {
        File file = getTestDataFile("post/PostReturnsCorrectPayload.json");
        Response response = ChartaApi.postCharta(file);

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void createChartaTestReturnsCorrectPayloadSecondTest() {
        File file = getTestDataFile("post/PostReturnsCorrectPayloadSecondTest.json");
        Response response = ChartaApi.postCharta(file);

        Approvals.verify(response.getBody().prettyPrint());
    }

    private File getTestDataFile(String pathToFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(pathToFile))
                .getFile());
    }
}
