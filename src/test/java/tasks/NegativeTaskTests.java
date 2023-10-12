package tasks;

import org.testng.annotations.Test;
import general.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTaskTests {

    TaskService taskService = new TaskService();

    // TODO: в ответе на апдейт: labels must be array of strings? Выяснить, в чем проблема
    //Пустое тело запроса
    @Test
    public void UpdateTaskWithoutBody() throws Exception {

        TaskReqBody taskReqBody = new TaskReqBody();

        taskService.updateTask(TaskHelper.getTaskRespBody().getId(),taskReqBody)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("At least one of supported fields should be set and non-empty"));

    }

    //Пустое тело запроса
    @Test
    public void CreateTaskWithoutBody() throws Exception {

        TaskReqBody taskReqBody = new TaskReqBody();
        taskService.createTask(taskReqBody)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("Required argument is missing"));
    }

    // Невалидный id
    @Test
    public void getTaskNotValidID() throws Exception {

        String wrongID = "/101";

        taskService.getTask(wrongID)
                .then()
                .statusCode(404)
                .log().body();

    }

    // Пустой токен
    @Test
    public void getAllTasksEmptyToken() throws Exception {

        given()
                .header("Authorization", "")
                .when()
                .get(Endpoints.baseUri + Endpoints.tasks)
                .then()
                .statusCode(401)
                .assertThat().body(equalTo("Forbidden"));

    }

    // Невалидный токен
    @Test
    public void getAllTasksNotValidToken() throws Exception {

        String wrongToken = "101588768dbc9273d8c6cda915b2beb3fb6263b9";

        given()
                .header("Authorization", wrongToken)
                .when()
                .get(Endpoints.baseUri + Endpoints.tasks)
                .then()
                .statusCode(403)
                .assertThat().body(containsString("Sorry, you are forbidden to access this"));
    }

    // Тело запроса с незаполненным полем "name"
    @Test
    public void createUnnamedTask() throws Exception {

        TaskReqBody taskReqBody = new TaskReqBody("");

        taskService.createTask(taskReqBody)
                .then()
                .statusCode(400)
                .assertThat().body(containsString("Invalid argument value"));

    }



}
