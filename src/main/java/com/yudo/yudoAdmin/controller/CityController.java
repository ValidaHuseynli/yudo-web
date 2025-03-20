package com.yudo.yudoAdmin.controller;

import com.yudo.yudoAdmin.dto.CityRequest;
import com.yudo.yudoAdmin.dto.CityResponse;
import com.yudo.yudoAdmin.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/save")
    public ResponseEntity<CityResponse> saveCity(@RequestBody CityRequest request){
        return cityService.createCity(request);
    }

}
