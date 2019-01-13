package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;

import entities.Activity;
import entities.Person;
import services.AuthManagerI;
import services.SearchingManagerI;

public class SearchingTest {
	@EJB
	SearchingManagerI searchingManager;

	@EJB
	AuthManagerI authentification;
	
	Person person1 = new Person();

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
		assertNotNull(searchingManager);
		System.out.println("Hello");
	}

	
	@Test
	public void testAddPerson() {
		Person person1 = new Person("hachemiTest", "abderahimTest", "abderahimhachemi@hotmail.com", "google.fr", new Date(18 / 05 / 1992), "123", null);
		searchingManager.addPerson(person1);
		assertNotNull(person1);
		
	}

	@Test
	public void showAllPersonsTest() {
		int nmbPerson = 3;

		for(int i=1;i<=nmbPerson;i++) {
			Person person = new Person("nomP"+i, "prenomP"+i, "nomP1prenomP"+i+"@hotmail.com", "siteP"+i+".fr", new Date(), "passP"+i, null);
			searchingManager.addPerson(person);
		}
		
		List<Person> persons=searchingManager.ViewAllPersons();
		/*
		 *Affichage de  searchingManager
		 for(Person p : persons)
			System.out.println("first name = "+p.getFirstName());
		*/
		assertTrue(persons.size()>= nmbPerson);
	}

	@Test
	public void findByNameTest() {
		Person person = new Person("hachemi", "abderahim", "abdou@hotmail.com", "google.fr", new Date(), "P123", null);
		searchingManager.addPerson(person);
		List<Person> persons = searchingManager.SearchingPersonLastName(person.getLastName());
		assertNotNull(persons);
		assertEquals(persons.size(), 1);
	}

	@Test
	public void findByFirstNameTest() {
		String firstname = "Chiheb";
		int nmbPerson = 2;
		
		for(int i=1;i<=nmbPerson;i++) {
			Person person = new Person(firstname, "guelmami"+i, "guelmami"+i+"@hotmail.com", "chiheb.fr", new Date(), "A123", null);
			searchingManager.addPerson(person);
		}
		
		List<Person> personsCh = searchingManager.SearchingPersonFirstName(firstname);
		assertEquals(nmbPerson, personsCh.size());
	}
	
	@Test
	public void findByLastName() {
		String lastname = "Guelmami";
		int nmbPerson = 2;
		
		for(int i=1;i<=nmbPerson;i++) {
			Person person = new Person("chiheb"+i, lastname, "chiheb"+i+"@hotmail.com", "chiheb.fr", new Date(), "A123", null);
			searchingManager.addPerson(person);
		}
		
		List<Person> personsG = searchingManager.SearchingPersonLastName(lastname);
		assertEquals(nmbPerson, personsG.size());
	}
	
	@Test
	public void testFindByTitletheSameActivitiesOfPersons(){
	Person person = new Person("chiheuhybuhb", "guel", "rahimhachemi@hotmail.com", "geoogle.fr", new Date(19 / 05 / 1992), "233", null);
	System.out.println("id pp : "+person.getIdPerson());
	Activity activity = new Activity();
	activity.setTitle("Licence Professionnelle");
	activity.setDescription("Technologies et programmation web");
	activity.setNature("nature");
	activity.setYear(new Date(2015));
	activity.setWebSite("www.uca.com");
	
	person.setActivities(Arrays.asList(activity));
	
	
	searchingManager.addPerson(person);

	
	
	List<Person> activitiesFound = searchingManager.SearchingPersonByActivityTitle("Licence Professionnelle");
	assertNotNull(activitiesFound);
	assertEquals(activitiesFound.size(),1);
	}

}