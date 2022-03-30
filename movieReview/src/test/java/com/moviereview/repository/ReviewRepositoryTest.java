package com.moviereview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.moviereview.entity.Member;
import com.moviereview.entity.Movie;
import com.moviereview.entity.Review;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

    @Test
    public void insertMovieReviews() throws Exception {
        IntStream.rangeClosed(1,200).forEach(i -> {
            Long mno = (long)((Math.random() * 100)) + 1;

            Long mid =((long)((Math.random() * 100)) + 1);
            Member member = Member.builder()
                .mid(mid)
                .build();

            Review movieReview = Review.builder()
                .member(member)
                .movie(Movie.builder().mno(mno).build())
                .grade((int) (Math.random() * 5) + 1)
                .text("이 영화에 대한 느낌..." + i)
                .build();

            repository.save(movieReview);

        });
    }

    @Test
    public void testGetMovieReviews() throws Exception {
      //given
        Movie movie = Movie.builder()
            .mno(92L)
            .build();
        //when
        List<Review> result = repository.findByMovie(movie);
        //then
        result.forEach(movieReview -> {
            System.out.print(movieReview.getReviewnum());
            System.out.print("\t"+movieReview.getGrade());
            System.out.print("\t"+movieReview.getText());
            System.out.print("\t"+movieReview.getMember().getEmail());
            System.out.println("-------------------------------------------");
        });
    }
}