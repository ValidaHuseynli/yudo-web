package com.yudo.yudoAdmin.controller;

import com.yudo.yudoAdmin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/block-seller/{userId}")
    public String blockSeller(@PathVariable Long userId) {
            adminService.blockSeller(userId);
            return "Seller " + userId + " has been blocked.";
    }

    @PostMapping("/unblock-seller/{userId}")
    public String unblockSeller(@PathVariable Long userId) {
        adminService.unBlockSeller(userId);
        return "Seller " + userId + " has been unblocked.";
    }

    @PostMapping("/block-seller")
    public ResponseEntity<String> blockSeller(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        try {
            adminService.blockSeller(userId, startDate, endDate);
            return ResponseEntity.ok("Seller " + userId + " has been blocked from "
                    + startDate + " to " + endDate);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/allow-post/{userId}")
    public ResponseEntity<String> allowUserToPost(@PathVariable Long userId) {
        try {
            adminService.allowUserToPost(userId);
            return ResponseEntity.ok("User with ID " + userId + " is now allowed to post.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
