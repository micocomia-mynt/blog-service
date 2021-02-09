package xyz.mynt.blogservice;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class BlogCreation {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // ISO Format YYYY-MM-DD
    @NotBlank(message = "datePublished is required")
    private String datePublished;

    @NotBlank(message = "author is required")
    private String author;
}
