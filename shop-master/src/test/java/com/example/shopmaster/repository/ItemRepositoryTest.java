package com.example.shopmaster.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.shopmaster.constant.ItemSellStatus;
import com.example.shopmaster.entity.Item;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장테스트")
    public void createItemTest() throws Exception {
      //given
        IntStream.rangeClosed(1,100).forEach(i->{
            Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
            Item savedItem = itemRepository.save(item);
            System.out.println(savedItem.toString());
        });
    }

}