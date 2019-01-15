package com.domloge.catholicon.catholiconmsseasons;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "seasons", path = "seasons")
@CrossOrigin("*")
public interface SeasonRepository extends PagingAndSortingRepository<Season, Integer> {

}
