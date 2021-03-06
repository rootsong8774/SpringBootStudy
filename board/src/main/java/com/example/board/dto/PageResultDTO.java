package com.example.board.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <DTO> Generic Type of DTO
 * @param <EN>  Generic Type of Entity
 */
@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;

    private int totalPage;

    private int page;
    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> pageList;
    /**
     * @param result : Page of entity to be delivered onto view
     * @param fn : Function for converting from entity to dto
     */
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    /**
     * page 관련 데이터를 초기화
     */
    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int) ((Math.ceil(page/10.0))*10);

        start = tempEnd - 9;
        prev = start > 1;
        end = Math.min(totalPage, tempEnd);
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed()
            .collect(Collectors.toList());
    }
}
