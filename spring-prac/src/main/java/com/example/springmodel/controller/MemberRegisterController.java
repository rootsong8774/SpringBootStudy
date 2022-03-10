package com.example.springmodel.controller;

import com.example.springmodel.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberRegisterController {
    private static final Logger logger = LoggerFactory.getLogger(MemberRegisterController.class);

    @RequestMapping(value = "/registerForm", method = RequestMethod.GET)
    public String registerForm() {
        logger.info("registerForm");

        return "/model/registerForm";
    }

    @RequestMapping(value = "/register01", method = RequestMethod.POST)
    public String register01(String userId) {
        logger.info("register01");

        logger.info("userId = " + userId);

        return "/model/result01";
    }

    @RequestMapping(value = "/register02", method = RequestMethod.POST)
    public String register02(@ModelAttribute("userId") String userId) {
        logger.info("register02");

        logger.info("userId = " + userId);

        return "/model/result02";
    }


    @RequestMapping(value = "/register03", method = RequestMethod.POST)
    public String register03(@ModelAttribute("userId") String userId, @ModelAttribute("password") String password) {
        logger.info("register03");

        logger.info("userId = " + userId);

        logger.info("password = " + password);

        return "/model/result03";
    }

    @RequestMapping(value = "/register04", method = RequestMethod.POST)
    public String register04(Member member) {
        logger.info("register04");

        logger.info("userId = " + member.getUserId());

        logger.info("password = " + member.getPassword());

        return "/model/result04";
    }
}
