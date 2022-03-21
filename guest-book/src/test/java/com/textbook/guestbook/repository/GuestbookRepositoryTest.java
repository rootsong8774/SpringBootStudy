package com.textbook.guestbook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.textbook.guestbook.entity.Guestbook;
import java.util.Optional;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() throws Exception {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                .title("Title...." + i)
                .content("Content...." + i)
                .writer("user" + (i % 10))
                .build();
            guestbookRepository.save(guestbook);
        });

    }

    @Test
    @Transactional
    public void updateTest() throws Exception {
        //given
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        //when
        if (result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title....");
            guestbook.changeContent("Changed Content....");

            guestbookRepository.save(guestbook);

            System.out.println("guestbook.getTitle() = " + guestbook.getTitle());
            System.out.println("guestbook.getContent() = " + guestbook.getContent());
        }
        //then
        assertThat(
                guestbookRepository.findById(300L).map(Guestbook::getTitle).orElse(null))
            .isEqualTo("Changed Title....");
        assertThat(
            guestbookRepository.findById(300L).map(Guestbook::getContent).orElse(null)).isEqualTo(
            "Changed Content....");
    }
}