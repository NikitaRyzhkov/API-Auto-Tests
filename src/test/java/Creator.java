import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Creator {
    static String taskID;
    public final static TaskReqBody reqBody = new TaskReqBody("Buy products");

    public static TaskRespBody getTaskRespBody() {
        TaskRespBody taskRespBody =
                given()
                        .spec(Specification.taskSpec)
                        .body(reqBody).contentType(ContentType.JSON)
                        .when()
                        .post(Endpoints.tasks)
                        .then()
                        .extract().body().as(TaskRespBody.class);
        return taskRespBody;

    }


}
