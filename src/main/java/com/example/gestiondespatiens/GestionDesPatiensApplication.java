package com.example.gestiondespatiens;

import com.example.gestiondespatiens.entities.Patient;
import com.example.gestiondespatiens.repository.PatientRepository;
import com.example.gestiondespatiens.security.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class GestionDesPatiensApplication implements CommandLineRunner {

    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionDesPatiensApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args ->{
          patientRepository.save(new Patient(null,"mohammed",new Date(),false,243));
          patientRepository.save(new Patient(null,"ayman",new Date(),false,143));
          patientRepository.save(new Patient(null,"zaki",new Date(),true,343));

        };
    }

    @Override
    public void run(String... args) throws Exception {
        //patientRepository.save(new Patient(null,"Mohammed",new Date(),false,123));
        //patientRepository.save(new Patient(null,"kamal",new Date(),true,123));
        //patientRepository.save(new Patient(null,"Imane",new Date(),false,123));

    }

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user3");
            if (u1==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER").build()
                );

            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user4");
            if (u2==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user4").password(passwordEncoder.encode("1234")).roles("USER").build()
                );

            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin1");
            if (u3==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin1").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
                );

        };

    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");


        };
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
