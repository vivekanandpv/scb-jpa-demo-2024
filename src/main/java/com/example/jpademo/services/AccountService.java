package com.example.jpademo.services;

import com.example.jpademo.viewmodels.AccountCreateViewModel;
import com.example.jpademo.viewmodels.AccountUpdateViewModel;
import com.example.jpademo.viewmodels.AccountViewModel;

import java.util.List;

public interface AccountService {
    List<AccountViewModel> getAll();
    AccountViewModel getById(int id);
    AccountViewModel create(AccountCreateViewModel viewModel);
    AccountViewModel update(int id, AccountUpdateViewModel viewModel);
    void deleteById(int id);
}
