package com.jpaspring.controller;

import com.jpaspring.domain.Board;
import com.jpaspring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/register")
    public void registerForm(Board board, Model model) {
    }

    @PostMapping("/register")
    public String register(Board board, Model model) throws Exception {
        boardService.register(board);
        model.addAttribute("msg", "등록이 완료되었습니다.");
        return "board/success";
    }

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", boardService.list());
    }

    @GetMapping("/read")
    public void read(Integer boardNo, Model model) {
        model.addAttribute(boardService.read(boardNo));
    }

    @PostMapping("remove")
    public String remove(Integer boardNo, Model model) {
        boardService.remove(boardNo);
        model.addAttribute("msg", "삭제가 완료되었습니다.");
        return "board/success";
    }

    @GetMapping("/modify")
    public void modifyForm(Integer boardNo, Model model) {
        model.addAttribute(boardService.read(boardNo));
    }

    @PostMapping("/modify")
    public String modify(Board board, Model model) throws Exception {
        boardService.modify(board);
        model.addAttribute("msg", "수정이 완료되었습니다.");

        return "board/success";
    }
}
