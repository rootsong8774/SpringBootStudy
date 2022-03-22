package com.textbook.guestbook.service;

import com.textbook.guestbook.dto.GuestbookDTO;
import com.textbook.guestbook.dto.PageRequestDTO;
import com.textbook.guestbook.dto.PageResultDTO;
import com.textbook.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    void modify(GuestbookDTO dto);

    void remove(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        return Guestbook.builder()
            .gno(dto.getGno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(dto.getWriter())
            .build();
    }

    default GuestbookDTO entityToDto(Guestbook entity) {

        return GuestbookDTO.builder()
            .gno(entity.getGno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .writer(entity.getWriter())
            .regDate(entity.getRegDate())
            .modDate(entity.getModDate())
            .build();
    }
}
