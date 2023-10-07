import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Creator {
    static String taskID;
    static TaskReqBody reqBody = new TaskReqBody("Buy products","at 12:00","en",2);

    public static String getTaskID() {

        TaskRespBody taskRespBody =
                given()
                    .spec(Specification.taskSpec)
                    .body(reqBody).contentType(ContentType.JSON)
                .when()
                    .post(Endpoints.tasks)
                .then()
                    .extract().body().as(TaskRespBody.class);
        taskID = taskRespBody.getId();
        return taskID;

    }
}
