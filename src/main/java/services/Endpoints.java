package services;

public final class Endpoints {
    public static final String BASE_URI = "https://api.todoist.com/rest/v2";
    public static final String TOKEN = "688588768dbc9273d8c6cda915b2beb3fb6263b2";
    public static final String TASKS = "/tasks";
    public static final String SINGLE_TASK = "/tasks/{id}";
    public static final String REOPEN_TASK = "/tasks/{id}/reopen";
    public static final String CLOSE_TASK = "/tasks/{id}/close";
    public static final String COMMENTS = "/comments?task_id={id}";
    public static final String SINGLE_COMMENT = "/comments";
    public static final String PARTICULAR_COMMENT = "/comments/{id}";

}
