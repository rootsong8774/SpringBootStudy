package com.textbook.guestbook.service;

import com.textbook.guestbook.dto.GuestbookDTO;
import com.textbook.guestbook.dto.PageRequestDTO;
import com.textbook.guestbook.dto.PageResultDTO;
import com.textbook.guestbook.entity.Guestbook;
import com.textbook.guestbook.repository.GuestbookRepository;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO---------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        guestbookRepository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = guestbookRepository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (this::entityToDto);
        return new PageResultDTO<>(result,fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {

        Optional<Guestbook> result = guestbookRepository.findById(gno);
        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = guestbookRepository.findById(dto.getGno());
        if (result.isPresent()) {
            Guestbook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            guestbookRepository.save(entity);
        }
    }

    @Override
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }
}
