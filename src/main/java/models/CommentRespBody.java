package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
public class CommentRespBody {
    private String id;
    private String task_id;
    private String project_id;
    private String content;
    private String posted_at;
    private CommentAttachment attachment;
}


