import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;


public class TaskTests {

// Два объекта, чтобы предотвратить применение не того спека
    Specification spec1 = new Specification();
    Specification spec = new Specification();


    // Test data
    Creator creator = new Creator();
    String updatedTaskBodyPath = "src/test/java/newtask_instance.json";
    File updatedTaskBody = new File(updatedTaskBodyPath);


    @Test
    public void postCreateTask() throws Exception {

        spec.tasksSpec.given()
                .body(creator.taskBody).contentType(ContentType.JSON)
                .when().post(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test
    public void getAllTasks() throws Exception {

        spec.tasksSpec.when()
                .get(Endpoints.tasks)
                .then().log().body().statusCode(200);
    }

    @Test
    public void getTask() throws Exception {

        spec1.singleTaskSpec.when()
                .get(Endpoints.singleTask)
                .then().log().body().statusCode(200);

    }
    @Test
    public void postUpdateTask() throws Exception {

        spec1.singleTaskSpec.given()
                .contentType(ContentType.JSON).body(updatedTaskBody)
                .when().post(Endpoints.singleTask)
                .then().log().body().statusCode(200);
    }

    @Test
    public void postCloseTask() throws Exception {

        spec1.singleTaskSpec.when()
                .post(Endpoints.closeTask)
                .then().log().body().statusCode(204);
    }

    @Test
    public void postReopenTask() throws Exception {

        spec1.singleTaskSpec.when()
                .post(Endpoints.reopenTask)
                .then().log().body().statusCode(204);
    }

    @Test
    public void deleteTask() throws Exception {
        String taskToDelete = creator.getTaskID();
        spec1.tasksSpec.given()
                .pathParam("id", taskToDelete)
                .when().delete(Endpoints.singleTask)
                .then().log().body().statusCode(204);
    }

}
