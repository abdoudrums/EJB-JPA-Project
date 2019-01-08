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
	 
//	@Before
//	public SearchingTest() throws Exception {
//		
//		EJBContainer.createEJBContainer().getContext().bind("inject", this);
//		assertNotNull(searchingManager);
//	}

	@Test
	public void testAddPerson() {
//        assertNotNull(searchingManager);
		person1.setFirstName("abdou");
		person1.setLastName("Hachemi");
		person1.setEmail("abderahimhachemi@hotmail.com");
		person1.setBirthDay(new Date(18 / 05 / 1992));
		person1.setWebsite("www.google.com");
		person1.setPassword("toto");
		searchingManager.addPerson(person1);
	}
	
	@Test
	public void showAllPersonsTest() {
		
//		List<Person>myList;
//		myList=searchingManager.showPersons();
//		assertNotNull(myList);
		assertNotNull(searchingManager.showAllPersons());
		assertEquals(1, searchingManager.showAllPersons().size());
	}
	
	@Test
	public void findByNameTest() {
		Person person=new Person();
		person.setLastName("Hachemi");
		List<Person> persons = searchingManager.findbyName(person.getLastName());
		assertNotNull(persons);
		assertEquals(1 ,persons.size());
	}
	
	@Test
	public void findByFirstNameTest() {
		Person person2=new Person();
		person2.setLastName("abdou");
		List<Person> persons1 = searchingManager.findbyFirstName(person2.getFirstName());
		assertNotNull(persons1);
		assertEquals(1 ,persons1.size());
	}
}