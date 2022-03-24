package com.example.board.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@SequenceGenerator(name = "reply_seq_gen", sequenceName = "reply_seq", initialValue = 1, allocationSize = 1)
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_gen")
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "bno")
    @Exclude
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }
}
