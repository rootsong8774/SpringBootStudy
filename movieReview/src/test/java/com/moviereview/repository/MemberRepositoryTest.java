package com.moviereview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.moviereview.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() throws Exception {
      //given
      Long mid = 1L;
        Member member = Member.builder()
            .mid(mid)
            .build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }
}