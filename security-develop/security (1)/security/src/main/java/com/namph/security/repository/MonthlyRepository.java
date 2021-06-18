package com.namph.security.repository;

import com.namph.security.domain.MonthlyLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRepository extends JpaRepository<MonthlyLoan,Integer> {
}
