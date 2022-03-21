package com.textbook.guestbook.repository;

import com.textbook.guestbook.entity.Memo;
import java.util.List;
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
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    @Transactional
    public void testClass() throws Exception {

        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() throws Exception {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample...." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() throws Exception {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("==============================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    /*@Test
    @Transactional
    public void testSelect2() throws Exception {
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("======================");
        System.out.println(memo);
    }*/

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        Long mno = 1L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("-------------------------------");

        System.out.println("result.getTotalPages() = " + result.getTotalPages());
        System.out.println("result.getTotalElements() = " + result.getTotalElements());
        System.out.println("result.getNumber() = " + result.getNumber());
        System.out.println("result.getSize() = " + result.getSize());
        System.out.println("result.hasNext() = " + result.hasNext());
        System.out.println("result.isFirst() = " + result.isFirst());

        System.out.println("-------------------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() throws Exception {
        Sort sort1 = Sort.by("mno").descending(); //내림 차순 정렬

        Pageable pageable = PageRequest.of(0, 10, sort1); //내림 차순 정렬 후 페이징

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(System.out::println);
    }

    @Test
    public void testQueryMethods() throws Exception {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L,
            80L);

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(System.out::println);
    }
}