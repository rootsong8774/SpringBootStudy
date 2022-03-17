package com.example.springbank.repository;

import com.example.springbank.domain.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankCustomer, String> {

}
