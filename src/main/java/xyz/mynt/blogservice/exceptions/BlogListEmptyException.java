package xyz.mynt.blogservice.exceptions;

public class BlogListEmptyException extends Exception{
    public BlogListEmptyException(String message) {
        super(message);
    }
}
