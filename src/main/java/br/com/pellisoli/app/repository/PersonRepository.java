package br.com.pellisoli.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.pellisoli.app.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, Long>{
	
	PersonModel findById(long id);
	
}   
