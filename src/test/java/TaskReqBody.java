public class TaskReqBody {

    private String content;
    private String due_string;
    private String due_lang;
    private int priority;

    public TaskReqBody(String content, String due_string, String due_lang, int priority) {
        this.content = content;
        this.due_string = due_string;
        this.due_lang = due_lang;
        this.priority = priority;
    }
    public String getContent(){
        return content;
    }
    public String getDue_string(){
        return due_string;
    }
    public String getDue_lang(){
        return due_lang;
    }
    public int getPriority(){
        return priority;
    }

}

// TODO: применить lombok
