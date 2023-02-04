package com.example.study.controller;

import com.example.study.SessionConstants;
import com.example.study.domain.Member;
import com.example.study.domain.Post;
import com.example.study.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/posts/new")
    public String writeForm(){
        return "posts/writePostForm";
    }

    @PostMapping("/posts/new")
    public String post(PostForm form, @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member){
        if(member == null){
            return "redirect:/";
        }

        Post post = new Post();
        post.setId(member.getId());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.upload(post);

        return "redirect:/";
    }

    @GetMapping("/posts/list")
    public String postList(Model model){
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/showMyPosts")
    public String myPosts(Model model, @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member){
        List<Post> posts = postService.findMyPosts(member.getId());
        model.addAttribute("posts", posts);

        return "posts/myPosts";
    }

    @RequestMapping(value="/posts/sendPid", method= RequestMethod.GET)
    public String showOne(Long pid, Model model, @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member){
        model.addAttribute("post", postService.findOnePost(pid));
        model.addAttribute("member", member);

        return "posts/onePost";
    }
}
