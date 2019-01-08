package services;

import java.util.List;

import entities.Activity;
import entities.Person;


public interface AuthentificationInterface {
	
	Person connexion(String email, String password);
	Person logout();
	Activity createActivity(Activity activity);
	Activity updateActivity(Activity activity);
	Activity findActivity(Activity activity);
	Activity removeActivity(Activity activity);
	List<Activity> showActivities(Person p);
	Person showPerson(Person person);
	void updatePerson(Person person);
	void removePerson(Person person);
}
