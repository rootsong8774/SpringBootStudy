package com.example.springbank.service;

import com.example.springbank.domain.BankCustomer;
import java.util.List;

public interface BankService {

    public List<BankCustomer> list();

    public void register(BankCustomer bankCustomer);

    public void modify(BankCustomer bankCustomer);

    public BankCustomer customerInfo(String customerId);

    public void remove(String customerId);
}
