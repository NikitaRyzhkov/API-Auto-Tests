package tasks;

import helpers.TaskHelper;
import io.restassured.http.ContentType;
import models.TaskReqBody;
import models.TaskRespBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.TaskService;
import java.util.List;
import static org.hamcrest.Matchers.containsString;

public class MainPositiveTaskTests {

    TaskService taskService = new TaskService();


    @Test
    public void deleteTaskTest() throws Exception {

        String activeTaskID = TaskHelper.getTaskRespBody().getId();

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

        String activeTaskID = TaskHelper.getTaskID();

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

        String taskID = TaskHelper.getTaskID();

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

        TaskRespBody taskRespBodyAfterCreation = TaskHelper.getTaskRespBody();

        TaskRespBody taskRespBodyAfterGet = taskService.getTask(taskRespBodyAfterCreation.getId())
                .then()
                .statusCode(200)
                .extract().body().as(TaskRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(taskRespBodyAfterGet.toString(),taskRespBodyAfterCreation.toString());
        softAssert.assertAll();

    }

    // TODO: в ответе на апдейт: labels must be array of strings? Выяснить, в чем проблема
    @Test
    public void updateTaskTest() throws Exception {

        TaskReqBody initialBody = new TaskReqBody("Buy products at Walmart");
        TaskReqBody updatedBody = new TaskReqBody("Buy fruits at Kroger");

        TaskRespBody initialRespBody = taskService.createTask(initialBody)
                .then()
                .extract().body().as(TaskRespBody.class);


        TaskRespBody updatedRespBody = taskService.updateTask(initialRespBody.getId(), updatedBody)
                .then()
                .log().all()
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

    @Test
    public void getAllTasksTest() throws Exception {

        List<TaskRespBody> taskRespBodies = taskService.getAllTasks()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response().getBody().jsonPath().getList(".", TaskRespBody.class);

        System.out.println(taskRespBodies.size());

    }

}
/*
 TODO
  - вынести "throws Exception" в родительский класс
  - использовать пакеты
*/
