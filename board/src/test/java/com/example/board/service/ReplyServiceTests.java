package com.example.board.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.board.dto.ReplyDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    void testGetList() {

        Long bno = 10L;

        List<ReplyDTO> result = replyService.getList(bno);

        result.forEach(System.out::println);
    }
}