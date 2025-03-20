package com.yudo.yudoAdmin.service;

import java.time.LocalDateTime;

public interface AdminService {
    void blockSeller(Long userId);
    void unBlockSeller(Long userId);
    void blockSeller(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    void allowUserToPost(Long userId);
}
