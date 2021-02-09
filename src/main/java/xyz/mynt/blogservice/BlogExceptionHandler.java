package xyz.mynt.blogservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.mynt.blogservice.exceptions.BlogListEmptyException;
import xyz.mynt.blogservice.exceptions.BlogNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BlogExceptionHandler {
    @ExceptionHandler({BlogNotFoundException.class, BlogListEmptyException.class})
    public ResponseEntity<Map<String, String>> handleLogicException(Exception e){ // Format output for exceptions
        Map<String, String> response = new HashMap<>();
        response.put("message", "Error: " + e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
