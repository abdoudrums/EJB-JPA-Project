package services;

import java.util.List;

import entities.Activity;
import entities.Person;

public interface SearchingInterface {
	
	Person addPerson(Person person);
	Person updatePerson(Person person);
	Person removePerson(long id);
	List<Person> ViewAllPersons();
	Person ViewOnePerson(Person person);
	List<Person> SearchingPersonLastName(String lastname);
	List<Person> SearchingPersonFirstName(String firstname);
	List<Activity> SearchingActivityTitle(String title);

}
