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

import static org.hamcrest.Matchers.is;
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

    @Test
    public void whenPostOnCharterEndpoint_thenPayloadReturnsCorrectValues() {
        File file = getTestDataFile("post/PostReturnsCorrectPayload.json");
        Response response = CharterApi.postCharter(file);
        response.then()
                .body("charterName", is("Charta Post contains correct values"))
                .body("areas", is("My area value is correct"))
                .body("start", is("2018-12-13T14:15:02.030+0000"))
                .body("nameOfTester", is("The POST tester"))
                .body("taskBreakDown", is("A Breakdown for testing http post"))
                .body("duration", is(14628))
                .body("testDesignAndExecutionTimeInPercent", is(214))
                .body("bugInvestigationAndReportingTimeInPercent", is(32))
                .body("sessionSetupTimeInPercentage", is(412))
                .body("charterVsOpportunityTimeInPercentage", is(251))
                .body("dataFilesPaths", is("My path to Data"))
                .body("testNotes", is("Hier ist eine neue Notiz"))
                .body("opportunities", is("Possibility to test further attributes"))
                .body("bugs", is("Keine Bugs gefunden"))
                .body("issues", is("Einige Issues mit Umbruch \n naechstes Issue"))
        ;
    }

    private File getTestDataFile(String pathToFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(pathToFile))
                .getFile());
    }
}
