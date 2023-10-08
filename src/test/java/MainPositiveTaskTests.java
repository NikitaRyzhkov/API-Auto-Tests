import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class MainPositiveTaskTests {


    @Test
    public void deleteTaskTest() throws Exception{

        TaskService taskService = new TaskService();
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
    public void closeAndReopenTaskTest() throws Exception {

        TaskService taskService = new TaskService();
        String activeTaskID = Creator.getTaskRespBody().getId();

        taskService.closeTask(activeTaskID)
                .then()
                .statusCode(204);

        TaskRespBody closedTaskBody = taskService.getTask(activeTaskID)
                .then()
                .extract().body().as(TaskRespBody.class);

        Assert.assertEquals(closedTaskBody.getIs_completed(), "true");

        taskService.reopenTask(activeTaskID)
                .then()
                .statusCode(204);

        TaskRespBody reopenedTaskBody = taskService.getTask(activeTaskID)
                .then()
                .extract().as(TaskRespBody.class);
        Assert.assertEquals(reopenedTaskBody.getIs_completed(), "false");
    }

    @Test
    public void getTaskTest() throws Exception {
        String activeTaskID = Creator.getTaskRespBody().getId();
        String activeTaskContent = Creator.getTaskRespBody().getContent();
        TaskService taskService = new TaskService();

        TaskRespBody taskRespBody = taskService.getTask(activeTaskID)
                .then()
                .statusCode(200)
                .extract().body().as(TaskRespBody.class);

        Assert.assertEquals(taskRespBody.getContent(), activeTaskContent);
        Assert.assertEquals(taskRespBody.getId(), activeTaskID);


    }
    // TODO: в ответе на апдейт: labels must be array of strings? Выяснить, в чем проблема
    @Test
    public void updateTaskTest() throws Exception{

        TaskService taskService = new TaskService();
        TaskReqBody initialBody = new TaskReqBody("Buy products at Walmart");
        TaskReqBody updatedBody = new TaskReqBody("Buy fruits at Kroger");


        TaskRespBody initialRespBody = taskService.createTask(initialBody)
                .then()
                .log().body()
                .extract().body().as(TaskRespBody.class);

        TaskRespBody updatedRespBody = taskService.updateTask(initialRespBody.getId(),updatedBody)
                .then()
                .statusCode(204)
                .extract().body().as(TaskRespBody.class);

    }
    @Test
    public void createTaskTest() throws Exception {
        TaskService taskService = new TaskService();
        TaskReqBody reqBody = new TaskReqBody("Buy yet another T-shirt");

        TaskRespBody respBody = taskService.createTask(reqBody)
                .then()
                .log().body()
                .statusCode(200)
                .extract().body().as(TaskRespBody.class);
        Assert.assertEquals(respBody.getContent(),reqBody.getContent());
    }

    @Test
    public void getAllTasksTest() throws Exception {

        TaskService taskService = new TaskService();

        taskService.getAllTasks()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body();

    }
    // TODO: Десериализация ответа getAllTasks
}
