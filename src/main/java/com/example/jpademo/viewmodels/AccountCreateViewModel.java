package com.example.jpademo.viewmodels;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountCreateViewModel {
    @Min(value = 1)
    private int accountType;

    @Min(value = 1)
    private long accountNumber;

    @NotBlank
    @Size(min = 3, max = 100)
    private String accountBranch;

    @Min(value = 0)
    private double accountBalance;

    @Min(value = 1)
    private int customerId;

    @Min(value = 1)
    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(@Min(value = 1) int accountType) {
        this.accountType = accountType;
    }

    @Min(value = 1)
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@Min(value = 1) long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotBlank @Size(min = 3, max = 100) String getAccountBranch() {
        return accountBranch;
    }

    public void setAccountBranch(@NotBlank @Size(min = 3, max = 100) String accountBranch) {
        this.accountBranch = accountBranch;
    }

    @Min(value = 0)
    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(@Min(value = 0) double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Min(value = 1)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@Min(value = 1) int customerId) {
        this.customerId = customerId;
    }
}
