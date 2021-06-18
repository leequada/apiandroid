package com.namph.security.service.impl;

import com.namph.security.domain.Loan;
import com.namph.security.domain.MonthlyLoan;
import com.namph.security.exception.CustomGlobalException;
import com.namph.security.repository.LoanRepository;
import com.namph.security.repository.MonthlyRepository;
import com.namph.security.service.MonthlyLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class MonthlyLoanServiceImpl implements MonthlyLoanService {
    @Autowired
    MonthlyRepository monthlyRepository;
    @Autowired
    LoanRepository loanRepository;

    @Transactional
    @Override
    public MonthlyLoan dongLai(Integer id) {
        Optional<MonthlyLoan> monthlyLoan = monthlyRepository.findById(id);
        if(monthlyLoan.isPresent()){
            throw new CustomGlobalException("data in valid");
        }
        monthlyLoan.get().setStatus(1);
        return monthlyRepository.save(monthlyLoan.get());
    }

    @Override
    public Set<MonthlyLoan> getMonthlyLoan(Integer id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent()){
            throw new CustomGlobalException("data in valid");
        }
        return loan.get().getMonthlyLoans();
    }
}

