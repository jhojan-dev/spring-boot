package com.jmamanig.personal.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmamanig.personal.data.model.PersonalData;

@Repository
public interface PersonalDataRepository extends CrudRepository<PersonalData, Long>{
	
	PersonalData findByPersonId(int personId);

}
