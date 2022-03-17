package com.example.springbank.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="B_Customers")
@Getter
@Setter
public class BankCustomer {

    @Id
    @Column(name="cust_jumin")
    private String customerId;
    @Column(name="cust_name")
    private String name;
    @Column(name="cust_addr")
    private String address;
    @Column(name="cust_birth")
    private Date birth;
    @Column(name="cust_email")
    private String email;
    @Column(name="cust_phnum")
    private String phoneNum;
    @Column(name="cust_job")
    private String job;
}
