import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskReqBody {

    private String content;
    private String due_string;
    private String due_lang;
    private int priority;

}

