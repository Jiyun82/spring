package com.example.study.repository;

import com.example.study.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findBySid(Long sid);
    Optional<Member> findById(String id);
    List<Member> findAll();
}
