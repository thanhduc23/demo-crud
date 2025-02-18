package com.example.util.mapper;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

public interface BaseMapper<E, D, R> {

    // Entity to DTO
    D toDto(E entity);

    // Request to Entity
    E toEntity(R request);

    // Update entity from request
    void updateEntity(@MappingTarget E entity, R request);

    // Map page of entities to page of DTOs 
    default Page<D> toDtoPage(Page<E> entities) {
        return entities.map(this::toDto);
    }
}
