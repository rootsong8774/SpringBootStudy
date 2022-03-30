package com.moviereview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.moviereview.entity.Movie;
import com.moviereview.entity.MovieImage;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    @Test
    public void testListPage() throws Exception {
      //given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.DESC, "mno"));
        //when
        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        //then
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll() throws Exception {
      //given
        List<Object[]> result = movieRepository.getMovieWithAll(94L);
      //then
        System.out.println(result);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

    }
}