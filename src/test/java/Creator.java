import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Creator {
    String taskID;
    RequestSpecification taskSpec = given().
                    header("Authorization", "Bearer " + Endpoints.token).
                    baseUri(Endpoints.baseUri);
    TaskReqBody reqBody = new TaskReqBody("Buy products","at 12:00","en",2);

    public String getTaskID() {

        Response response = taskSpec.given()
                    .body(reqBody).contentType(ContentType.JSON)
                .when()
                    .post(Endpoints.tasks)
                .then()
                    .extract().response();
        taskID = response.path("id");
        return taskID;

    }
}
// TODO: применить static, например, в методах Creator, Specification