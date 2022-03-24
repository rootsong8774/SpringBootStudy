package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import java.time.LocalDateTime;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public Long register(BoardDto dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);
        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDto, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDto> fn = (en -> entityToDto(
            Board.builder().bno((Long) en[0]).title(
                (String) en[1]).build(),
            (Member) Member.builder().name((String)en[3]).email((String)en[4]).build(),
            (Long) en[5]));


/*
 entityToDto(Board.builder().bno((Long) en[0]).title(
            (String) en[1]).content((String) en[2]).build())

*/

        Page<Object[]> result = repository.getBoardWithReplyCount(
            pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<BoardDto, Object[]>(result, fn);

    }


}
