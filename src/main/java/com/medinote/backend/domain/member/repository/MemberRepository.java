package com.medinote.backend.domain.member.repository;

import com.medinote.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> getMemberByMemberId(Long memberId);
    Optional<Member> getMemberBySocialId(String socialId);
}
