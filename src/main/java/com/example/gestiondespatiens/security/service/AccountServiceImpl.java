package com.example.gestiondespatiens.security.service;

import com.example.gestiondespatiens.security.entities.AppRole;
import com.example.gestiondespatiens.security.entities.AppUser;
import com.example.gestiondespatiens.security.repo.AppRoleRipository;
import com.example.gestiondespatiens.security.repo.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private AppUserRepository appUserRepository;
    private AppRoleRipository appRoleRipository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("This user already exists");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Password not match");
        appUser=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedApp = appUserRepository.save(appUser);

        return savedApp;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRipository.findById(role).orElse(null);
        if (appRole!=null) throw new RuntimeException("This alredy exists");
        appRole=AppRole.builder()
                .role(role)
                .build();
        return appRoleRipository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRipository.findById(role).get();
        appUser.getRoles().add(appRole);
        //appUserRepository.save(appUser);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRipository.findById(role).get();
        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
