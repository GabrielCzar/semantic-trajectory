package com.gabrielczar.semantic.repositories;

import com.gabrielczar.semantic.entities.City;
import com.gabrielczar.semantic.entities.InterestPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "interest-points", collectionResourceRel = "interest_points")
public interface InterestPointRepository extends JpaRepository<InterestPoint, Integer> {

    @RestResource(path = "find-by-city", rel = "find_by_city")
    Page<InterestPoint> findAllByCity_Name(@Param("city") String city, Pageable pageable);

    @RestResource(exported = false)
    Boolean existsByCity(City city);

    @Override @RestResource(exported = false)
    <S extends InterestPoint> List<S> saveAll(Iterable<S> iterable);

    @Override @RestResource(exported = false)
    <S extends InterestPoint> S saveAndFlush(S s);

    @Override @RestResource(exported = false)
    <S extends InterestPoint> S save(S s);
}
