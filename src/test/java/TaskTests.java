import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

// С помощью testng.xml запускать гет запросы и остальные в отдельных тестах
public class TaskTests {

    Specification spec = new Specification();


    // Test data
    Creator creator = new Creator();
    String updatedTaskBodyPath = "src/test/java/newtask_instance.json";
    File updatedTaskBody = new File(updatedTaskBodyPath);


    @Test
    public void CreateTask() throws Exception {

        spec.tasksSpec.given()
                .body(creator.taskBody).contentType(ContentType.JSON)
                .when().post(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test (groups = {"get.requests"})
    public void getAllTasks() throws Exception {

        spec.tasksSpec.when()
                .get(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test (groups = {"get.requests"})
    public void getTask() throws Exception {

        spec.singleTaskSpec.when()
                .get(Endpoints.singleTask)
                .then().log().body().statusCode(200);

    }
    @Test
    public void UpdateTask() throws Exception {

        spec.singleTaskSpec.given()
                .contentType(ContentType.JSON).body(updatedTaskBody)
                .when().post(Endpoints.singleTask)
                .then().log().body().statusCode(200);
    }

    @Test
    public void CloseTask() throws Exception {

        spec.singleTaskSpec.when()
                .post(Endpoints.closeTask)
                .then().log().body().statusCode(204);
    }

    @Test
    public void ReopenTask() throws Exception {

        spec.singleTaskSpec.when()
                .post(Endpoints.reopenTask)
                .then().log().body().statusCode(204);
    }

    @Test
    public void deleteTask() throws Exception {
        String taskToDelete = creator.getTaskID();
        spec.tasksSpec.given()
                .pathParam("id", taskToDelete)
                .when().delete(Endpoints.singleTask)
                .then().log().body().statusCode(204);
    }

}
// TODO: составить изолированные тесты из "логирующих"

// TODO: уйти от изоляции гет запросов для позитивных тестов
// Возможное решение: вынос в отдельный класс всю информацию о запросе, т. н. Сервис