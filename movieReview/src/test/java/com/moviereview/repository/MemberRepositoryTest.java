package com.moviereview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.moviereview.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember() throws Exception {

        IntStream.rangeClosed(1,100).forEach(i-> {
            Member member = Member.builder()
                .email("r" + i + "@aaa.com")
                .pw("a" + (1674 * i))
                .nickname("reviewer" + i)
                .build();

            memberRepository.save(member);
        });
    }
}