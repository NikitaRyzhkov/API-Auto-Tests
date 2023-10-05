package models;

import lombok.Getter;

@Getter
public class CommentReqBody {

    private String task_id;
    private String project_id;
    private String content;
    private CommentAttachment attachment;

    public CommentReqBody(String task_id, String content) {
        this.task_id = task_id;
        this.content = content;
    }

    public CommentReqBody(String task_id, String content, CommentAttachment attachment) {
        this.task_id = task_id;
        this.content = content;
        this.attachment = attachment;
    }

}
