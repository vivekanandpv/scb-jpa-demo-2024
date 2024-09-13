package com.example.jpademo.services;

import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.models.Account;
import com.example.jpademo.models.Customer;
import com.example.jpademo.repositories.AccountRepository;
import com.example.jpademo.repositories.CustomerRepository;
import com.example.jpademo.viewmodels.AccountCreateViewModel;
import com.example.jpademo.viewmodels.AccountUpdateViewModel;
import com.example.jpademo.viewmodels.AccountViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImplementation implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImplementation(
            AccountRepository accountRepository,
            CustomerRepository customerRepository
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<AccountViewModel> getAll() {
        return accountRepository
                .findAll()
                .stream()
                .map(this::toViewModel)
                .toList();
    }

    @Override
    public AccountViewModel getById(int id) {
        return toViewModel(getEntityById(id));
    }

        @Override
        public AccountViewModel create(AccountCreateViewModel viewModel) {
            return toViewModel(accountRepository.saveAndFlush(toEntity(viewModel)));
        }

    @Override
    public AccountViewModel update(int id, AccountUpdateViewModel viewModel) {
        //  find the entity first
        Account entity = getEntityById(id);

        //  copy the properties
        BeanUtils.copyProperties(viewModel, entity);

        //  save and return
        return toViewModel(accountRepository.saveAndFlush(entity));
    }

    @Override
    public void deleteById(int id) {
        accountRepository.delete(getEntityById(id));
    }

    private AccountViewModel toViewModel(Account entity) {
        AccountViewModel viewModel = new AccountViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        return viewModel;
    }

    private Account toEntity(AccountCreateViewModel viewModel) {
        Account entity = new Account();
        BeanUtils.copyProperties(viewModel, entity);

        Customer masterEntity = getParentEntityById(viewModel.getCustomerId());
        entity.setCustomer(masterEntity);

        return entity;
    }

    private Account getEntityById(int id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Could not find the account with id: %d", id)));
    }

    private Customer getParentEntityById(int id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Could not find the customer with id: %d", id)));
    }
}
