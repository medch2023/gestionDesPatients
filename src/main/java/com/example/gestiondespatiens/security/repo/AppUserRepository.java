package com.example.gestiondespatiens.security.repo;

import com.example.gestiondespatiens.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser , String> {
    AppUser findByUsername(String username);
}
