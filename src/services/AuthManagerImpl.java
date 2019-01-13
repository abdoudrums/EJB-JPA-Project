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
public class AuthManagerImpl implements AuthManagerI {
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


}
