package com.proiect;

import com.proiect.model.Admin;
import com.proiect.model.AdminRole;
import com.proiect.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JudoMoldovaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudoMoldovaApplication.class, args);
    }

    @Bean
    public CommandLineRunner createSuperAdmin(AdminRepository adminRepository,
                                              PasswordEncoder passwordEncoder) {
        return args -> {
            if (!adminRepository.existsByUsername("superadmin")) {
                Admin admin = Admin.builder()
                        .username("superadmin")
                        .password(passwordEncoder.encode("SuperAdmin123"))
                        .role(AdminRole.SUPER_ADMIN)
                        .build();
                adminRepository.save(admin);
                System.out.println("=Superadmin creat cu succes=");
            }
        };
    }
}
