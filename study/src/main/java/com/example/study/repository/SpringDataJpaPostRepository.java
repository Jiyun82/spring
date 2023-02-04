package com.example.study.repository;

import com.example.study.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
    List<Post> findPostById(String id);

    Post findPostByPid(Long pid);
}
