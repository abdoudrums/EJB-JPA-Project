package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	}

	@Test
	public void testAddPerson() {
		
		person1.setFirstName("abderahim");
		person1.setLastName("Hachemi");
		person1.setEmail("abderahimhachemi@hotmail.com");
		person1.setBirthDay(new Date(18 / 05 / 1992));
		person1.setWebsite("www.google.com");
		person1.setPassword("toto");
		searchingManager.addPerson(person1);
	}

//	@Test
//	public void showAllPersonsTest() {
//
////		List<Person>myList;
////		myList=searchingManager.showPersons();
////		assertNotNull(myList);
//		
//		Person person3 = new Person("bhachemi", "babderahim", "babdou@hotmail.com", "bgoogle.fr", new Date(), "1b23", null);
//		searchingManager.addPerson(person3);
//		assertNotNull(searchingManager.showAllPersons());
//		assertEquals(1, searchingManager.showAllPersons().size());
//	}

	@Test
	public void SearchingLastNameTest() {
		Person person = new Person("hachemi", "abderahim", "abdou@hotmail.com", "google.fr", new Date(), "123", null);
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