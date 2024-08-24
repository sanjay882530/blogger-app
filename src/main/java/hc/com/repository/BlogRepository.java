package hc.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hc.com.model.Blog;
public interface BlogRepository extends JpaRepository<Blog, Long> {
	Optional<Blog> findById(Long id);

	List<Blog> findByUserId(Long userId);
}
