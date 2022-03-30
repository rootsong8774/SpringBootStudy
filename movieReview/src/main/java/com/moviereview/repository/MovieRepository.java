package com.moviereview.repository;

import com.moviereview.entity.Movie;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(
        "select m.title,m.mno, mi.inum, mi.uuid, mi.imgName, mi.path, avg(coalesce(r.grade,0) ), count(distinct r)"
            + " from Movie m "
            + " left outer join MovieImage mi on mi.movie = m "
            + " left outer join Review r on r.movie=m "
            + " group by m.title,m.mno, mi.inum, mi.uuid, mi.imgName, mi.path")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m.title,m.mno, mi.inum, mi.uuid, mi.imgName, mi.path, avg(coalesce(r.grade, 0)), count(r)"
        + " from Movie m"
        + " left outer join MovieImage mi on mi.movie = m "
        + " left outer join Review r on r.movie = m "
        + " where m.mno = :mno group by m.title,m.mno, mi.inum, mi.uuid, mi.imgName, mi.path")
    List<Object[]> getMovieWithAll(Long mno);
}
