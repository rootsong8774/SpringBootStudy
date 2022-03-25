package com.example.board.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="board_seq_gen", sequenceName = "board_seq", initialValue = 1, allocationSize = 1)
@ToString
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name= "email")
    @Exclude
    private Member writer;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }


}
