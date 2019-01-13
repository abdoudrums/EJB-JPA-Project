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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Activity;
import entities.Person;

@Stateless(name = "SearchingManager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SearchingManagerImpl implements SearchingManagerI {
	
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
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Person addPerson(Person p) {
		em.persist(p);
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
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity createActivity(Activity activity) {
		em.persist(activity);
		//return this.findActivity(activity)
		return null;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity updateActivity(Activity activity) {
		return em.merge(activity);
	}

	@Override
	public Activity removeActivity(Activity activity) {
		Activity toDeleteActivity = em.find(Activity.class, activity.getIdActivity());
		em.remove(toDeleteActivity);
		return toDeleteActivity;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Person> ViewAllPersons() {
		Query query = em.createQuery("SELECT p FROM Person p");
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

	// recherche par prenom
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
	public List<Person> SearchingPersonByActivityTitle(String title) {
		Query query = em.createQuery("SELECT person FROM Activity a WHERE a.title LIKE'%" + title + "%'");
		
			List<Person> persons = query.getResultList();
			System.out.println("findPersonsByTitle :"+persons.size());
			return persons;
	}

	@Override
	public Activity findActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getIdActivity());
		return foundActivity;
	}

	@Override
	public List<Activity> showActivities(Person p) {
		Query query = null;
		try {
			query = em.createQuery("SELECT a FROM Activity a");
		} catch (NoResultException e) {
			return null;
		}
		if (query != null) {
			return query.getResultList();
		}
		return null;
	}

}
