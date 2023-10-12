package tasks;

import org.testng.annotations.Test;
import java.util.List;


public class TaskHelper {

    static TaskService taskService = new TaskService();
    private final static TaskReqBody reqBody = new TaskReqBody("Buy products");
    private static final TaskRespBody respBody = taskService.createTask(reqBody)
            .then()
            .extract()
            .body().as(TaskRespBody.class);

    public static TaskRespBody getTaskRespBody() {
        return respBody;
    }
    public static String getTaskID() {
        return respBody.getId();
    }
    public static String getTaskContent(){
        return respBody.getContent();
    }
    @Test
    public void deleteAllTasks() throws Exception {

        List<TaskRespBody> taskRespBodies = taskService.getAllTasks()
                .then()
                .extract().response().getBody().jsonPath().getList(".", TaskRespBody.class);

        for (TaskRespBody body : taskRespBodies) {taskService.deleteTask(body.getId());}
    }



}
