package comments;

import helpers.CommentHelper;
import io.restassured.http.ContentType;
import models.CommentReqBody;
import models.CommentRespBody;
import models.TaskRespBody;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import services.CommentService;
import helpers.TaskHelper;
import org.testng.annotations.Test;

import java.util.List;

public class MainCommentPositiveTests {

    CommentService commentService = new CommentService();

    @Test
    public void createCommentTest() {

        CommentReqBody reqBody = new CommentReqBody(TaskHelper.getTaskID(), "Comment 1");
        CommentRespBody respBody = commentService.createComment(reqBody)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(CommentRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respBody.getContent(), reqBody.getContent());
        softAssert.assertEquals(reqBody.getTask_id(), reqBody.getTask_id());
        softAssert.assertAll();

        commentService.deleteComment(respBody.getId());

    }

    @Test
    public void getCommentTest() {

        CommentRespBody bodyAfterCreation = CommentHelper.getCommentRespBody();

        CommentRespBody bodyAfterGet = commentService.getComment(bodyAfterCreation.getId())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(CommentRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(bodyAfterGet.toString(), bodyAfterCreation.toString());
        softAssert.assertAll();

    }

    // при запросе гет удаленного комментария, приходит его тело и код 200 (возможно это баг)
    @Test
    public void deleteCommentTest() {

        commentService.deleteComment(CommentHelper.getCommentID())
                .then()
                .statusCode(204);

    }

    @Test
    public void updateCommentTest() {

        CommentRespBody respBody = CommentHelper.getCommentRespBody();
        CommentReqBody updatedReqBody = new CommentReqBody(respBody.getId(), "Updated comment");

        CommentRespBody updatedBody = commentService.updateComment(CommentHelper.getCommentID(), updatedReqBody)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(CommentRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updatedBody.getId(), respBody.getId());
        softAssert.assertNotEquals(updatedBody.getContent(), respBody.getContent());
        softAssert.assertAll();


    }

    @Test
    public void getAllCommentsTest() {

        CommentRespBody respBody = CommentHelper.getCommentRespBody();

        List<CommentRespBody> respBodies = commentService.getAllComments(respBody.getTask_id())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getList(".", CommentRespBody.class);

        SoftAssert softAssert = new SoftAssert();
        for (CommentRespBody body : respBodies) {
            softAssert.assertEquals(body.getId(), respBody.getId());
            softAssert.assertAll();
        }

    }

}
