package HamstoryBack.HamstoryBack.controller;

import HamstoryBack.HamstoryBack.domain.Member;
import HamstoryBack.HamstoryBack.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/members/new")
    public Member create(@RequestBody MemberForm form) throws Exception{
        Member member = new Member();
        member.setNickname(form.getNickName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.insertMember(member);

        return member;
    }
}
