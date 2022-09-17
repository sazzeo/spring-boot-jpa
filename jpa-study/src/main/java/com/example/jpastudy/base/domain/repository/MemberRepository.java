package com.example.jpastudy.base.domain.repository;

import com.example.jpastudy.base.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member , Long> {
}
