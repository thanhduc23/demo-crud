package com.example.service.impl;


import com.example.dto.response.BrandDetailResponse;
import com.example.dto.response.PageResponse;
import com.example.dto.resquest.BrandRequestDTO;
import com.example.model.Brand;
import com.example.repository.BrandRepository;
import com.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public long saveBrand(BrandRequestDTO request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setType(request.getType());
        
        Brand savedBrand = brandRepository.save(brand);
        return savedBrand.getId();
    }

    @Override
    public void updateBrand(long brandId, BrandRequestDTO request) {
        Brand brand = getBrandById(brandId);
        brand.setName(request.getName());
        brand.setType(request.getType());
        
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public BrandDetailResponse getBrand(long brandId) {
        Brand brand = getBrandById(brandId);
        return buildBrandResponse(brand);
    }

    @Override
    public PageResponse getAllBrands(int pageNo, int pageSize) {
        Page<Brand> page = brandRepository.findAll(PageRequest.of(pageNo, pageSize));
        
        List<BrandDetailResponse> list = page.stream()
                .map(this::buildBrandResponse)
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private Brand getBrandById(long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    private BrandDetailResponse buildBrandResponse(Brand brand) {
        return BrandDetailResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .type(brand.getType())
                .build();
    }
}
