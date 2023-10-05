package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentAttachment {
    private String resource_type;
    private String file_url;
    private String file_type;
    private String file_name;
}
