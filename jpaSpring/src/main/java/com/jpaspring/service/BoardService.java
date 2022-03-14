package com.jpaspring.service;

import com.jpaspring.domain.Board;
import java.util.List;

public interface BoardService {

    public void register(Board board) ;

    public Board read(Integer boardNo);

    public void modify(Board board);

    public void remove(Integer boardNo);

    public List<Board> list();
}
