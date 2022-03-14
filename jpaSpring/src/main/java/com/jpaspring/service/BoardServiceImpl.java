package com.jpaspring.service;

import com.jpaspring.domain.Board;
import com.jpaspring.repository.BoardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService{


    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public void register(Board board) {
        this.boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Board read(Integer boardNo) {
        return this.boardRepository.getById(boardNo);
    }

    @Override
    @Transactional
    public void modify(Board board) {
        Board boardEntity = this.boardRepository.getById(board.getBoardNo());
        boardEntity.setTitle(board.getTitle());
        boardEntity.setContent(board.getContent());
        boardEntity.setWriter(board.getWriter());
    }

    @Override
    @Transactional
    public void remove(Integer boardNo) {
        this.boardRepository.deleteById(boardNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> list() {
        return this.boardRepository.findAll(Sort.by(Direction.DESC,"boardNo"));
    }
}
