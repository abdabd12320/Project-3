package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Repository.AccountRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }
    public List<Account> getMyAccount(Integer id)
    {
        return accountRepository.findAllByCustomer(customerRepository.findCustomerById(id));
    }

    public void addAccount(Integer userID,Account account)
    {
        Customer c = customerRepository.findCustomerById(userID);
        if(c == null)
        {
            throw new ApiException("Customer not found");
        }
        account.setCustomer(c);
        accountRepository.save(account);
        if(userID != account.getCustomer().getId())
        {
            accountRepository.delete(account);
            throw new ApiException("Not match");
        }
        customerRepository.save(c);
    }
    public void updateAccount(Integer accountID,Integer customerID,Account account)
    {
        Account a = accountRepository.findAccountById(accountID);
        if(a == null)
        {
            throw new ApiException("Account not found");
        }
        if(customerRepository.findCustomerById(customerID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(customerID != a.getCustomer().getId())
        {
            throw new ApiException("Not match");
        }
        a.setAccountNumber(account.getAccountNumber());
        a.setBalance(account.getBalance());
        a.setCustomer(customerRepository.findCustomerById(customerID));
        accountRepository.save(a);
    }
    public void deleteAccount(Integer accountID,Integer customerID)
    {
        if(accountRepository.findAccountById(accountID) == null)
        {
            throw new ApiException("Account not found");
        }
        if(customerRepository.findCustomerById(customerID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(customerID != accountRepository.findAccountById(accountID).getCustomer().getId())
        {
            throw new ApiException("Not match");
        }
        accountRepository.delete(accountRepository.findAccountById(accountID));
    }
    public void turnActive(Integer accountID, Integer userID)
    {
        Account a = accountRepository.findAccountById(accountID);
        if(a == null)
        {
            throw new ApiException("Account not found");
        }
        Customer c = customerRepository.findCustomerById(userID);
        if(c == null)
        {
            throw new ApiException("Customer not found");
        }
        if(userID != a.getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        a.setActive(true);
        a.setCustomer(c);
        accountRepository.save(a);
    }
    public Account viewMyAccountDetails(Integer accountID, Integer userID)
    {
        if(accountRepository.findAccountById(accountID) == null)
        {
            throw new ApiException("Account not found");
        }
        if(customerRepository.findCustomerById(userID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(userID != accountRepository.findAccountById(accountID).getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        return accountRepository.findAccountById(accountID);
    }
    public void WithdrawAndDepositMoney(Integer accountID, Integer userID,int amount,char c)
    {
        Account a = accountRepository.findAccountById(accountID);
        if(a == null)
        {
            throw new ApiException("Account not found");
        }
        if(customerRepository.findCustomerById(userID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(userID != accountRepository.findAccountById(accountID).getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        if(!a.isActive())
        {
            throw new ApiException("The account not turned");
        }
        if(c == 'D')
        {
            a.setBalance(a.getBalance() + amount);
            a.setCustomer(customerRepository.findCustomerById(userID));
            accountRepository.save(a);
        }
        else if(c == 'W')
        {
            a.setBalance(a.getBalance() - amount);
            a.setCustomer(customerRepository.findCustomerById(userID));
            accountRepository.save(a);
        }
        else throw new ApiException("please only W or D");
    }
    public void transferFunds(Integer fromAccountID,Integer toAccountID, Integer userID,int amount)
    {
        Account a1 = accountRepository.findAccountById(fromAccountID);
        Account a2 = accountRepository.findAccountById(toAccountID);
        if(a1 == null)
        {
            throw new ApiException("Account not found");
        }
        if(a2 == null)
        {
            throw new ApiException("Account not found");
        }
        if(customerRepository.findCustomerById(userID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(userID != accountRepository.findAccountById(fromAccountID).getCustomer().getId() && userID != accountRepository.findAccountById(toAccountID).getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        if(!a1.isActive() || !a2.isActive())
        {
            throw new ApiException("The account not turned");
        }
        a1.setBalance(a1.getBalance() - amount);
        a2.setBalance(a2.getBalance() + amount);
        a1.setCustomer(customerRepository.findCustomerById(userID));
        a2.setCustomer(customerRepository.findCustomerById(userID));
        accountRepository.save(a1);
        accountRepository.save(a2);
    }
    public void blockAccount(Integer accountID, Integer userID)
    {
        Account a = accountRepository.findAccountById(accountID);
        if(a == null)
        {
            throw new ApiException("Account not found");
        }
        Customer c = customerRepository.findCustomerById(userID);
        if(c == null)
        {
            throw new ApiException("Customer not found");
        }
        if(userID != a.getCustomer().getUser().getId())
        {
            throw new ApiException("not match");
        }
        a.setActive(false);
        a.setCustomer(c);
        accountRepository.save(a);
    }
}
