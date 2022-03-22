package com.textbook.guestbook.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.textbook.guestbook.entity.Guestbook;
import com.textbook.guestbook.entity.QGuestbook;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Test
    public void testQuery1() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        //when
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        //then
        result.stream().forEach(System.out::println);
    }

    @Test
    public void testQuery2() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        //when
        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(0L));
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        //then
        result.stream().forEach(System.out::println);
    }
}