package com.assignment.temeliobackend.repository;

import com.assignment.temeliobackend.model.NonProfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonProfitRepository extends JpaRepository<NonProfit, Integer> {
}
