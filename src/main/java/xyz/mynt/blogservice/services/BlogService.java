package xyz.mynt.blogservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.mynt.blogservice.Blog;
import xyz.mynt.blogservice.BlogCreation;
import xyz.mynt.blogservice.exceptions.BlogListEmptyException;
import xyz.mynt.blogservice.exceptions.BlogNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class BlogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogService.class);

    private final List<Blog> blogs = new ArrayList<>();
    private final IdService idService;

    public BlogService(IdService idService) {
        this.idService = idService;
    }

    public Blog create(BlogCreation blogCreation) {
        LocalDate parsedPublished = LocalDate.parse(blogCreation.getDatePublished());

        // Generate blog ID
        String blogId = idService.getNextBlogId();

        // Save details as blog with ID
        Blog newBlog = new Blog(blogId);
        newBlog.setAuthor(blogCreation.getAuthor());
        newBlog.setContent(blogCreation.getContent());
        newBlog.setDatePublish(parsedPublished);
        newBlog.setTitle(blogCreation.getTitle());

        blogs.add(newBlog);
        return newBlog;
    }

    public List<Blog> getBlogs() throws BlogListEmptyException {
        if (blogs.isEmpty()){
            throw new BlogListEmptyException("Blog list is empty.");
        }
        return blogs;
    };

    public Blog getBlogById(String Id) throws BlogNotFoundException{
       return blogs.stream()
                .filter(blog -> Id.equals(blog.getBlogId()))
                .findAny()
                .orElseThrow(() -> new BlogNotFoundException("Blog does not exist"));
    }

    public void updateBlog(Blog blog, BlogCreation blogCreation){
        int blogIndex = blogs.indexOf(blog);

        // get user id
        LocalDate parsedPublished = LocalDate.parse(blogCreation.getDatePublished());

        blog.setTitle(blogCreation.getTitle());
        blog.setAuthor(blogCreation.getAuthor());
        blog.setContent(blogCreation.getContent());
        blog.setDatePublish(parsedPublished);

        blogs.set(blogIndex, blog);
    }

    public void deleteBlog(String Id) throws BlogNotFoundException {
        Blog blog = getBlogById(Id);

        int blogIndex = blogs.indexOf(blog);
        blog.setDeleted(TRUE);

        blogs.set(blogIndex, blog);
    }

}
