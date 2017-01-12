package com.pingidentity.halifax.fuji.domain;

 
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import java.util.List;

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
 
}