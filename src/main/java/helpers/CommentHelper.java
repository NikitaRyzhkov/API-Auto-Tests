package helpers;

import models.CommentReqBody;
import models.CommentRespBody;
import services.CommentService;

public class CommentHelper {

    static CommentService commentService = new CommentService();
    private final static CommentReqBody reqBody = new CommentReqBody(TaskHelper.getTaskID(), "Comment 2");
    private final static CommentRespBody respBody = commentService.createComment(reqBody)
            .then()
            .extract().body().as(CommentRespBody.class);

    public static CommentRespBody getCommentRespBody() {
        return respBody;
    }
    public static String getCommentID(){
        return respBody.getId();
    }
    public static String getCommentContent(){
        return respBody.getContent();
    }
    public static String getCommentTaskID(){
        return respBody.getTask_id();
    }
}
