package fbstudy.fbstudy.controller;

import fbstudy.fbstudy.domain.Member;
import fbstudy.fbstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@RestController
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    /*
    @PostMapping("/members/new")
    public Member create(@RequestBody MemberForm form) throws Exception{
        Member member = new Member();
        member.setNickname(form.getNickName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.insertMember(member);

        return member;
    }*/

    @PostMapping("/members/new")
    public String create(MemberForm form) throws Exception{
        Member member = new Member();
        //member.setId(form.getId());
        member.setNickname(form.getNickName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.insertMember(member);

        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(){
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(MemberForm form, HttpServletRequest request) throws Exception{
        Member member = memberService.getMemberDetail(form.getEmail());

        if(member == null){//가입된 회원이 아님
            return "members/createMemberForm";
        }else{
            if(member.getPw().equals(form.getPw())){
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                //원래는 SessionConstants.LOGIN_MEMBER 만들어서 햇다

                return "redirect:/";
            }else{//비번 틀림
                return "members/loginForm";
            }
        }
    }

    @GetMapping("/members/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("member", null);

        return "redirect:/";
    }

    @GetMapping("/members/mypage")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("member", session.getAttribute("member"));

        return "members/mypage";
    }
}
