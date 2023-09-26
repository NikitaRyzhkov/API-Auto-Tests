import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;




public class TaskTests {

    String token = "688588768dbc9273d8c6cda915b2beb3fb6263b2";
    RequestSpecification taskSpec = given().header("Authorization","Bearer "+token).baseUri("https://api.todoist.com/rest/v2/tasks");
    String taskBodyPath = "src/test/java/task_instanse.json";
    String updatedTaskBodyPath = "src/test/java/newtask_instanse.json";
    File taskBody = new File(taskBodyPath);
    File updatedTaskBody = new File(updatedTaskBodyPath);






    @Test
    public void postCreateTask() throws Exception {

        taskSpec.given().body(taskBody).contentType(ContentType.JSON).
                when().post().then().log().body().statusCode(200);
    }
    @Test
    public void getTask() throws Exception {

        taskSpec.when().get("/7262900498").then().log().body().statusCode(200);

    }
    @Test
    public void getAllTasks() throws Exception {

        taskSpec.when().get().then().log().body().statusCode(200);
    }
    @Test
    public void postUpdateTask() throws Exception {

        taskSpec.given().contentType(ContentType.JSON).body(updatedTaskBody).when().post("/7262900498").then().log().body().statusCode(200);
    }
    @Test
    public void postCloseTask() throws Exception {

        taskSpec.when().post("/7262900498/close").then().log().body().statusCode(204);
    }
    @Test
    public void postReopenTask() throws Exception {

        taskSpec.when().post("/7262900498/reopen").then().log().body().statusCode(204);
    }
    @Test
    public void deleteTask() throws Exception {
        taskSpec.when().delete("/7262900498").then().log().body().statusCode(204);
    }

}
// вытащить айдишник задачи и провести с ним дальнейшие тесты
// без этого это не автотесты, а херь