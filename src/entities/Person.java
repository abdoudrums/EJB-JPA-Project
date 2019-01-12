package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table(name = "Person",
uniqueConstraints = {
   @UniqueConstraint(columnNames = {
      "first_name", "email"
   })})

@Entity(name = "Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;	
	   @Id()
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private long idPerson;
	   

	@Column(name = "first_name", length = 200, nullable = false)
	   private String firstName;
	   
	  
	   @Column(name = "last_name", length = 200, nullable = false)
	   private String lastName;
	   
	   @Column(name = "email", length = 200, nullable = false, unique=true)
	   private String email;
	   
	   @Column(name = "website", length = 200, nullable = false)
	   private String website;
	   
	   
	   @Temporal(TemporalType.DATE)
	   @Column(name = "birth_day")
	   private Date birthDay;
	   
	   @Column(name = "password", length = 200, nullable = false, unique=true)
	   private String password;
	   
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		@JoinTable(name = "Activity")
		private List<Activity> activities;
		
		   public Person() {
			}


		   
	public Person(String firstName, String lastName, String email, String website, Date birthDay, String password,
				List<Activity> activities) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.website = website;
			this.birthDay = birthDay;
			this.password = password;
			this.activities = activities;
		}



	public long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(long idPerson) {
		this.idPerson = idPerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}
