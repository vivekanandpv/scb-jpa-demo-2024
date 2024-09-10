package com.example.jpademo.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewModel {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active;
    private String role;

    private List<AccountViewModel> accounts = new ArrayList<>();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AccountViewModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountViewModel> accounts) {
        this.accounts = accounts;
    }
}
