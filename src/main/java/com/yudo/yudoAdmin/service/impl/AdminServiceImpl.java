package com.yudo.yudoAdmin.service.impl;

import com.yudo.yudoAdmin.dao.entity.UserEntity;
import com.yudo.yudoAdmin.dao.repository.UserRepository;
import com.yudo.yudoAdmin.enums.UserStatus;
import com.yudo.yudoAdmin.exception.NotFoundException;
import com.yudo.yudoAdmin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;


    @Override
    public void blockSeller(Long userId) {

        UserEntity seller = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Account is not found for id: " + userId));

        if (seller.getRole() != UserStatus.SELLER) {
            throw new RuntimeException("User is not a seller!");
        }

        seller.setSellerBlocked(true);
        userRepository.save(seller);
    }

    @Override
    public void unBlockSeller(Long userId) {
        UserEntity seller = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Account is not found for id: " + userId));

        if (seller.getRole() != UserStatus.SELLER) {
            throw new RuntimeException("User is not a seller!");
        }

        seller.setSellerBlocked(false);
        userRepository.save(seller);
    }

    @Override
    public void blockSeller(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        UserEntity seller = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Account is not found for id: " + userId));

        if (seller.getRole() != UserStatus.SELLER) {
            throw new RuntimeException("User is not a seller!");
        }
        if (endDate.isBefore(startDate)) {
            throw new RuntimeException("End date must be after start date.");
        }

        seller.setBlockedFrom(startDate);
        seller.setBlockedUntil(endDate);
        userRepository.save(seller);
    }

    @Override
    public void allowUserToPost(Long userId) {
        UserEntity seller = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Account is not found for id: " + userId));
        seller.setCanPost(true);
        userRepository.save(seller);
    }
}
