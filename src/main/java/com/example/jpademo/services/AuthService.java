package com.example.jpademo.services;

import com.example.jpademo.viewmodels.LoginViewModel;
import com.example.jpademo.viewmodels.TokenResponseViewModel;
import com.example.jpademo.viewmodels.UserListViewModel;
import com.example.jpademo.viewmodels.UserRegisterViewModel;

import java.util.List;

public interface AuthService {
    TokenResponseViewModel getToken(LoginViewModel viewModel);
    void register(UserRegisterViewModel viewModel);
    List<UserListViewModel> getAllUsers();
    UserListViewModel getUserById(int id);
}