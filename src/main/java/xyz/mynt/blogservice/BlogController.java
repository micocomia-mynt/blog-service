package xyz.mynt.blogservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.mynt.blogservice.exceptions.BlogListEmptyException;
import xyz.mynt.blogservice.exceptions.BlogNotFoundException;
import xyz.mynt.blogservice.services.BlogService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(path = "create") // Create endpoint, create a blog
    public ResponseEntity<Map<String, String>>  create(@Valid @RequestBody BlogCreation request){
        Map<String, String> response = new HashMap<>();
        LOGGER.info("Processing blog creation");

        Blog createdBlog = blogService.create(request);
        response.put("message", "Blog created");

        LOGGER.info("Blog creation done");
        return ResponseEntity.created(URI.create(createdBlog.getBlogId())).body(response); // Return response
    }

    @GetMapping(path = "blogs") // blogs endpoint, return array of blogs
    public ResponseEntity<List<Blog>> getBlog() throws BlogListEmptyException {
        LOGGER.info("Getting blog lists");

        List<Blog> blogList = blogService.getBlogs();

        LOGGER.info("Got blog list");
        return ResponseEntity.ok(blogList);
    }

    @GetMapping(path = "blogs/{Id}") // blogs endpoint, return array of blogs
    public ResponseEntity<Blog> getBlogById(@PathVariable String Id) throws BlogNotFoundException {
        LOGGER.info("Getting blog {}",Id);

        Blog blog = blogService.getBlogById(Id);

        LOGGER.info("Got blog {}",Id);
        return ResponseEntity.ok(blog);
    }

    @PutMapping(path = "blogs/{Id}")
    public ResponseEntity<Map<String, String>> updateBlog(@PathVariable String Id,
                                             @Valid @RequestBody BlogUpdate request) throws BlogNotFoundException {
        LOGGER.info("Updating blog {}",Id);
        Map<String, String> response = new HashMap<>();

        Blog blog = blogService.getBlogById(Id);
        blogService.updateBlog(blog, request);
        response.put("message", "Blog #" + Id + " updated");

        LOGGER.info("Updated blog {}",Id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "blogs/{Id}")
    public ResponseEntity<Map<String, String>> deleteBlog(@PathVariable String Id) throws BlogNotFoundException {
        LOGGER.info("Deleting blog {}",Id);
        Map<String, String> response = new HashMap<>();

        blogService.deleteBlog(Id);
        response.put("message", "Blog #" + Id + " deleted");

        LOGGER.info("Deleted blog {}",Id);
        return ResponseEntity.ok(response);
    }

}
