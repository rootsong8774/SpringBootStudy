package com.example.board.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {

    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long bno;
    private String title;
    private String content;
    private String writerEmail;
    private String writerName;
    private int replyCount;
}
