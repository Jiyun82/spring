package com.example.study.controller;

import com.example.study.SessionConstants;
import com.example.study.domain.Member;
import com.example.study.domain.Post;
import com.example.study.service.MemberService;

import com.example.study.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @Autowired
    public MemberController(MemberService memberService, PostService postService){
        this.memberService = memberService;
        this.postService = postService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members/logIn")
    public String logInForm(){
        return "members/logInForm";
    }

    @PostMapping("/members/logIn")
    public String logIn(MemberForm form, HttpServletRequest request){
        //form.getId() 해서 존재하는 회원인지 확인하고 form.getPassword()로 비밀번호 맞는지 체크
        Optional<Member> member = memberService.findById(form.getId());

        //true -> null이 아님
        if(member.isPresent()){
            if(memberService.checkPassword(form.getId(), form.getPassword())){
                HttpSession session = request.getSession();//세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성하여 반환
                session.setAttribute(SessionConstants.LOGIN_MEMBER, member.get());//세션에 로그인 회원 정보 보관

                return "redirect:/";
            }
            //비번틀림
            return "members/logInForm";
        }
        //가입된 회원이 아님
        return "members/createMemberForm";
    }

    @GetMapping("/members/logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();//세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, null);//세션에 로그인 회원 정보 보관
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/mypage")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("member", session.getAttribute(SessionConstants.LOGIN_MEMBER));
        //postService.findMyPosts("a");

        return "members/mypage";
    }
}
