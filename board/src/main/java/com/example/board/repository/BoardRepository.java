package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.repository.search.SearchBoardRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from Board b left join Reply r On r.board = b where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "Select  b.bno, b.title, b.regDate, w.name, w.email, count(r.rno)"
        + " from Board b "
        + " left join b.writer w "
        + " left join Reply r On r.board = b "
        + " group by b.bno, b.title, b.regDate, w.name, w.email",
        countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

   /* @Query(value = "Select  b, w , count(r.rno)"
        + " from Board b "
        + " left join b.writer w "
        + " left join Reply r On r.board = b "
        + " group by b",
        countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);*/

    @Query(value = "Select  b.title, b.content, b.regDate, b.modDate, w.name, w.email, count(r.rno)"
        + " from Board b left join b.writer w "
        + " left join Reply r On r.board = b "
        + " where b.bno = :bno"
        + " group by b.title, b.content, b.regDate, b.modDate, w.name, w.email")
    Object getBoardByBno(@Param("bno") long bno);


}
