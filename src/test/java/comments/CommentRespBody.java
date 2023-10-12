package comments;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRespBody {
    private String id;
    private String task_id;
    private String project_id;
    private String content;
    private String posted_at;
    private CommentAttachment attachment;
}


