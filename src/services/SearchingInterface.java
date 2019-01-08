package services;

import java.util.List;

import entities.Activity;
import entities.Person;

public interface SearchingInterface {
	
	Person addPerson(Person person);
	List<Person> showAllPersons();
	Person showPerson(Person person);
	List<Person> findbyName(String lastname);
	List<Person> findbyFirstName(String firstname);
	List<Activity> findByTitle(String title);

}
