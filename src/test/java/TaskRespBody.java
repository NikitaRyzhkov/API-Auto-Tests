import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskRespBody {
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

//{
//        "id": "7291950902",
//        "assigner_id": null,
//        "assignee_id": null,
//        "project_id": "2216743585",
//        "section_id": null,
//        "parent_id": null,
//        "order": 301,
//        "content": "Buy products",
//        "description": "",
//        "is_completed": false,
//        "labels": [
//
//        ],
//        "priority": 2,
//        "comment_count": 0,
//        "creator_id": "23957370",
//        "created_at": "2023-10-05T18:36:36.772875Z",
//        "due": {
//        "date": "2023-10-06",
//        "string": "at 12:00",
//        "lang": "en",
//        "is_recurring": false,
//        "datetime": "2023-10-06T12:00:00"
//        },
//        "url": "https://todoist.com/showTask?id=7291950902",
//        "duration": null
//        }