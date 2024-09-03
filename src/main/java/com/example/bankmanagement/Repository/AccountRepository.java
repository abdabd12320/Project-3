package com.example.bankmanagement.Repository;

import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountById(Integer id);

    List<Account> findAccountByAccountNumber(String accountNumber);

    List<Account> findAllByCustomer(Customer customer);
}
