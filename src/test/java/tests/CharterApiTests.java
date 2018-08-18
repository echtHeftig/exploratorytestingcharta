package tests;

import api.CharterApi;
import charter.Application;
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
public class CharterApiTests {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        CharterApi.deleteAllCharters();
    }

    @Test
    public void getCharterListReturnsEmptyArrayIfNoCharterExists() {
        Response response = CharterApi.getCharterList();

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void whenPostOnCharterEndpoint_thenPayloadAlwaysReturnsObjectContainingAnId() {
        File file = getTestDataFile("post/PostReturnsPayloadWithId.json");
        Response response = CharterApi.postCharter(file);
        response.then().body("id", matchesPattern("[a-z0-9]+"));
    }

    private File getTestDataFile(String pathToFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(pathToFile))
                .getFile());
    }
}
