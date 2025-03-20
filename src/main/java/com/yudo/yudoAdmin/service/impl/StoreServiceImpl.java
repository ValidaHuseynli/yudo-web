package com.yudo.yudoAdmin.service.impl;

import com.yudo.yudoAdmin.dao.entity.Store;
import com.yudo.yudoAdmin.dao.repository.StoreRepository;
import com.yudo.yudoAdmin.dto.StoreRequest;
import com.yudo.yudoAdmin.dto.StoreResponse;
import com.yudo.yudoAdmin.exception.NotFoundException;
import com.yudo.yudoAdmin.mapper.StoreMapper;
import com.yudo.yudoAdmin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public ResponseEntity<StoreResponse> saveStore(StoreRequest request) {
        logger.info("ActionLog.saveStore.start: {}", request);

        Store store = StoreMapper.INSTANCE.requestToEntity(request);
        Store save = storeRepository.save(store);
        StoreResponse storeResponse = StoreMapper.INSTANCE.entityToResponse(save);

        logger.info("ActionLog.saveStore.end: {}", storeResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
    }

    @Override
    public ResponseEntity<StoreResponse> getStore(String name) {
        logger.info("ActionLog.getStore.start: {}", name);

        Store store = storeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Store is not found for name: " + name));

        StoreResponse storeResponse = StoreMapper.INSTANCE.entityToResponse(store);

        logger.info("ActionLog.getStore.end: {}", storeResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
    }

    @Override
    public List<StoreResponse> getAllStore() {
        logger.info("ActionLog.getAllStore.start");
        List<Store> all = storeRepository.findAll();

        List<StoreResponse> storeResponses = StoreMapper.INSTANCE.entitiesToResponse(all);
        logger.info("ActionLog.getAllStore.end: {}", storeResponses);
        return storeResponses;
    }
}
