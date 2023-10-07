import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class NegativeTaskTests {

    String wrongID = "/101";
    String wrongToken = "688588768dbc9273d8c6cda915b2beb3fb6263b9";
    TaskReqBody reqUnnamedBody = new TaskReqBody("", "at 12:00", "en", 2);

    //Пустое тело запроса
    @Test
    public void UpdateTaskWithoutBody() throws Exception {

        //Specification.tasksSpec
                given().spec(Specification.taskSpec)
                .pathParam("id", Creator.getTaskID())
                .when().post(Endpoints.singleTask)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("At least one of supported fields should be set and non-empty"));

    }
    //Пустое тело запроса
    @Test
    public void CreateTaskWithoutBody() throws Exception {

       // Specification.tasksSpec.
                given().spec(Specification.taskSpec)
                .when().post(Endpoints.tasks)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("Required argument is missing"));
    }

    // Невалидный id
    @Test
    public void getTaskNotValidID() throws Exception {

//        Specification.tasksSpec.
                given().spec(Specification.taskSpec)
                .when()
                .get(Endpoints.tasks + wrongID)
                .then()
                .statusCode(404)
                .assertThat().body(equalTo("Task not found"));

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
       // Specification.tasksSpec
                given()
                        .spec(Specification.taskSpec)
                        .body(reqUnnamedBody).contentType(ContentType.JSON)
                .when()
                        .post(Endpoints.tasks)
                .then()
                        .statusCode(400).assertThat().body(containsString("Invalid argument value"));

    }


}
