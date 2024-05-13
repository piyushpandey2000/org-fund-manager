package com.assignment.temeliobackend.controller;

import com.assignment.temeliobackend.Foundation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foundation")
public class FoundationController {

    @GetMapping("/info")
    public ResponseEntity<Foundation> getInfo() {
        return ResponseEntity.ok(Foundation.instance);
    }
}
