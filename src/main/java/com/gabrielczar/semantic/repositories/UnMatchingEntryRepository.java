package com.gabrielczar.semantic.repositories;

import com.gabrielczar.semantic.entities.UnMatchingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "un-matching-entries", collectionResourceRel = "un_matching_entries")
public interface UnMatchingEntryRepository extends JpaRepository<UnMatchingEntry, Long> {

    @RestResource(path = "find-by-token", rel = "find_by_token")
    List<UnMatchingEntry> findAllByToken(@Param("token") String token);

}
