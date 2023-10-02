import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class NegativeTaskTests {

    Specification spec1 = new Specification();
    Specification spec = new Specification();
    String wrongID = "/101";
    String wrongToken = "688588768dbc9273d8c6cda915b2beb3fb6263b9";

    @Test //Пустое тело запроса
    public void UpdateTaskWithoutBody() throws Exception {

        spec1.singleTaskSpec.given()
                .when().post(Endpoints.singleTask)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("At least one of supported fields should be set and non-empty"));

    }

    @Test //Пустое тело запроса
    public void CreateTaskWithoutBody() throws Exception {

        spec.tasksSpec.given()
                .when().post(Endpoints.tasks)
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("Required argument is missing"));
    }

    @Test // Невалидный id
    public void getTaskNotValidID() throws Exception {

        spec.tasksSpec.
                when().
                get(Endpoints.tasks + wrongID)
                .then()
                .statusCode(404)
                .assertThat().body(equalTo("Task not found"));

    }

    @Test // Пустой токен
    public void getAllTasksEmptyToken() throws Exception {

        given().header("Authorization", "").when()
                .get(Endpoints.baseUri + Endpoints.tasks)
                .then()
                .statusCode(401)
                .assertThat().body(equalTo("Forbidden"));

    }

    @Test // Невалидный токен
    public void getAllTasksNotValidToken() throws Exception {

        given().header("Authorization", wrongToken).when()
                .get(Endpoints.baseUri + Endpoints.tasks)
                .then().statusCode(403)
                .assertThat().body(containsString("Sorry, you are forbidden to access this"));
    }


}
