package tests;

import api.CharterApi;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

@RunWith(SpringRunner.class)
public class PostChartersTests extends BaseApiTests {

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
    public void whenPostOnCharterEndpointOnlyWithCharterName_thenEverythingElseIsNull() {
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
    public void whenCreateByPostSuccesful_thenReturnStatus201Created() {
        final Response response = CharterApi.postCharter("{ \"charterName\": \"postSoll201CreatedSein\" }");
        Assert.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    @Test
    public void whenCreateByPostSuccesful_thenReturnLocationHeader() {
        final Response response = CharterApi.postCharter(
                "{ \"charterName\": \"es soll ein location header geben\"}");
        Assert.assertNotNull(response.getHeader("Location"));
        MatcherAssert.assertThat(response.getHeader("Location"), matchesPattern("/charters/[a-z0-9]+"));
    }
}
