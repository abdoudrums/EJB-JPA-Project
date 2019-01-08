package services;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.Activity;
import entities.Person;

@Stateful
public class AuthentificationManager implements AuthentificationInterface {
	@PersistenceContext(unitName = "myDatabase")
	private EntityManager em;
	private Person connectPerson = new Person();
	
	@Override
	public Person connexion(String email, String password) throws NoResultException {
		Query query = null;
		query = em.createQuery
				("SELECT accessperson FROM Person accessperson WHERE accessperson.email='"+email+"' AND accessperson.password = '"+password+"'");
		if (query != null) {
			try {
				connectPerson = (Person) query.getSingleResult();
			} catch (Exception e) {
				return null;
			}

			return connectPerson;
		}
		return null;
	}
	
	@Override
	public Person logout() {
		connectPerson = null;
		return connectPerson;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity createActivity(Activity activity) {
		em.persist(activity);
		return this.findActivity(activity);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity updateActivity(Activity activity) {
		return em.merge(activity);
	}

	@Override
	public Activity findActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getIdActivity());
		return foundActivity;
	}

	
	@Override
	public Activity removeActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getIdActivity());
		em.remove(foundActivity);
		return foundActivity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> showActivities(Person person) {
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

	public Person getConnectPerson() {
		return connectPerson;
	}

	public void setConnectPerson(Person connectPerson) {
		this.connectPerson = connectPerson;
	}

	@Override
	public Person showPerson(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.idPerson = :id")
				.setParameter("id", person.getIdPerson());
		if (query.getResultList().size() == 0)
			return null;
		Person shownPerson = (Person) query.getSingleResult();
		return shownPerson;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePerson(Person person) {
		if (em.find(Person.class, person.getIdPerson()) != null)
			em.merge(person);
	}

	@Override
	public void removePerson(Person person) {
		Person foundPerson = em.find(Person.class, person.getIdPerson());
		em.remove(foundPerson);
	}
}
