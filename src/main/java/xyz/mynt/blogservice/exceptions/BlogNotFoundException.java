package xyz.mynt.blogservice.exceptions;

public class BlogNotFoundException extends Exception{
    public BlogNotFoundException(String message) {
        super(message);
    }
}
