package hc.com.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hc.com.model.Blog;
import hc.com.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    
    public Blog addBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
        
    }
    
    public List<Blog> findByUserId(Long userId) {
        return blogRepository.findByUserId(userId);
    }
}
