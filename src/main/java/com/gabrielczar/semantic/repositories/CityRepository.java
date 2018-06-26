package com.gabrielczar.semantic.repositories;

import com.gabrielczar.semantic.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Integer> {

    @RestResource(path = "find-by-name", rel = "find_by_name")
    City findByName(@Param("name") String name);
}
