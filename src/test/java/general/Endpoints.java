package general;

public final class Endpoints {
    public static final String baseUri = "https://api.todoist.com/rest/v2";
    public static final String token = "688588768dbc9273d8c6cda915b2beb3fb6263b2";
    public static final String tasks = "/tasks";
    public static final String singleTask = "/tasks/{id}";
    public static final String reopenTask = "/tasks/{id}/reopen";
    public static final String closeTask = "/tasks/{id}/close";
    public static final String comments = "/comments?task_id={id}";
    public static final String singleComment = "/comments";
    public static final String particularComment = "/comments/{id}";

}
