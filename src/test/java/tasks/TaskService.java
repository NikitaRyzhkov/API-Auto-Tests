package tasks;

import general.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TaskService {

    public Response deleteTask(String id) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id", id)
                .delete(Endpoints.singleTask);
    }
    public Response createTask(TaskReqBody taskReqBody) {
        return given()
                .spec(Specification.taskSpec)
                .body(taskReqBody).contentType(ContentType.JSON)
                .when()
                .post(Endpoints.tasks);
    }
    public Response updateTask(String id, TaskReqBody taskReqBody) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id", id)
                .body(taskReqBody).contentType(ContentType.JSON)
                .when().post(Endpoints.singleTask);
    }

    public Response closeTask(String id) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id", id)
                .post(Endpoints.closeTask);
    }
    public Response reopenTask(String id) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id", id)
                .when()
                .post(Endpoints.reopenTask);
    }
    public Response getTask(String id) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id", id)
                .get(Endpoints.singleTask);
    }
    public Response getAllTasks(){
        return given()
                .spec(Specification.taskSpec)
                .when()
                .get(Endpoints.tasks);
    }
}

