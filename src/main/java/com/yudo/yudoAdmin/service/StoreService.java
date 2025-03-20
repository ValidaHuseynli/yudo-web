package com.yudo.yudoAdmin.service;

import com.yudo.yudoAdmin.dto.StoreRequest;
import com.yudo.yudoAdmin.dto.StoreResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StoreService {

    ResponseEntity<StoreResponse> saveStore(StoreRequest request);

    ResponseEntity<StoreResponse> getStore(String name);

    List<StoreResponse> getAllStore();
}
