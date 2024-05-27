package com.example.gestiondespatiens.security.repo;

import com.example.gestiondespatiens.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRipository extends JpaRepository<AppRole , String> {

}
