package xyz.mynt.blogservice;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Blog {
    private String blogId;

    public Blog(String blogId) {
        this.blogId = blogId;
    }

    private String title;
    private String content;
    private LocalDate datePublish;
    private String author;
    private Boolean deleted = false;
}
