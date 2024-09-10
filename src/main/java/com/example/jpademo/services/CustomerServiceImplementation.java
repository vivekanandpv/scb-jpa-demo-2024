package com.example.jpademo.services;

import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.models.Account;
import com.example.jpademo.models.Customer;
import com.example.jpademo.repositories.CustomerRepository;
import com.example.jpademo.viewmodels.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerViewModel> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(this::toViewModel)
                .toList();
    }

    @Override
    public CustomerViewModel getById(int id) {
        return toViewModel(getEntityById(id));
    }

    @Override
    public CustomerViewModel create(CustomerCreateViewModel viewModel) {
        return toViewModel(customerRepository.saveAndFlush(toEntity(viewModel)));
    }

    @Override
    public CustomerViewModel update(int id, CustomerUpdateViewModel viewModel) {
        //  find the entity first
        Customer entity = getEntityById(id);

        //  copy the properties
        BeanUtils.copyProperties(viewModel, entity);

        //  save and return
        return toViewModel(customerRepository.saveAndFlush(entity));
    }

    @Override
    public void deleteById(int id) {
        customerRepository.delete(getEntityById(id));
    }

    private CustomerViewModel toViewModel(Customer entity) {
        CustomerViewModel viewModel = new CustomerViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        viewModel.setAccounts(entity.getAccounts().stream().map(this::toViewModel).toList());
        return viewModel;
    }

    private Customer toEntity(CustomerCreateViewModel viewModel) {
        Customer entity = new Customer();
        BeanUtils.copyProperties(viewModel, entity);
        return entity;
    }

    private Customer getEntityById(int id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Could not find the customer with id: %d", id)));
    }

    private AccountViewModel toViewModel(Account entity) {
        AccountViewModel viewModel = new AccountViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        return viewModel;
    }
}
