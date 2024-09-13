package com.example.jpademo.apis;

import com.example.jpademo.services.AuthService;
import com.example.jpademo.viewmodels.LoginViewModel;
import com.example.jpademo.viewmodels.TokenResponseViewModel;
import com.example.jpademo.viewmodels.UserListViewModel;
import com.example.jpademo.viewmodels.UserRegisterViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthApi {
    private final AuthService authService;


    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponseViewModel> login(@Valid @RequestBody LoginViewModel viewModel) {
        return ResponseEntity.ok(authService.getToken(viewModel));
    }


    @PostMapping(value = "/register")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterViewModel viewModel) {
        authService.register(viewModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<UserListViewModel>> getUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping(value = "/users/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UserListViewModel> getUser(@PathVariable int id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }
}

