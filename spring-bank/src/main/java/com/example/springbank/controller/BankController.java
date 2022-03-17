package com.example.springbank.controller;

import com.example.springbank.domain.BankCustomer;
import com.example.springbank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/customerInfo")
public class BankController {

    private BankService bankService;

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", bankService.list());
    }

    @GetMapping("/register")
    public void registerForm(Model model, BankCustomer bankCustomer) {

    }

    @PostMapping("/register")
    public String register(Model model, BankCustomer bankCustomer) {
        bankService.register(bankCustomer);
        model.addAttribute("msg", "고객정보 등록이 완료되었습니다.");
        return "customerInfo/success";
    }

    @GetMapping("/read")
    public void customerInfo(Model model, String customerId) {
        model.addAttribute(bankService.customerInfo(customerId));
    }

    @GetMapping("/modify")
    public void modifyForm(Model model, String customerId) {
        model.addAttribute(bankService.customerInfo(customerId));
    }

    @PostMapping("/modify")
    public String modify(Model model, BankCustomer bankCustomer) {
        bankService.modify(bankCustomer);
        model.addAttribute("msg", "고객정보 수정이 완료되었습니다.");
        return "customerInfo/success";
    }

    @PostMapping("/remove")
    public String remove(Model model, String customerId) {
        bankService.remove(customerId);
        model.addAttribute("msg", "고객정보 삭제가 완료되었습니다.");
        return "customerInfo/success";
    }
}
