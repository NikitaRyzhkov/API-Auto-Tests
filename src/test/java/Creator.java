import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Creator {

    String taskBodyPath = "src/test/java/task_instance.json";
    File taskBody = new File(taskBodyPath);
    String taskID;
    RequestSpecification taskSpec = given().
                    header("Authorization", "Bearer " + Endpoints.token).
                    baseUri(Endpoints.baseUri);

    public String getTaskID() {

        Response response = taskSpec.given()
                    .body(taskBody).contentType(ContentType.JSON)
                .when()
                    .post(Endpoints.tasks)
                .then()
                    .extract().response();
        taskID = response.path("id");
        return taskID;

    }
}
