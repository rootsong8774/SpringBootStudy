package com.example.shopmaster.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.shopmaster.MemberFormDTO;
import com.example.shopmaster.entity.Member;
import com.example.shopmaster.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    public PasswordEncoder passwordEncoder;
    public Member createMember() {
        MemberFormDTO memberFormDTO = MemberFormDTO.builder()
            .email("test@test.com")
            .name("aaa")
            .address("sss")
            .password("1111")
            .build();
        return Member.createMember(memberFormDTO, passwordEncoder);
    }

    @Test
    public void saveDuplicateMemberTest() throws Exception {
      //given
        Member member1 = this.createMember();
        Member member2 = this.createMember();
        //when
        memberService.saveMember(member1);
        Throwable e = assertThrows(IllegalStateException.class,
            () -> {
                memberService.saveMember(member2);
            });
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
        //then
    }
}