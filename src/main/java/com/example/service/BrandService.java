package com.example.service;

import com.example.dto.response.BrandDetailResponse;
import com.example.dto.response.PageResponse;
import com.example.dto.resquest.BrandRequestDTO;

public interface BrandService {
    long saveBrand(BrandRequestDTO request);
    void updateBrand(long brandId, BrandRequestDTO request);
    void deleteBrand(long brandId);
    BrandDetailResponse getBrand(long brandId);
    PageResponse getAllBrands(int pageNo, int pageSize);
}
    