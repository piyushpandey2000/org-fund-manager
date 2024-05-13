package com.assignment.temeliobackend.controller;

import com.assignment.temeliobackend.model.Email;
import com.assignment.temeliobackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/getAll")
    public List<Email> getAll() {
        return emailService.getAll();
    }
}
