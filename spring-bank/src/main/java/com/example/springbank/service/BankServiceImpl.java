package com.example.springbank.service;

import com.example.springbank.domain.BankCustomer;
import com.example.springbank.repository.BankRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class BankServiceImpl implements BankService{

    private BankRepository bankRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BankCustomer> list() {
        return bankRepository.findAll();
    }

    @Override
    public void register(BankCustomer bankCustomer) {
        bankRepository.save(bankCustomer);
    }

    @Override
    public void modify(BankCustomer bankCustomer) {
        BankCustomer customerEntity = this.bankRepository.getById(bankCustomer.getCustomerId());
        customerEntity.setAddress(bankCustomer.getAddress());
        customerEntity.setBirth(bankCustomer.getBirth());
        customerEntity.setEmail(bankCustomer.getEmail());
        customerEntity.setPhoneNum(bankCustomer.getPhoneNum());
        customerEntity.setJob(bankCustomer.getJob());
    }

    @Override
    @Transactional(readOnly = true)
    public BankCustomer customerInfo(String customerId) {
        return bankRepository.getById(customerId);
    }

    @Override
    public void remove(String customerId) {
        bankRepository.deleteById(customerId);
    }
}
