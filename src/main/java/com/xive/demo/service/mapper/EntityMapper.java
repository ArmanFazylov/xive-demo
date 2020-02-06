package com.xive.demo.service.mapper;

/*
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

import java.util.List;


/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
