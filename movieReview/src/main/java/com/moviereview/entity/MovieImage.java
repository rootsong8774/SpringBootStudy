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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SequenceGenerator(name = "movie_image_seq", sequenceName = "movie_image_seq", initialValue = 1, allocationSize = 1)
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_image_seq")
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_mno")
    @Exclude
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
