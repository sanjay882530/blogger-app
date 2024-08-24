package hc.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hc.com.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}