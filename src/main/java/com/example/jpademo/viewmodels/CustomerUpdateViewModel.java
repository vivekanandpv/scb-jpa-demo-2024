package com.example.jpademo.viewmodels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerUpdateViewModel {
    @NotBlank
    @Size(min = 2, max = 200)
    private String firstName;

    @Size(max = 200)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phone;

    private boolean active;

    public @NotBlank @Size(min = 2, max = 200) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(min = 2, max = 200) String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 200) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(max = 200) String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 10, max = 15) String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank @Size(min = 10, max = 15) String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
