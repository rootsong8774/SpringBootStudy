package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;
import java.time.LocalDateTime;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
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
            Board.builder()
                .bno((Long) en[0])
                .title((String) en[1])
                .regDate((LocalDateTime) en[2])
                .build(),
            (Member) Member.builder()
                .name((String) en[3])
                .email((String) en[4])
                .build(),
            (Long) en[5]));

        /*Page<Object[]> result = repository.getBoardWithReplyCount(
            pageRequestDTO.getPageable(Sort.by("bno").descending()));*/
        Page<Object[]> result = repository.searchPage(pageRequestDTO.getType(),
            pageRequestDTO.getKeyword(),
            pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);

    }

    @Override
    public BoardDto get(Long bno) {
        Object[] result = (Object[]) repository.getBoardByBno(bno);
        return  entityToDto(
            Board.builder()
                .bno(bno)
                .title((String) result[0])
                .content((String) result[1])
                .regDate((LocalDateTime) result[2])
                .modDate((LocalDateTime) result[3])
                .build(),
            (Member) Member.builder()
                .name((String) result[4])
                .email((String) result[5])
                .build(),
            (Long) result[6]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        System.out.println("----------------");
        repository.deleteById(bno);
    }

    @Override
    public void modify(BoardDto boardDto) {

        Board board = repository.getOne(boardDto.getBno());

        board.changeTitle(boardDto.getTitle());
        board.changeContent(boardDto.getContent());

        repository.save(board);
    }


}
