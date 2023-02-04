package com.example.study.service;

import com.example.study.domain.Member;
import com.example.study.domain.Post;
import com.example.study.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시물 작성, 게시글아이디 리턴
    public Long upload(Post post){
        postRepository.save(post);
        return post.getPid();
    }

    //모든 게시글 목록 리턴
    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public List<Post> findMyPosts(String id){
        return postRepository.findPostById(id);
    }

    public Post findOnePost(Long pid){
        return postRepository.findPostByPid(pid);
    }
}
