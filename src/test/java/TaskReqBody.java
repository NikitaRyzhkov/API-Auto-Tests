import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskReqBody {

    private String content;
    private String description;
    private String project_id;
    private String section_id;
    private String parent_id;
    private int order;
    private String[] labels;
    private int priority;
    private String due_string;
    private String due_date;
    private String due_datetime;
    private String due_lang;
    private String assignee_id;
    private String duration;
    private String duration_unit;

// only one due_* field can be used (due_lang is special case)


    public TaskReqBody(String content){
        this.content = content;

    }

}

// TODO: разобраться, почему запрос не проходит, если duration объявляется как int