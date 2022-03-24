package com.example.board.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}