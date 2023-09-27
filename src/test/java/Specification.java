import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {

    Creator creator = new Creator();
    String taskID = creator.getTaskID();

    public final RequestSpecification singleTaskSpec = given()
            .pathParam("id", taskID)
            .header("Authorization", "Bearer " + Endpoints.token)
            .baseUri(Endpoints.baseUri);
    public final RequestSpecification tasksSpec = given()
            .header("Authorization", "Bearer " + Endpoints.token)
            .baseUri(Endpoints.baseUri);
}
