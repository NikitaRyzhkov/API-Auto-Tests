package comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

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


