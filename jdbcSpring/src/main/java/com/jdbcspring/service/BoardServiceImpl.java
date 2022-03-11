package com.jdbcspring.service;

import com.jdbcspring.dao.BoardDAO;
import com.jdbcspring.domain.Board;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public void register(Board board) {
        boardDAO.create(board);
    }

    @Override
    public Board read(Integer boardNo) {
        return boardDAO.read(boardNo);
    }

    @Override
    public void modify(Board board) {
        boardDAO.update(board);
    }

    @Override
    public void remove(Integer boardNo) {
        boardDAO.delete(boardNo);
    }

    @Override
    public List<Board> list() {
        return boardDAO.list();
    }
}
