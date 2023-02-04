package com.example.study.repository;

import com.example.study.SessionConstants;
import com.example.study.domain.Member;
import com.example.study.domain.Post;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class JpaPostRepository implements PostRepository{

    private final EntityManager em;

    public JpaPostRepository(EntityManager em){
        this.em = em;
    }

    public Post save(Post post){
        em.persist(post);
        return post;
    }

    public List<Post> findAll(){
        return em.createQuery("select m form Post m", Post.class).getResultList();
    }

    //왜 되지?
    public List<Post> findPostById(String id){
        return em.createQuery("select m from Post m", Post.class).getResultList();
    }

    public Post findPostByPid(Long pid){
        return em.createQuery("select m from Post m", Post.class).getSingleResult();
    }
}
