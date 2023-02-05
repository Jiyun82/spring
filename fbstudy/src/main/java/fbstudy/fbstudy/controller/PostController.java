package fbstudy.fbstudy.controller;

import fbstudy.fbstudy.domain.Member;
import fbstudy.fbstudy.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class PostController {

    @GetMapping("/posts/new")
    public String writeForm(){
        return "posts/writePostForm";
    }

    @PostMapping("/posts/new")
    public String upload(PostForm form, @SessionAttribute(name = "member", required = false) Member member){
        if(member == null){//잘못된 접근
            return "redirect:/";
        }

        Post post = new Post();
        post.setId(member.getEmail());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        return "redirect:/";
    }
}
