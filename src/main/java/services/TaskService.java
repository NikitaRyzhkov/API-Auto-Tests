package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.TaskReqBody;
import static io.restassured.RestAssured.given;

public class TaskService {

    public Response deleteTask(String id) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id", id)
                .delete(Endpoints.SINGLE_TASK);
    }
    public Response createTask(TaskReqBody taskReqBody) {
        return given()
                .spec(Specification.SPECIFICATION)
                .body(taskReqBody).contentType(ContentType.JSON)
                .when()
                .post(Endpoints.TASKS);
    }
    public Response updateTask(String id, TaskReqBody taskReqBody) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id", id)
                .body(taskReqBody).contentType(ContentType.JSON)
                .when().post(Endpoints.SINGLE_TASK);
    }

    public Response closeTask(String id) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id", id)
                .post(Endpoints.CLOSE_TASK);
    }
    public Response reopenTask(String id) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id", id)
                .when()
                .post(Endpoints.REOPEN_TASK);
    }
    public Response getTask(String id) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id", id)
                .get(Endpoints.SINGLE_TASK);
    }
    public Response getAllTasks(){
        return given()
                .spec(Specification.SPECIFICATION)
                .when()
                .get(Endpoints.TASKS);
    }
}

