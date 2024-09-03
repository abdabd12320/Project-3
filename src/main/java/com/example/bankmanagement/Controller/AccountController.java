package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v7/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get-all-account")
    public ResponseEntity getAllAccounts()
    {
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }
    @GetMapping("/get-my-account")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(accountService.getMyAccount(user.getId()));
    }
    @PostMapping("/add-account")
    public ResponseEntity addAccount(@AuthenticationPrincipal User user, @Valid@RequestBody Account account)
    {
        accountService.addAccount(user.getId(),account);
        return ResponseEntity.status(200).body("Account added");
    }
    @PutMapping("/update-account/{accountID}")
    public ResponseEntity updateAccount(@PathVariable Integer accountID,@AuthenticationPrincipal User user,@Valid@RequestBody Account account)
    {
        accountService.updateAccount(accountID,user.getId(),account);
        return ResponseEntity.status(200).body("Account updated");
    }
    @DeleteMapping("/delete-account/{accountID}")
    public ResponseEntity deleteAccount(@PathVariable Integer accountID,@AuthenticationPrincipal User user)
    {
        accountService.deleteAccount(accountID,user.getId());
        return ResponseEntity.status(200).body("Account deleted");
    }
    @PutMapping("/turn-active/{id}")
    public ResponseEntity turnActive(@PathVariable Integer id,@AuthenticationPrincipal User user)
    {
        accountService.turnActive(id, user.getId());
        return ResponseEntity.status(200).body("Account turned");
    }
    @GetMapping("/view-my-account-details/{accountID}")
    public ResponseEntity viewMyAccountDetails(@PathVariable Integer accountID,@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(accountService.viewMyAccountDetails(accountID,user.getId()));
    }
    @PutMapping("/withdraw-and-deposit-money/{accountID}/{amount}/{c}")
    public ResponseEntity WithdrawAndDepositMoney(@PathVariable Integer accountID,@PathVariable int amount,@PathVariable char c,@AuthenticationPrincipal User user)
    {
        accountService.WithdrawAndDepositMoney(accountID, user.getId(), amount, c);
        return ResponseEntity.status(200).body("Done Withdraw and Deposit money");
    }
    @PutMapping("/transfer-funds/{fromAccountID}/{toAccountID}/{amount}")
    public ResponseEntity transferFunds(@PathVariable Integer fromAccountID,@PathVariable Integer toAccountID, @AuthenticationPrincipal User user,@PathVariable int amount)
    {
        accountService.transferFunds(fromAccountID,toAccountID, user.getId(),amount);
        return ResponseEntity.status(200).body("Done Transfer Funds");
    }
    @PutMapping("/block-account/{accountID}")
    public ResponseEntity blockAccount(@PathVariable Integer accountID,@AuthenticationPrincipal User user)
    {
        accountService.blockAccount(accountID,user.getId());
        return ResponseEntity.status(200).body("Done block");
    }
}
