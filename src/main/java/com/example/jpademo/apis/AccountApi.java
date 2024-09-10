package com.example.jpademo.apis;

import com.example.jpademo.services.AccountService;
import com.example.jpademo.viewmodels.AccountUpdateViewModel;
import com.example.jpademo.viewmodels.AccountCreateViewModel;
import com.example.jpademo.viewmodels.AccountViewModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@Tag(name = "Account API", description = "RESTful Web API for Accounts")
public class AccountApi {
    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountViewModel>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountViewModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AccountViewModel> create(@Valid @RequestBody AccountCreateViewModel viewModel) {
        return ResponseEntity.ok(accountService.create(viewModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountViewModel> update(@PathVariable int id, @Valid @RequestBody AccountUpdateViewModel viewModel) {
        return ResponseEntity.ok(accountService.update(id, viewModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
