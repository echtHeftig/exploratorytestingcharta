package tests;

import api.CharterApi;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GetChartersTests extends BaseApiTests {

    @Test
    public void getCharterListReturnsEmptyArrayIfNoCharterExists() {
        Response response = CharterApi.getCharterList();

        Approvals.verify(response.getBody().prettyPrint());
    }
}
