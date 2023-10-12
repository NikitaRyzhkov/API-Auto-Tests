package comments;

import org.checkerframework.checker.units.qual.C;
import tasks.TaskHelper;
import org.testng.annotations.Test;
import tasks.TaskRespBody;

public class MainCommentPositiveTests {

    CommentService commentService = new CommentService();

    @Test
    public void createCommentTest() {

        CommentReqBody reqBody = new CommentReqBody(TaskHelper.getTaskID(),"Comment 1");
        commentService.createComment(reqBody)
                .then()
                .log().body();

    }

    @Test
    public void getCommentTest() {

        commentService.getComment(CommentHelper.getCommentID())
                .then()
                .log()
                .all();
    }
    @Test
    public void deleteCommentTest() {

        commentService.deleteComment(CommentHelper.getCommentID())
                .then()
                .log()
                .all();
    }

    @Test
    public void updateCommentTest(){

        CommentReqBody updatedReqBody = new CommentReqBody(TaskHelper.getTaskRespBody().getId(),"Updated comment");

        commentService.updateComment(CommentHelper.getCommentID(),updatedReqBody)
                .then()
                .log().all();

    }
    @Test
    public void getAllCommentsTest() {
        commentService.getAllComments(CommentHelper.getCommentTaskID())
                .then().log().all();
    }

}
