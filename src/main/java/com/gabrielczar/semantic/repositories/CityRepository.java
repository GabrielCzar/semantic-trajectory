package com.gabrielczar.semantic.repositories;

import com.gabrielczar.semantic.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Integer> {

    @RestResource(path = "find-by-name", rel = "find_by_name")
    City findByName(@Param("name") String name);

    @Override @RestResource(exported = false)
    <S extends City> S save(S s);

    @Override @RestResource(exported = false)
    <S extends City> S saveAndFlush(S s);

    @Override @RestResource(exported = false)
    <S extends City> List<S> saveAll(Iterable<S> iterable);
}
