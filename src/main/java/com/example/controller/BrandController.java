package com.example.controller;


import com.example.dto.response.BrandDetailResponse;
import com.example.dto.response.PageResponse;
import com.example.dto.response.ResponseData;
import com.example.dto.resquest.BrandRequestDTO;
import com.example.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<PageResponse> getAllBrands(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PageResponse response = brandService.getAllBrands(pageNo, pageSize);
        return new ResponseData<>(HttpStatus.OK.value(), "brands", response);
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
