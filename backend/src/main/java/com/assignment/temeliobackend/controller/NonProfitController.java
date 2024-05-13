package com.assignment.temeliobackend.controller;

import com.assignment.temeliobackend.Constants;
import com.assignment.temeliobackend.model.Email;
import com.assignment.temeliobackend.model.NonProfit;
import com.assignment.temeliobackend.service.NonProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nonprofit")
public class NonProfitController {

    @Autowired
    NonProfitService nonProfitService;

    @GetMapping("/getAll")
    public ResponseEntity<List<NonProfit>> getAll() {
        return ResponseEntity.ok(nonProfitService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<NonProfit> getById(@RequestParam int id) {
        return ResponseEntity.ok(nonProfitService.getById(id));
    }

    @PostMapping("/getEmailForBatch")
    public ResponseEntity<List<Email>> getEmailForBatch(@RequestBody Map<String, List<Integer>> body) {
        List<Integer> ids = body.get(Constants.ID);
        return ResponseEntity.ok(nonProfitService.getEmailForBatch(ids));
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody @Validated NonProfit nonProfit) {
        return ResponseEntity.ok(nonProfitService.add(nonProfit));
    }

    @PostMapping("/addFund")
    public ResponseEntity<?> addFund(@RequestBody Map<String, Object> req) {
        if (!req.containsKey(Constants.ID) || !req.containsKey(Constants.FUND) || !req.containsKey(Constants.EMAIL_CONTENT)) {
            return ResponseEntity.badRequest().build();
        }

        int id = (int) req.getOrDefault("id", 0);
        int fund = (int) req.getOrDefault("fund", 0);
        String emailContent = (String) req.get(Constants.EMAIL_CONTENT);
        return ResponseEntity.ok(nonProfitService.addFund(id, fund, emailContent));
    }

    @DeleteMapping("remove")
    public ResponseEntity<?> removeById(@RequestParam int id) {
        nonProfitService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
