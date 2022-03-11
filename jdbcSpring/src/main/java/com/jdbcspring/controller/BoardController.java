package com.jdbcspring.controller;

import com.jdbcspring.domain.Board;
import com.jdbcspring.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger("BoardController.class");

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerForm(Board board, Model model) {
        logger.info("registerForm");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Board board, Model model) {
        logger.info("register");

        boardService.register(board);

        model.addAttribute("msg", "등록이 완료되었습니다.");

        return "board/success";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) {
        logger.info("list");

        model.addAttribute("list", boardService.list());

    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(int boardNo, Model model) {
        logger.info("read");

        model.addAttribute(boardService.read(boardNo));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(int boardNo, Model model) {
        logger.info("remove");
        boardService.remove(boardNo);

        model.addAttribute("msg", "삭제가 완료되었습니다.");
        return "/board/success";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyForm(int boardNo, Model model) {
        logger.info("modifyForm");

        model.addAttribute(boardService.read(boardNo));

    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Board board, Model model) {
        logger.info("modify");

        boardService.modify(board);
        model.addAttribute("msg", "수정이 완료되었습니다.");

        return "board/success";
    }
}
