package com.yudo.yudoAdmin.mapper;

import com.yudo.yudoAdmin.dao.entity.CityEntity;
import com.yudo.yudoAdmin.dto.CityRequest;
import com.yudo.yudoAdmin.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CityMapper {
    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    public abstract CityEntity requestToEntity(CityRequest request);
    public abstract CityResponse entityToResponse(CityEntity entity);
}
