package tests;

import api.CharterApi;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class DeleteChartersTests extends BaseApiTests {

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

    @Test
    public void whenDeleteSingleCharter_thenReturnListWithoutDeletedCharter() {
        final int amountOfChartersToCreateBeforeTest = 5;
        createCharterMultipleTimes(amountOfChartersToCreateBeforeTest);
        final Response getCharterListResponse = CharterApi.getCharterList();
        final int charterCountBeforeDelete = getCharterListResponse.jsonPath().getList("$").size();
        Assert.assertThat(charterCountBeforeDelete, is(amountOfChartersToCreateBeforeTest));
        List<String> idList = getCharterListResponse.getBody().jsonPath().getList("id");
        CharterApi.deleteSingleCharter(idList.get(0));
        final int charterCountAfterDelete = CharterApi.getCharterList().jsonPath().getList("$").size();
        Assert.assertThat(charterCountAfterDelete, is(amountOfChartersToCreateBeforeTest - 1));
    }

    @Test
    public void whenDeleteSingleCharter_thenReturn204NoContent() {
        final int amountOfChartersToCreateBeforeTest = 7;
        createCharterMultipleTimes(amountOfChartersToCreateBeforeTest);
        final Response getCharterListResponse = CharterApi.getCharterList();
        final int charterCountBeforeDelete = getCharterListResponse.jsonPath().getList("$").size();
        Assert.assertThat(charterCountBeforeDelete, is(amountOfChartersToCreateBeforeTest));
        List<String> idList = getCharterListResponse.getBody().jsonPath().getList("id");
        final Response response = CharterApi.deleteSingleCharter(idList.get(0));
        Assert.assertThat(response.statusCode(), is(HttpStatus.NO_CONTENT.value()));
        final int charterCountAfterDelete = CharterApi.getCharterList().jsonPath().getList("$").size();
        Assert.assertThat(charterCountAfterDelete, is(amountOfChartersToCreateBeforeTest - 1));
    }

    @Test
    public void whenDeleteNonExistingCharterInEmptyDatabase_thenReturn404NotFound() {
        final Response getCharterListResponse = CharterApi.getCharterList();
        final int charterCountBeforeDelete = getCharterListResponse.jsonPath().getList("$").size();
        Assert.assertThat(charterCountBeforeDelete, is(0));
        final Response response = CharterApi.deleteSingleCharter("71489bhf4151");
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void whenDeleteNonExistingCharterInFilledDatabase_thenReturn404NotFound() {
        final int amountOfChartersToCreateBeforeTest = 7;
        createCharterMultipleTimes(amountOfChartersToCreateBeforeTest);
        final Response getCharterListResponse = CharterApi.getCharterList();
        final int charterCountBeforeDelete = getCharterListResponse.jsonPath().getList("$").size();
        Assert.assertThat(charterCountBeforeDelete, is(amountOfChartersToCreateBeforeTest));
        final Response response = CharterApi.deleteSingleCharter("71489bhf4151");
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND.value()));
    }

    private void createCharterMultipleTimes(int amountOfChartersToCreate) {
        for (int i = 0; i < amountOfChartersToCreate; i++) {
            CharterApi.postCharter("{ \"charterName\" : \"myCharter+" + i + "\"}");
        }
    }
}
