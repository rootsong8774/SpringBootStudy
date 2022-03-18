package com.jpaspring.service;

import com.jpaspring.domain.Board;
import java.util.List;

public interface BoardService {

    public void register(Board board) throws Exception ;

    public Board read(Integer boardNo);

    public void modify(Board board) throws Exception;

    public void remove(Integer boardNo);

    public List<Board> list();
}
