package com.example.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class WebHookForm {
    public static final String WEB_HOOK_REG = "(?:http|https)://[^\\s^<^>^{^}]+(?:\\s|$|\\n)";

    @NotEmpty(message = "hook_url_not_empty")
    @Length(min = 8, max = 2048, message = "hook_url_too_long")
    @Pattern(regexp = WEB_HOOK_REG, message = "hook_url_error")
    private String hook_url;
    @Length(max = 255, message = "token_too_long")
    private String token;

    private boolean type_push;
    private boolean type_mr_pr;
    private boolean type_topic;
    private boolean type_member;
    private boolean type_comment;
    private boolean type_document;
    private boolean type_watch;
    private boolean type_star;
    private boolean type_task;
    private boolean type_ci;
    private boolean type_artifact;
    private boolean type_agile;

    private boolean active;
    private String type;
    private String schema;
    private List<String> events;

}
