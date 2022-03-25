package com.example.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import java.util.List;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDto dto = BoardDto.builder()
            .title("Test.")
            .content("Test.....")
            .writerEmail("user55@aaa.com")
            .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        //when
        List<BoardDto> result = boardService.getList(pageRequestDTO).getDtoList();
        //then
        for (BoardDto boardDto : result) {
            System.out.println(boardDto);
        }
    }

    @Test
    public void testGet() throws Exception {

        Long bno = 100L;

        System.out.println(boardService.get(bno));

    }

    @Test
    @Transactional
    public void testRemove() throws Exception {
        //given
        Long bno = 2L;
        //when
        boardService.removeWithReplies(bno);
    }

    @Test
    @Transactional
    public void modifyTest() throws Exception {
      //given
      BoardDto boardDto = BoardDto.builder()
          .bno(3L)
          .title("제목 변경")
          .content("내용 변경")
          .build();
      //when
        boardService.modify(boardDto);
      //then
        assertThat(boardService.get(3L).getTitle()).isEqualTo("제목 변경");
    }
}