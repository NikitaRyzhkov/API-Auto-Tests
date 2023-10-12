import lombok.Data;

@Data
public class AllTaskBody {

    private String id;
    private String assigner_id;
    private String assignee_id;
    private String project_id;
    private String section_id;
    private String parent_id;
    private int order;
    private String content;
    private String description;
    private String is_completed;
    private String[] labels;
    private int priority;
    private int comment_count;
    private String creator_id;
    private String created_at;
    private TaskDue due;
    private String url;
    private String duration;

}
