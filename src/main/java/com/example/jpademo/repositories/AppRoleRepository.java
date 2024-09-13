package com.example.jpademo.repositories;

import com.example.jpademo.models.AppRole;
import com.example.jpademo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    Optional<AppRole> findByRole(String role);

    @Query("SELECT role.users FROM AppRole role WHERE role.role = ?1")
    List<AppUser> findUsersByRole(String claim);
}
