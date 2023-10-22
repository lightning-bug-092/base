package com.hius.mapper;

import org.mapstruct.Mapper;

public interface EntityMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
