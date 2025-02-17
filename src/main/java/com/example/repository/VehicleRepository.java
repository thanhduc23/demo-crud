package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT v.* FROM vehicles v " +
           "JOIN brands b ON b.id = v.brand_id " +
           "JOIN users o ON o.id = v.owner_id " +
           "WHERE (:brandId IS NULL OR b.id = :brandId) " +
           "AND (:brandName IS NULL OR b.name ILIKE '%' || CAST(:brandName AS VARCHAR) || '%') " +
           "AND (:manufactureYear IS NULL OR v.manufacture_year = :manufactureYear) " +
           "AND (:minPrice IS NULL OR v.price >= :minPrice) " +
           "AND (:maxPrice IS NULL OR v.price <= :maxPrice) " +
           "AND (:ownerId IS NULL OR o.id = :ownerId) " +
           "AND (:ownerName IS NULL OR o.name ILIKE '%' || CAST(:ownerName AS VARCHAR) || '%')",
           nativeQuery = true)
    Page<Vehicle> searchVehicles(
            @Param("brandId") Long brandId,
            @Param("brandName") String brandName,
            @Param("manufactureYear") Integer manufactureYear,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("ownerId") Long ownerId,
            @Param("ownerName") String ownerName,
            Pageable pageable
    );

    @Query(value = """
        SELECT v.* FROM vehicles v 
        JOIN brands b ON b.id = v.brand_id 
        WHERE (v.price > 10000000 AND b.name LIKE 'S%')
        OR (v.price <= 10000000 AND b.type = 'BUS')
        """, nativeQuery = true)
    Page<Vehicle> findVehiclesByPriceAndBrandCondition(Pageable pageable);
}
