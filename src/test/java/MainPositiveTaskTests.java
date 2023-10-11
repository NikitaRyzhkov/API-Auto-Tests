import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.containsString;

public class MainPositiveTaskTests {

    TaskService taskService = new TaskService();


    @Test
    public void deleteTaskTest() throws Exception {

        String activeTaskID = Creator.getTaskRespBody().getId();

        taskService.deleteTask(activeTaskID)
                .then()
                .statusCode(204);

        taskService.getTask(activeTaskID)
                .then()
                .statusCode(404)
                .assertThat().body(containsString("Task not found"));


    }

    @Test
    public void closeTaskTest() throws Exception {

        String activeTaskID = Creator.getTaskRespBody().getId();

        taskService.closeTask(activeTaskID)
                .then()
                .statusCode(204);

        TaskRespBody closedTaskBody = taskService.getTask(activeTaskID)
                .then()
                .extract().body().as(TaskRespBody.class);

        Assert.assertEquals(closedTaskBody.getIs_completed(), "true");


    }

    @Test
    public void reopenTaskTest() {

        String taskID = Creator.getTaskRespBody().getId();

        taskService.closeTask(taskID)
                .then()
                .statusCode(204);


        taskService.reopenTask(taskID)
                .then()
                .statusCode(204);

        TaskRespBody reopenedTaskBody = taskService.getTask(taskID)
                .then()
                .extract().as(TaskRespBody.class);
        Assert.assertEquals(reopenedTaskBody.getIs_completed(), "false");

    }

    @Test
    public void getTaskTest() throws Exception {

        String activeTaskID = Creator.getTaskRespBody().getId();
        String activeTaskContent = Creator.getTaskRespBody().getContent();

        TaskRespBody taskRespBody = taskService.getTask(activeTaskID)
                .then()
                .statusCode(200)
                .extract().body().as(TaskRespBody.class);

        Assert.assertEquals(taskRespBody.getContent(), activeTaskContent);
        Assert.assertEquals(taskRespBody.getId(), activeTaskID);


    }

    // TODO: в ответе на апдейт: labels must be array of strings? Выяснить, в чем проблема
    @Test
    public void updateTaskTest() throws Exception {

        TaskReqBody initialBody = new TaskReqBody("Buy products at Walmart");
        TaskReqBody updatedBody = new TaskReqBody("Buy fruits at Kroger");

        TaskRespBody initialRespBody = taskService.createTask(initialBody)
                .then()
                .log().body()
                .extract().body().as(TaskRespBody.class);


        TaskRespBody updatedRespBody = taskService.updateTask(initialRespBody.getId(), updatedBody)
                .then()
                .log().body()
                .statusCode(204)
                .extract().body().as(TaskRespBody.class);

    }

    @Test
    public void createTaskTest() throws Exception {

        TaskReqBody reqBody = new TaskReqBody("Buy yet another T-shirt");

        TaskRespBody respBody = taskService.createTask(reqBody)
                .then()
                .statusCode(200)
                .extract().body().as(TaskRespBody.class);
        Assert.assertEquals(respBody.getContent(), reqBody.getContent());
    }

    // TODO: Десериализация ответа getAllTasks
    @Test
    public void getAllTasksTest() throws Exception {

        taskService.getAllTasks()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response().getBody().as(AllTaskBody.class);


    }

}
// TODO: вынести "throws Exception" в родительский класс
// TODO: использовать пакеты