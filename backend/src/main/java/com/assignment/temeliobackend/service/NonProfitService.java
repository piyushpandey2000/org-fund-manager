package com.assignment.temeliobackend.service;

import com.assignment.temeliobackend.Foundation;
import com.assignment.temeliobackend.exception.EntityNotFoundException;
import com.assignment.temeliobackend.model.Email;
import com.assignment.temeliobackend.model.NonProfit;
import com.assignment.temeliobackend.repository.EmailRepository;
import com.assignment.temeliobackend.repository.NonProfitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NonProfitService {
    private final NonProfitRepository nonProfitRepository;
    private final EmailRepository emailRepository;

    public NonProfitService(NonProfitRepository nonProfitRepository, EmailRepository emailRepository) {
        this.nonProfitRepository = nonProfitRepository;
        this.emailRepository = emailRepository;
    }

    public List<NonProfit> getAll() {
        return nonProfitRepository.findAll();
    }

    public NonProfit getById(int id) {
        return nonProfitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Non profit with id=%d doesn't exist", id)));
    }

    public int add(NonProfit nonProfit) {
        return nonProfitRepository.save(nonProfit).getId();
    }

    public NonProfit addFund(int id, int fundAmount, String emailContent) {
        NonProfit nonProfit = getById(id);
        Foundation.instance.transferFund(fundAmount);
        emailContent = Email.getEmailContentToSave(emailContent, nonProfit, fundAmount);
        Email email = emailRepository.save(new Email(null, emailContent, nonProfit));
        nonProfit.addFund(fundAmount, email);
        return nonProfitRepository.save(nonProfit);
    }

    public void removeById(int id) {
        nonProfitRepository.deleteById(id);
    }

    public List<Email> getEmailForBatch(List<Integer> ids) {
        return nonProfitRepository.findAllById(ids).stream()
                .flatMap(nonProfit -> nonProfit.getEmailList().stream())
                .collect(Collectors.toList());
    }
}
