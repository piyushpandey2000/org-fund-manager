package com.assignment.temeliobackend.service;

import com.assignment.temeliobackend.model.Email;
import com.assignment.temeliobackend.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getAll() {
        return emailRepository.findAll();
    }
}
