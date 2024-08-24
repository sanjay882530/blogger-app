package hc.com.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hc.com.model.Blog;
import hc.com.model.User;
import hc.com.repository.BlogRepository;
import hc.com.service.BlogService;
import hc.com.service.UserService;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
    	System.out.println("username::"+user.getUsername() +" password::"+ user.getPassword());
        User createdUser = userService.signUp(user);
        if (createdUser != null) {
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
    	System.out.println("username::"+user.getUsername()+" password::"+ user.getPassword());
        User authenticatedUser = userService.login(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog, @RequestHeader("username") String username) {
        User user = userService.checkUrl(username); // Replace with proper token validation
       
        if (!user.getRole().equals("BLOGGER")) {
            throw new RuntimeException("Not authorized!");
        }
        return blogRepository.save(blog);
    }
    @PostMapping("/add")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.addBlog(blog);
        return ResponseEntity.ok(createdBlog);
    }
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserBlogs(@PathVariable Long userId) {
		List<Blog> blogs = blogService.findByUserId(userId);
		return ResponseEntity.ok(blogs);
	}
	 
    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id, @RequestHeader("username") String username) {
        User user = userService.login(username, ""); // Replace with proper token validation
        if (!user.getRole().equals("BLOGGER")) {
            throw new RuntimeException("Not authorized!");
        }
        blogRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog, @RequestHeader("username") String username) {
        User user = userService.login(username, ""); // Replace with proper token validation
        if (!user.getRole().equals("BLOGGER")) {
            throw new RuntimeException("Not authorized!");
        }
        Blog existingBlog = blogRepository.findById(id).orElse(null);
        if (existingBlog != null) {
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            existingBlog.setAuthor(blog.getAuthor());
            existingBlog.setImageUrl(blog.getImageUrl());
            return blogRepository.save(existingBlog);
        }
        return null;
    }
}

