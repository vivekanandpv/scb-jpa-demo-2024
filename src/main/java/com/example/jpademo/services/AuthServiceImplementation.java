package com.example.jpademo.services;

import com.example.jpademo.models.*;
import com.example.jpademo.repositories.AppUserRepository;
import com.example.jpademo.utils.AppJwtUtils;
import com.example.jpademo.viewmodels.TokenResponseViewModel;
import com.example.jpademo.viewmodels.*;
import com.example.jpademo.exceptions.*;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImplementation implements AuthService {
    private final AppUserService userService;
    private final AppJwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;

    public AuthServiceImplementation(
            AppUserService userService,
            AppJwtUtils jwtUtils,
            PasswordEncoder passwordEncoder,
            AppUserRepository userRepository
    ) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public TokenResponseViewModel getToken(LoginViewModel viewModel) {
        AppUser user = userRepository.findUserByUsername(viewModel.getUsername())
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(viewModel.getPassword(), user.getPassword())) {
            throw new LoginFailedException();
        }

        String token = jwtUtils.generateToken(userService
                .loadUserByUsername(viewModel.getUsername()));

        return new TokenResponseViewModel(token);
    }

    @Override
    public void register(UserRegisterViewModel viewModel) {
        if (this.userService.usernameExists(viewModel.getUsername())) {
            throw new GeneralAuthenticationException();
        }

        AppUser user = this.userService.createUser(viewModel);
    }

    @Override
    public List<UserListViewModel> getAllUsers() {
        return this.userService
                .getAllUsers()
                .stream()
                .map(u -> {
                    UserListViewModel viewModel = new UserListViewModel();
                    BeanUtils.copyProperties(u, viewModel);
                    viewModel.setRoles(u.getAppRoles().stream().map(AppRole::getRole).collect(Collectors.toList()));

                    return viewModel;
                })
                .sorted(Comparator.comparingInt(UserListViewModel::getAppUserId))
                .collect(Collectors.toList());
    }

    @Override
    public UserListViewModel getUserById(int id) {
        AppUser user = this.userService.findUserById(id)
                .orElseThrow(RecordNotFoundException::new);

        UserListViewModel viewModel = new UserListViewModel();
        BeanUtils.copyProperties(user, viewModel);
        viewModel.setRoles(user.getAppRoles().stream().map(AppRole::getRole).collect(Collectors.toList()));
        return viewModel;
    }
}
