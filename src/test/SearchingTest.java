package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;
import entities.Person;
import services.SearchingInterface;

public class SearchingTest {
	@EJB
	SearchingInterface searchingManager;

	Person person1 = new Person();

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
		assertNotNull(searchingManager);
		System.out.println("Hello");
	}

//	@Before
//	public SearchingTest() throws Exception {
//		
//		EJBContainer.createEJBContainer().getContext().bind("inject", this);
//		assertNotNull(searchingManager);
//	}

	
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
		Person person2 = new Person("hachemiI", "Aabderahim", "Aabdou@hotmail.com", "Agoogle.fr", new Date(), "A123", null);
		searchingManager.addPerson(person2);
		List<Person> persons = searchingManager.SearchingPersonFirstName(person2.getFirstName());
		assertNotNull(persons);
		assertEquals(1, persons.size());
	}
}