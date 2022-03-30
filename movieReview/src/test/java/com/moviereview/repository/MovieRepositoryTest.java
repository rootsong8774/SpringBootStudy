package com.moviereview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.moviereview.entity.Movie;
import com.moviereview.entity.MovieImage;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Test
    @Commit
    @Transactional
    public void insertMovies() throws Exception {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie...." + i).build();
            System.out.println("---------------------------------");
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .movie(movie)
                    .imgName("test" + j + ".jpg")
                    .build();

                imageRepository.save(movieImage);
            }
            System.out.println("=================================");
        });
    }
}