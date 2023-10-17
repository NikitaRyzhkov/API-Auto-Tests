package tasks;

import helpers.TaskHelper;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import models.TaskReqBody;
import models.TaskRespBody;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.TaskService;

public class AdditionalPositiveTaskTests {

    TaskService taskService = new TaskService();

    @Description("Создание Task с двумя заполненными полями: content, description")
    @Test
    public void createTaskWithDescriptionTest() {
        TaskReqBody taskReqBody = new TaskReqBody("Buy coffee", "If there's no coffee, buy tea");

        TaskRespBody respBody = taskService.createTask(taskReqBody)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(TaskRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respBody.getContent(), taskReqBody.getContent());
        softAssert.assertEquals(respBody.getDescription(), taskReqBody.getDescription());
        softAssert.assertAll();
    }

    @Description("Запрос на обновление двух полей: content, labels")
    @Test
    public void updateTaskWithLabelsTest() {

        String[] labels = {"Shopping", "Malls"};
        TaskReqBody taskReqBody = new TaskReqBody("Buy food", labels);

        TaskRespBody respBody = taskService.updateTask(TaskHelper.getTaskID(), taskReqBody)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(TaskRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respBody.getContent(), taskReqBody.getContent());
        softAssert.assertEqualsNoOrder(respBody.getLabels(), labels);
        softAssert.assertAll();
    }

    @Description("Запрос на обновление трех полей: content, labels, priority")
    @Test
    public void updateTaskWithLabelsAndPriority() {
        String[] labels = {"Hobby", "Art"};
        TaskReqBody reqBody = new TaskReqBody("Order paints", 2, labels);

        TaskRespBody respBody = taskService.updateTask(TaskHelper.getTaskID(), reqBody)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(TaskRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respBody.getPriority(), reqBody.getPriority());
        softAssert.assertEqualsNoOrder(respBody.getLabels(), labels);
        softAssert.assertEquals(respBody.getContent(), reqBody.getContent());
        softAssert.assertAll();
    }




}
