package com.yudo.yudoAdmin.controller;

import com.yudo.yudoAdmin.dto.StoreRequest;
import com.yudo.yudoAdmin.dto.StoreResponse;
import com.yudo.yudoAdmin.service.impl.StoreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreServiceImpl storeService;

    @PostMapping("/save")
    public ResponseEntity<StoreResponse> saveStore(@RequestBody StoreRequest request){
        return storeService.saveStore(request);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable String name){
        return storeService.getStore(name);
    }

    @GetMapping
    public List<StoreResponse> getStores(){
        return storeService.getAllStore();
    }
}
