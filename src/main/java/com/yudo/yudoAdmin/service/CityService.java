package com.yudo.yudoAdmin.service;

import com.yudo.yudoAdmin.dto.CityRequest;
import com.yudo.yudoAdmin.dto.CityResponse;
import org.springframework.http.ResponseEntity;

public interface CityService {

    ResponseEntity<CityResponse> createCity (CityRequest request);

}
