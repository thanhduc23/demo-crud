package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.response.BrandDetailResponse;
import com.example.dto.resquest.BrandRequestDTO;

public interface BrandService {

    long saveBrand(BrandRequestDTO request);

    void updateBrand(long brandId, BrandRequestDTO request);

    void deleteBrand(long brandId);

    BrandDetailResponse getBrand(long brandId);

    Page<BrandDetailResponse> getAllBrands(Pageable pageable);
}
