package com.example.jpademo.services;

import com.example.jpademo.viewmodels.CustomerCreateViewModel;
import com.example.jpademo.viewmodels.CustomerUpdateViewModel;
import com.example.jpademo.viewmodels.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    List<CustomerViewModel> getAll();
    CustomerViewModel getById(int id);
    CustomerViewModel create(CustomerCreateViewModel viewModel);
    CustomerViewModel update(int id, CustomerUpdateViewModel viewModel);
    void deleteById(int id);
}
