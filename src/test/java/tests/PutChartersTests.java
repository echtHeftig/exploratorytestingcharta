package tests;

import api.CharterApi;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

@RunWith(SpringRunner.class)
public class PutChartersTests extends BaseApiTests {

    @Test
    public void whenUpdatingCharterWithPut_thenUpdatedCharterIsPersisted() {
        File createWithPostFile = getTestDataFile("post/CreateCharterWithPostBeforePut.json");
        File updateWithPutFile = getTestDataFile("put/UpdateCharterWithPut.json");
        final Response postResponse = CharterApi.postCharter(createWithPostFile);
        final String id = postResponse.path("id");
        postResponse.then()
                .body("id", is(id))
                .body("charterName", is("My charter has a name and test notes vor dem Test"))
                .body("areas", is("Das hier wird hoffentlich durch null ueberschrieben"))
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

    @Test
    public void whenUpdatingNonExistingCharterWithPut_thenNewCharterIsPersisted() {
        //Arrange
        final String nonExistingCharterId = "142071847190";
        final Response response = CharterApi.getSingleCharter(nonExistingCharterId);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode());

        //Act
        File createWithPutFile = getTestDataFile("put/CreateCharterWithPut.json");
        final Response putResponse = CharterApi.putCharter(nonExistingCharterId, createWithPutFile);

        //Assert
        putResponse.then()
                .body("id", not(nonExistingCharterId))
                .body("id", matchesPattern("[a-z0-9]+"))
                .body("charterName", is("Diese Charter existiert noch nicht. Ein Put ist Create or Update"))
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
                .body("testNotes", is("Da es nicht existiert, bedeutet Put hier Create"))
                .body("opportunities", is("Es ist die Chance, CreateOrUpdate per Put zu ueben"))
                .body("bugs", is("Hier wurde nun ein Bug eingetragen"))
                .body("issues", isEmptyOrNullString());
    }
}
