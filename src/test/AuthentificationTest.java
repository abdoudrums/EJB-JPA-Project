package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;

import entities.Person;
import services.AuthManagerI;
import services.SearchingInterface;

public class AuthentificationTest {

	@EJB
	AuthManagerI connectManager;

	@EJB
	SearchingInterface searchingManager;

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
		assertNotNull(searchingManager);
		System.out.println("Hello");
	}

	/*
	 * Creation d'une peronne authentifié Deux personne authentifiee n'ont pas la
	 * meme adresse mail
	 */
	@Test
	public void testAuthentificationDePersonnes() throws Exception {
		String pass = "0000";
		// 1ere personne
		Person person1 = new Person("test", "test", null, "google.fr", new Date(18 / 05 / 1992), null, null);
		person1.setEmail("chihebguelmami@gmail.com");
		person1.setPassword(pass);
		System.out.println(person1);
		searchingManager.addPerson(person1);

		// Test 1ere personne
		Person personConnected = connectManager.connexion(person1.getEmail(), person1.getPassword());
		assertEquals(pass, personConnected.getPassword());

	}

	@Test
	public void testLogout() {
		Person personLoggedIn = new Person("test2", "test2", null, "google.fr", new Date(), null, null);
		personLoggedIn.setEmail("chihebbifrost@gmail.com");
		personLoggedIn.setPassword("0000");

		Person personLoggedOut = new Person("test2", "test2", null, "google.fr", new Date(), null, null);

		connectManager.connexion(personLoggedIn.getEmail(), personLoggedIn.getPassword());
		assertNotNull(connectManager);
		personLoggedOut = connectManager.logout();
		assertNull(personLoggedOut);
	}

}
