import org.testng.annotations.Test;
import java.util.List;


public class Cleaner {

    TaskService taskService = new TaskService();
    @Test
    public void deleteAllTasks() throws Exception {

        List<TaskRespBody> taskRespBodies = taskService.getAllTasks()
                .then()
                .extract().response().getBody().jsonPath().getList(".",TaskRespBody.class);

        for (TaskRespBody body : taskRespBodies) {
            taskService.deleteTask(body.getId());
        }

        taskService.getAllTasks()
                .then()
                .log().body();

    }
}
