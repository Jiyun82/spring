package com.example.study.repository;

import com.example.study.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findBySid(Long sid) {
        Member member = em.find(Member.class, sid);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findById(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
