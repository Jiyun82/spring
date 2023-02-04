package com.example.study.service;

import com.example.study.repository.MemberRepository;
import com.example.study.domain.Member;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getSid();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복되는 아이디 입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberSid){
        return memberRepository.findBySid(memberSid);
    }

    public Optional<Member> findById(String id){
        return memberRepository.findById(id);
    }

    //맞으면 true
    public boolean checkPassword(String id, String password){
        Member member = memberRepository.findById(id).get();
        String rightPw = member.getPassword();
        if(rightPw.equals(password)){
            return true;
        }
        return false;
    }
}
