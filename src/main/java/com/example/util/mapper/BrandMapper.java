package com.example.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.dto.response.BrandDetailResponse;
import com.example.dto.resquest.BrandRequestDTO;
import com.example.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper extends BaseMapper<Brand, BrandDetailResponse, BrandRequestDTO> {

    @Override
    BrandDetailResponse toDto(Brand entity);

    @Override
    Brand toEntity(BrandRequestDTO request);

    @Override
    void updateEntity(@MappingTarget Brand entity, BrandRequestDTO request);
}
