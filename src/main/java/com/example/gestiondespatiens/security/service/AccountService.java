package com.example.gestiondespatiens.security.service;

import com.example.gestiondespatiens.security.entities.AppRole;
import com.example.gestiondespatiens.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String Role);
    AppUser loadUserByUsername(String username);
}
