package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.response.BrandDetailResponse;
import com.example.dto.response.ResponseData;
import com.example.dto.resquest.BrandRequestDTO;
import com.example.service.BrandService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/brand")
@Tag(name = "Brand Controller")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/")
    public ResponseData<Long> addBrand(@RequestBody BrandRequestDTO request) {
        long brandId = brandService.saveBrand(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Brand created successfully", brandId);
    }

    @GetMapping("/")
    public ResponseData<Page<BrandDetailResponse>> getAllBrands(Pageable pageable) {
        Page<BrandDetailResponse> brands = brandService.getAllBrands(pageable);
        return new ResponseData<>(HttpStatus.OK.value(), "brands", brands);

    }

    @GetMapping("/{brandId}")
    public ResponseData<BrandDetailResponse> getBrandDetails(@PathVariable long brandId) {
        BrandDetailResponse response = brandService.getBrand(brandId);
        return new ResponseData<>(HttpStatus.OK.value(), "brand", response);
    }

    @PutMapping("/{brandId}")
    public ResponseData<Void> updateBrand(@PathVariable long brandId, @RequestBody BrandRequestDTO request) {
        brandService.updateBrand(brandId, request);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Brand updated successfully");
    }

    @DeleteMapping("/{brandId}")
    public ResponseData<Void> deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully");
    }
}
