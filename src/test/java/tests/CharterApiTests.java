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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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

    @Test
    public void whenDeletingAllCharters_thenNoChartersExistAnymore() {
        File file = getTestDataFile("delete/PostCharterBeforeDeleteAllCharters.json");
        CharterApi.postCharter(file);
        final Response charterListBeforeDelete = CharterApi.getCharterList();
        charterListBeforeDelete.then().body("results.size()", equalTo(1));
        final Response deleteAllResponse = CharterApi.deleteAllCharters();
        deleteAllResponse.then().statusCode(200);
        final Response charterListAfterDelete = CharterApi.getCharterList();
        charterListAfterDelete.then().body("results.size()", equalTo(0));
    }

    private File getTestDataFile(String pathToFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(pathToFile))
                .getFile());
    }

    @Test
    public void whenPostOnCharterEndpointOnlyWithCharterName_thenEverythingElseIsNull(){
        File file = getTestDataFile("post/OnlyCharterNameInPayload.json");
        final Response response = CharterApi.postCharter(file);
        response.then()
                .body("charterName", is("Nur der CharterNamer ist definiert"))
                .body("areas", isEmptyOrNullString())
                .body("start", isEmptyOrNullString())
                .body("nameOfTester", isEmptyOrNullString())
                .body("taskBreakDown", isEmptyOrNullString())
                .body("duration", isEmptyOrNullString())
                .body("testDesignAndExecutionTimeInPercent", isEmptyOrNullString())
                .body("bugInvestigationAndReportingTimeInPercent", isEmptyOrNullString())
                .body("sessionSetupTimeInPercentage", isEmptyOrNullString())
                .body("charterVsOpportunityTimeInPercentage", isEmptyOrNullString())
                .body("dataFilesPaths", isEmptyOrNullString())
                .body("testNotes", isEmptyOrNullString())
                .body("opportunities", isEmptyOrNullString())
                .body("bugs", isEmptyOrNullString())
                .body("issues", isEmptyOrNullString());
    }

    @Test
    public void whenUpdatingCharterWithPut_thenUpdatedCharterIsPersisted() {
        File createWithPostFile = getTestDataFile("post/CreateCharterWithPostBeforePut.json");
        File updateWithPutFile = getTestDataFile("put/UpdateCharterWithPut.json");
        final Response postResponse = CharterApi.postCharter(createWithPostFile);
        final String id = postResponse.path("id");
        postResponse.then()
                .body("id", is(id))
                .body("charterName", is("My charter has a name and test notes vor dem Test"))
                .body("areas", isEmptyOrNullString())
                .body("start", isEmptyOrNullString())
                .body("nameOfTester", isEmptyOrNullString())
                .body("taskBreakDown", isEmptyOrNullString())
                .body("duration", isEmptyOrNullString())
                .body("testDesignAndExecutionTimeInPercent", isEmptyOrNullString())
                .body("bugInvestigationAndReportingTimeInPercent", isEmptyOrNullString())
                .body("sessionSetupTimeInPercentage", isEmptyOrNullString())
                .body("charterVsOpportunityTimeInPercentage", isEmptyOrNullString())
                .body("dataFilesPaths", isEmptyOrNullString())
                .body("testNotes", is("Hier sind Testnotizen vor dem Test"))
                .body("opportunities", isEmptyOrNullString())
                .body("bugs", isEmptyOrNullString())
                .body("issues", isEmptyOrNullString());

        final Response putResponse = CharterApi.putCharter(id, updateWithPutFile);
        putResponse.then()
                .body("id", is(id))
                .body("charterName", is("Nach dem Test gibt es einen neuen Testnamen und Bugs"))
                .body("areas", isEmptyOrNullString())
                .body("start", isEmptyOrNullString())
                .body("nameOfTester", isEmptyOrNullString())
                .body("taskBreakDown", isEmptyOrNullString())
                .body("duration", isEmptyOrNullString())
                .body("testDesignAndExecutionTimeInPercent", isEmptyOrNullString())
                .body("bugInvestigationAndReportingTimeInPercent", isEmptyOrNullString())
                .body("sessionSetupTimeInPercentage", isEmptyOrNullString())
                .body("charterVsOpportunityTimeInPercentage", isEmptyOrNullString())
                .body("dataFilesPaths", isEmptyOrNullString())
                .body("testNotes", is("Hier sind Testnotizen nach dem Test"))
                .body("opportunities", isEmptyOrNullString())
                .body("bugs", is("Hier wurde nun ein Bug eingetragen"))
                .body("issues", isEmptyOrNullString());
        }
}
