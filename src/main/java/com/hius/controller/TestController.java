package com.hius.controller;

import com.hius.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String test(){
        accountRepository.findAll();
        return "123";
    }
}
