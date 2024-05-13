package com.assignment.temeliobackend.repository;

import com.assignment.temeliobackend.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
