import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Cleaner {

    String[] id = {"7298853700"};
    @Test
    public void deleteTask() throws Exception {
        for(int i = 0; i< (id.length-1);i++)
        {given().spec(Specification.taskSpec)
                .pathParam("id", id[i])
                .when().delete(Endpoints.singleTask)
                .then().log().body().statusCode(204);}

    }

}

// TODO: Десериализация ответа getAllTasks, в том числе и  для очистки кучи тасков