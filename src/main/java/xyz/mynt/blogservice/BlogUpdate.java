package xyz.mynt.blogservice;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlogUpdate {
    @NotBlank(message = "content is required")
    private String content;
}
