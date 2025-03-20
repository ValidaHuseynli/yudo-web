package com.yudo.yudoAdmin.mapper;

import com.yudo.yudoAdmin.dao.entity.Store;
import com.yudo.yudoAdmin.dto.StoreRequest;
import com.yudo.yudoAdmin.dto.StoreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class StoreMapper {
    public static final StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    public abstract Store requestToEntity(StoreRequest request);
    public abstract StoreResponse entityToResponse(Store request);
    public abstract List<StoreResponse> entitiesToResponse(List<Store> request);

}
