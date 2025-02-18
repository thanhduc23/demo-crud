package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.response.BrandDetailResponse;
import com.example.dto.resquest.BrandRequestDTO;
import com.example.model.Brand;
import com.example.repository.BrandRepository;
import com.example.service.BrandService;
import com.example.util.mapper.BrandMapper;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public long saveBrand(BrandRequestDTO request) {
        Brand brand = brandMapper.toEntity(request);
        Brand savedBrand = brandRepository.save(brand);
        return savedBrand.getId();
    }

    @Override
    public void updateBrand(long brandId, BrandRequestDTO request) {
        Brand brand = getBrandById(brandId);
        brandMapper.updateEntity(brand, request);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public BrandDetailResponse getBrand(long brandId) {
        Brand brand = getBrandById(brandId);
        return brandMapper.toDto(brand);
    }

    @Override
    public Page<BrandDetailResponse> getAllBrands(Pageable pageable) {
        Page<Brand> page = brandRepository.findAll(pageable);
        return brandMapper.toDtoPage(page);

    }

    private Brand getBrandById(long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }
}
