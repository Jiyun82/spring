package com.example.study;

import com.example.study.repository.*;
import com.example.study.service.MemberService;
import com.example.study.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    public SpringConfig(MemberRepository memberRepository, PostRepository postRepository){
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository);
    }
}
