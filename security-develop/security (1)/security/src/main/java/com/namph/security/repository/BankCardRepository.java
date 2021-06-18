package com.namph.security.repository;

import com.namph.security.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard,Integer> {
}
