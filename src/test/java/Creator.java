import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class Creator {
    private final static TaskReqBody reqBody = new TaskReqBody("Buy products");

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
