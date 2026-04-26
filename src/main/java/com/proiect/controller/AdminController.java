package com.proiect.controller;

import com.proiect.dto.AdminDto;
import com.proiect.dto.CreateAdminRequest;
import com.proiect.model.Admin;
import com.proiect.model.AdminRole;
import com.proiect.repository.AdminRepository;
import com.proiect.repository.RefreshTokenRepository;
import com.proiect.service.impl.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminRepository adminRepository;
    private final AuthService authService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@Valid @RequestBody CreateAdminRequest request) {
        authService.createAdmin(request);
        return ResponseEntity.ok("Admin creat cu succes");
    }

    @GetMapping("/list")
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(a -> new AdminDto(a.getId(), a.getUsername(), a.getRole().name()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin negăsit"));
        refreshTokenRepository.deleteAllByAdmin(admin);
        adminRepository.delete(admin);
        log.warn("Admin șters — id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<AdminDto> updateRole(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin negăsit"));
        admin.setRole(AdminRole.valueOf(body.get("role")));
        adminRepository.save(admin);
        log.info("Rol schimbat — admin id: {}, nou rol: {}", id, body.get("role"));
        return ResponseEntity.ok(new AdminDto(admin.getId(), admin.getUsername(), admin.getRole().name()));
    }
}