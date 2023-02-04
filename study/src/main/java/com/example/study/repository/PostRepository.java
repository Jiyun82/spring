package com.example.study.repository;

import com.example.study.domain.Member;
import com.example.study.domain.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface PostRepository {

    Post save(Post post);//게시물 저장

    List<Post> findAll();//모든 게시글 리턴

    List<Post> findPostById(String id);//매개변수 아무것도 설정 안하면 에러남 왜지???????????ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ

    Post findPostByPid(Long pid);
}
