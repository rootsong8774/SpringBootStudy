package com.example.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository repository;

    @Test
    public void insertReply() throws Exception {
        IntStream.rangeClosed(1,300).forEach(i->{
            long bno = (long) ((Math.random() * 100) + 1);

            Board board = Board.builder()
                .bno(bno)
                .build();

            Reply reply = Reply.builder()
                .text("Reply..........."+i)
                .board(board)
                .replyer("guest")
                .build();

            repository.save(reply);
        });
    }
    
    @Test
    @Transactional
    public void readReply1() throws Exception {
      //given
        Optional<Reply> result = repository.findById(1L);
        Reply reply = result.get();
              
      //then
        System.out.println("reply = " + reply);
        System.out.println("reply.getBoard() = " + reply.getBoard());
    }
}