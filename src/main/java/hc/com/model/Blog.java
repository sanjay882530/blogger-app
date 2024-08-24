package hc.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String title;
    @Column(columnDefinition = "varchar(2000) default 'Test Blog'")
    private String content;
    @Column(columnDefinition = "varchar(255) default 'Sanjay'")
    private String author;
    @Column(columnDefinition = "varchar(500) default 'https://www.pexels.com/photo/smiling-mother-and-son-hugging-20877343/'")
    private String imageUrl;

    // Getters and Setters
}
