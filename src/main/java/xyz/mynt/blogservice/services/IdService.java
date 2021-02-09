package xyz.mynt.blogservice.services;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {
    public String getNextBlogId() {
        return UUID.randomUUID().toString();
    }
}
