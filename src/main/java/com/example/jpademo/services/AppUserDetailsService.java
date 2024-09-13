package com.example.jpademo.services;

import com.example.jpademo.auth.AppUserDetails;
import com.example.jpademo.models.AppRole;
import com.example.jpademo.models.AppUser;
import com.example.jpademo.repositories.AppRoleRepository;
import com.example.jpademo.repositories.AppUserRepository;
import com.example.jpademo.viewmodels.UserRegisterViewModel;
import com.example.jpademo.exceptions.RecordNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements AppUserService {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDetailsService(AppUserRepository userRepository, AppRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserDetails(this.userRepository.findUserByUsername(username)
                .orElseThrow(RecordNotFoundException::new));
    }

    @Override
    public boolean usernameExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public AppUser createUser(UserRegisterViewModel viewModel) {
        AppUser entity = new AppUser();
        BeanUtils.copyProperties(viewModel, entity, "password");

        entity.setAppRoles(
                viewModel
                        .getRoles()
                        .stream()
                        .map(r -> this.roleRepository.findByRole(r).orElseThrow(RecordNotFoundException::new))
                        .collect(Collectors.toSet())
        );

        entity.setPassword(this.passwordEncoder.encode(viewModel.getPassword()));

        return userRepository.saveAndFlush(entity);
    }

    @Override
    public Optional<AppUser> findUser(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<AppUser> findUserById(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Set<AppRole> getRolesByUsername(String username) {
        return this.userRepository.findRolesByUsername(username);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return this.userRepository.findAll();
    }

    private boolean isLoginAllowed(String username) {
        AppUser user = this.findUser(username)
                .orElseThrow(RecordNotFoundException::new);

        //  custom logic
        return user != null;
    }
}
