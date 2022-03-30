package com.moviereview.entity;

import static javax.persistence.FetchType.LAZY;

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
@SequenceGenerator(name = "review_seq", sequenceName = "review_seq", initialValue = 1, allocationSize = 1)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    private Long reviewnum;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_mno")
    @Exclude
    private Movie movie;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_mid")
    @Exclude
    private Member member;

    private int grade;

    private String text;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
