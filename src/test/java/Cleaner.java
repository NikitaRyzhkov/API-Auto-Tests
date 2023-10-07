import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Cleaner {
    String [] id = {"7265630374","7265768409","7265796080","7265798497","7265829535","7265830276","7265832436","7265832905","7265832968","7265833343","7265838076","7265850241","7265862049","7265862705"};
    @Test
    public void deleteTask() throws Exception {
        for(int i = 0; i< (id.length-1);i++)
        {given().spec(Specification.taskSpec)
                .pathParam("id", id[i])
                .when().delete(Endpoints.singleTask)
                .then().log().body().statusCode(204);}
    }

}
