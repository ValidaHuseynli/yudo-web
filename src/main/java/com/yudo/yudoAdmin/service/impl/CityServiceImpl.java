package com.yudo.yudoAdmin.service.impl;

import com.yudo.yudoAdmin.dao.entity.CityEntity;
import com.yudo.yudoAdmin.dao.repository.CityRepository;
import com.yudo.yudoAdmin.dto.CityRequest;
import com.yudo.yudoAdmin.dto.CityResponse;
import com.yudo.yudoAdmin.mapper.CityMapper;
import com.yudo.yudoAdmin.service.CityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);


    @Override
    public ResponseEntity<CityResponse> createCity(CityRequest request) {
        logger.info("ActionLog.saveCity.start: {}", request);
        CityEntity cityEntity = CityMapper.INSTANCE.requestToEntity(request);

        CityEntity save = cityRepository.save(cityEntity);

        CityResponse cityResponse = CityMapper.INSTANCE.entityToResponse(save);

        logger.info("ActionLog.saveCity.start: {}", cityResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponse);
    }
}
