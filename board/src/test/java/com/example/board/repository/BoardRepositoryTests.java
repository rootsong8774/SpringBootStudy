package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import java.util.Arrays;
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

@Transactional
@SpringBootTest
class BoardRepositoryTests {

    @Autowired BoardRepository boardRepository;

    @Test
    public void insertBoard() throws Exception {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                .email("user"+i + "@aaa.com")
                .build();
            Board board = Board.builder()
                .title("Title...."+i)
                .content("Content...."+i)
                .writer(member)
                .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testRead1() throws Exception {
      //given
        Optional<Board> result = boardRepository.findById(100L);
        //when
        Board board = result.get();
      //then
        System.out.println("board = " + board);
        System.out.println("board.getWriter() = " + board.getWriter());
    }

    @Test
    public void testReadWithWriter() throws Exception {
      //given
        Object result = boardRepository.getBoardWithWriter(100L);
        //when
        Object[] arr = (Object[]) result;
      //then
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply() throws Exception {
      //given
        List<Object[]> result = boardRepository.getBoardWithReply(1L);
      //then
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testWithReplyCount() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        //when
        result.get().forEach(row -> {
            System.out.println(Arrays.toString(row));
        });

        //then
    }

    @Test
    public void testRead2() throws Exception {
        //given
        Object result = boardRepository.getBoardByBno(100L);
        //when
        System.out.println(Arrays.toString((Object[]) result));
        //then
    }

    @Test
    public void testSearch1() throws Exception {
        boardRepository.search1();
    }

    @Test
    public void testSearchPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title").ascending());

        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);

    }


}