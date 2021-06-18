package com.namph.security.service;

import com.namph.security.domain.MonthlyLoan;

import java.util.Set;

public interface MonthlyLoanService {
    MonthlyLoan dongLai(Integer id);
    Set<MonthlyLoan> getMonthlyLoan(Integer id);
}
