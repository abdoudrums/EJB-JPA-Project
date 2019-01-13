package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Activity;
import entities.Person;

@Stateless(name = "SearchingManager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SearchingManager implements SearchingInterface {

	@PersistenceContext(unitName = "myDatabase")
	private EntityManager em;

	@PostConstruct()
	public void start() {
		System.out.println("Starting " + this);
	}

	@PreDestroy
	public void end() {
		System.out.println("Stopping " + this);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Person addPerson(Person p) {
		em.persist(p);
		System.err.println("addPerson with  id =" + p.getIdPerson());
		System.err.println("addPerson witdh firstName=" + p.getFirstName());
		return (p);
	}
	
	@Override
    public Person updatePerson(Person p) {
   	 	em.merge(p);
        return(p);
   	}
	
	@Override
    public Person removePerson(long id) {
   	 Person person = em.find(Person.class,id);
   	 em.remove(person);
   	 System.err.println("Delete  id =" + person.getIdPerson());
   	 return person; 
   		}
	
	

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> ViewAllPersons() {
		Query query = em.createQuery("SELECT *FROM Person");
		return query.getResultList();

	}

	@Override
	public Person ViewOnePerson(Person person) {
		return em.find(Person.class, person.getIdPerson());
	}

	// recherche par nom
	@Override
	public List<Person> SearchingPersonLastName(String lastname) {
		Query query = em.createQuery("SELECT lastName FROM Person p WHERE p.lastName LIKE '%" + lastname + "%'");
		if (query.getResultList().size() == 0)
			return null;
		@SuppressWarnings("unchecked")
		List<Person> Persons = (List<Person>) query.getResultList();
		return Persons;
	}

	// recherche par prénom
	@Override
	public List<Person> SearchingPersonFirstName(String firstname) {
		Query query = em.createQuery("SELECT firstName FROM Person p WHERE p.firstName LIKE '%" + firstname + "%'");
		if (query.getResultList().size() == 0)
			return null;
		@SuppressWarnings("unchecked")
		List<Person> Persons = (List<Person>) query.getResultList();
		return Persons;
	}

	// recherche par titre d'activity
	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> SearchingActivityTitle(String title) {
		Query query = em.createQuery("SELECT title FROM Activity a WHERE a.title LIKE'%" + title + "%'");
		;
		if (query.getResultList().size() == 0)
			return null;
		List<Activity> Activities = (List<Activity>) query.getResultList();
		return Activities;
	}
}
