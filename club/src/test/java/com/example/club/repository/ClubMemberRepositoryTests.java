package com.example.club.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.club.entity.ClubMember;
import com.example.club.entity.ClubMemberRole;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ClubMemberRepositoryTests {

    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() throws Exception {
        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                .email("user"+i+"@example.com")
                .name("사용자"+i)
                .fromSocial(false)
                .password(passwordEncoder.encode("1111"))
                .build();

            clubMember.addMemberRole(ClubMemberRole.USER);

            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            clubMemberRepository.save(clubMember);

        });
    }

    @Test
    public void testRead() throws Exception {
      //given
        Optional<ClubMember> result = clubMemberRepository.findByEmail("user95@example.com",
            false);
        //when
        ClubMember clubMember = result.get();
        //then
        System.out.println("clubMember = " + clubMember);
    }
}