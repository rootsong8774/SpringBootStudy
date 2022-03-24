package com.example.board.repository;

import com.example.board.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() throws Exception {
        IntStream.rangeClosed(1,100).boxed().forEach(i -> {
            Member member = Member.builder()
                .email("user"+i + "@aaa.com")
                .password("pw"+(i*1324))
                .name("USER"+i)
                .build();
            memberRepository.save(member);
        });


    }
}