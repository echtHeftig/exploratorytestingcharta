package tests;

import api.ChartaApi;
import charta.Application;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Objects;

import static org.hamcrest.text.MatchesPattern.matchesPattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChartaApiTests {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        ChartaApi.deleteAllChartas();
    }

    @Test
    public void getChartaListReturnsEmptyArrayIfNoChartaExists() {
        Response response = ChartaApi.getChartaList();

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void whenPostOnChartaEndpoint_thenPayloadAlwaysReturnsObjectContainingAnId() {
        File file = getTestDataFile("post/PostReturnsPayloadWithId.json");
        Response response = ChartaApi.postCharta(file);
        response.then().body("id", matchesPattern("[a-z0-9]+"));
    }

    private File getTestDataFile(String pathToFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(pathToFile))
                .getFile());
    }
}
