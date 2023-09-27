import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;


public class TaskTests {

    // Test data
    Creator creator = new Creator();
    String taskID = creator.getTaskID();
    String updatedTaskBodyPath = "src/test/java/newtask_instance.json";
    File updatedTaskBody = new File(updatedTaskBodyPath);
    // Test specification
    RequestSpecification singleTaskSpec = given().
            pathParam("id", taskID).
            header("Authorization", "Bearer " + Endpoints.token).
            baseUri(Endpoints.baseUri);
    RequestSpecification tasksSpec = given().
            header("Authorization", "Bearer " + Endpoints.token).
            baseUri(Endpoints.baseUri);


    @Test
    public void postCreateTask() throws Exception {

        tasksSpec.given().body(creator.taskBody).contentType(ContentType.JSON).
                when().post(Endpoints.tasks).then().log().body().statusCode(200);
    }

    @Test
    public void getTask() throws Exception {

        singleTaskSpec.when().get(Endpoints.singleTask).then().log().body().statusCode(200);

    }

    @Test
    public void getAllTasks() throws Exception {

        tasksSpec.when().get(Endpoints.tasks).then().log().body().statusCode(200);
    }
    @Test
    public void getAllTasks1() throws Exception {

       given().
               header("Authorization", "Bearer " + Endpoints.token).
               baseUri(Endpoints.baseUri).
               when().get(Endpoints.tasks).
               then().log().body().statusCode(200);
    }

    @Test
    public void postUpdateTask() throws Exception {

        singleTaskSpec.given().contentType(ContentType.JSON).body(updatedTaskBody).when().post(Endpoints.singleTask).then().log().body().statusCode(200);
    }

    @Test
    public void postCloseTask() throws Exception {

        singleTaskSpec.when().post(Endpoints.closeTask).then().log().body().statusCode(204);
    }

    @Test
    public void postReopenTask() throws Exception {

        singleTaskSpec.when().post(Endpoints.reopenTask).then().log().body().statusCode(204);
    }

    @Test
    public void deleteTask() throws Exception {
        String taskToDelete = creator.getTaskID();
        tasksSpec.given().pathParam("id", taskToDelete).when().delete(Endpoints.singleTask).then().log().body().statusCode(204);
    }

}
