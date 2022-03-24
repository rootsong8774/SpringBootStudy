package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;

public interface BoardService {

    Long register(BoardDto dto);

    PageResultDTO<BoardDto, Object[]> getList(PageRequestDTO pageRequestDTO);

    default Board dtoToEntity(BoardDto dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder()
            .bno(dto.getBno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(member)
            .build();
    }

    default BoardDto entityToDto(Board board, Member member, Long replyCount) {

        return BoardDto.builder()
            .bno(board.getBno())
            .title(board.getTitle())
            .content(board.getContent())
            .regDate(board.getRegDate())
            .modDate(board.getModDate())
            .writerEmail(member.getEmail())
            .writerName(member.getName())
            .replyCount(replyCount.intValue())
            .build();


    }
}
