package com.example.jpademo.apis;

import com.example.jpademo.services.CustomerService;
import com.example.jpademo.viewmodels.CustomerCreateViewModel;
import com.example.jpademo.viewmodels.CustomerUpdateViewModel;
import com.example.jpademo.viewmodels.CustomerViewModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@Tag(name = "Customer API", description = "RESTful Web API for Customer")
public class CustomerApi {
    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerViewModel>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerViewModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerViewModel> create(@Valid @RequestBody CustomerCreateViewModel viewModel) {
        return ResponseEntity.ok(customerService.create(viewModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerViewModel> update(@PathVariable int id, @Valid @RequestBody CustomerUpdateViewModel viewModel) {
        return ResponseEntity.ok(customerService.update(id, viewModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
