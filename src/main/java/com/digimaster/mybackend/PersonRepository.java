package com.digimaster.mybackend;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonModel,Integer>{
	PersonModel getPersonModelByName(String name);
	PersonModel getPersonModelByNameAndCity(String name, String city); //getname&city buat diservice dan repository baru dicontroller
	PersonModel getPersonModelByCityAndAge(String city, int age);
	Iterable<PersonModel> getPersonModelByCity(String city);
	@Transactional
	void deletePersonModelByName(String name);
}