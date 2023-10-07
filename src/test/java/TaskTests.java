import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

// С помощью testng.xml запускать гет запросы и остальные в отдельных тестах
public class TaskTests {

    // Test data
    TaskReqBody reqBody = new TaskReqBody("Buy products","at 12:00","en",2);
    TaskReqBody reqBodyUpdated = new TaskReqBody("Fill the docs","tomorrow","en",2);




    @Test
    public void CreateTask() throws Exception {

        given().spec(Specification.taskSpec)
                .body(reqBody).contentType(ContentType.JSON)
                .when().post(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test
    public void getAllTasks() throws Exception {

      //  Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .when()
                .get(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test
    public void getTask() throws Exception {

//        Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .pathParam("id", Creator.getTaskID())
                .get(Endpoints.singleTask)
                .then().log().body().statusCode(200);

    }
    @Test
    public void UpdateTask() throws Exception {

      //  Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .pathParam("id", Creator.getTaskID())
                .contentType(ContentType.JSON).body(reqBodyUpdated)
                .when().post(Endpoints.singleTask)
                .then().log().body().statusCode(200);
    }

    @Test
    public void CloseTask() throws Exception {

       // Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .pathParam("id", Creator.getTaskID())
                .post(Endpoints.closeTask)
                .then().log().body().statusCode(204);
    }

    @Test
    public void ReopenTask() throws Exception {

        //Specification.tasksSpec
                given().spec(Specification.taskSpec)
                    .pathParam("id", Creator.getTaskID())
                .when()
                    .post(Endpoints.reopenTask)
                .then()
                    .log().body().statusCode(204);
    }

    @Test
    public void deleteTask() throws Exception {
        //Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .pathParam("id", Creator.getTaskID())
                .when().delete(Endpoints.singleTask)
                .then().log().body().statusCode(204);
    }

}
// TODO: составить изолированные тесты из "логирующих"
// TODO: уйти от изоляции гет запросов для позитивных тестов
// Возможное решение: вынос в отдельный класс всю информацию о запросе, т. н. Сервис
