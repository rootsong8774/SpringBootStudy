package com.textbook.guestbook.service;

import static org.junit.jupiter.api.Assertions.*;

import com.textbook.guestbook.dto.GuestbookDTO;
import com.textbook.guestbook.dto.PageRequestDTO;
import com.textbook.guestbook.dto.PageResultDTO;
import com.textbook.guestbook.entity.Guestbook;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class GuestbookServiceTest {

    @Autowired GuestbookService service;

    @Test
    @Transactional
    public void testRegister() throws Exception {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
            .title("Sample Title....")
            .content("Sample Content....")
            .writer("user0")
            .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() throws Exception {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        List<GuestbookDTO> dtoList = resultDTO.getDtoList();

        System.out.println("Prev = " + resultDTO.isPrev());
        System.out.println("Next = " + resultDTO.isNext());
        System.out.println("Total = " + resultDTO.getTotalPage());
        System.out.println("----------------------------------------");
        for (GuestbookDTO guestbookDTO : dtoList) {
            System.out.println(guestbookDTO);
        }
        System.out.println("----------------------------------------");
        resultDTO.getDtoList().forEach(System.out::println);
    }
}