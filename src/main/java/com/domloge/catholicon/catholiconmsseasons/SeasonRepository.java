package com.domloge.catholicon.catholiconmsseasons;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "seasons", path = "seasons")
@CrossOrigin("*")
public interface SeasonRepository extends MongoRepository<Season, Integer> {

    // { teams: { $elemMatch: {teamId: 4} } }
    @Query("{ leagues: { $elemMatch: { divisions: { $elemMatch: { divisionId: ?0 } } } } }")
    Season findSeasonByDivisionId(int divisionId);

}
