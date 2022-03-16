package com.jdbcspring.dao;

import com.jdbcspring.domain.Board;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Board board) {
        String query = "INSERT INTO board VALUES (board_seq.nextval, ?, ?, ?, SYSTIMESTAMP)";
        jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getWriter());
    }

    public Board read(Integer boardNo) {
        List<Board> results = jdbcTemplate.query(
            "SELECT board_no, title, content, writer, reg_date FROM board where board_no=?",
            getBoardRowMapper(), boardNo);

        return results.isEmpty() ? null : results.get(0);
    }

    public void update(Board board) {
        String query = "UPDATE board SET title = ?, content=? WHERE board_no=?";
        jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getBoardNo());
    }

    public void delete(Integer boardNo) {
        String query = "DELETE from board WHERE board_no=?";
        jdbcTemplate.update(query, boardNo);
    }

    /*public List<Board> list() {
        List<Board> results = jdbcTemplate.query(
            "SELECT board_no, title, content, writer, reg_date FROM board where board_no>0 ORDER BY board_no desc, reg_date desc",
            getBoardRowMapper()
        );
        return results;
    }*/


    private RowMapper<Board> getBoardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();

            board.setBoardNo(rs.getInt("board_no"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setWriter(rs.getString("writer"));
            board.setRegDate(rs.getDate("reg_date"));
            return board;
        };
    }
}
