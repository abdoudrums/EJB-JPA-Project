package services;

import java.util.List;

import entities.Activity;
import entities.Person;

public interface SearchingManagerI {
	// acces person
	Person addPerson(Person person);
	Person updatePerson(Person person);
	Person removePerson(long id);
	//acces activite
	Activity createActivity(Activity activity);
	Activity updateActivity(Activity activity);
	Activity removeActivity(Activity activity);
	//Searching
	List<Person> ViewAllPersons();
	Person ViewOnePerson(Person person);
	List<Person> SearchingPersonLastName(String lastname);
	List<Person> SearchingPersonFirstName(String firstname);
	List<Person> SearchingPersonByActivityTitle(String title);
	Activity findActivity(Activity activity);
	List<Activity> showActivities(Person p);
}
