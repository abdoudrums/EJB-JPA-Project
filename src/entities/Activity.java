package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

@Entity
@Table(name = "Activity")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	private Integer idActivity;

	@Temporal(TemporalType.DATE)
	private Date year;
	
    @Column(name="title", nullable = false)
	private String title;
	
    @Column(name="nature", nullable = false)
	private String nature;
    
    @Column(name="description", length = 2000, nullable = false)
	private String description;
	
    @Column(name="webSite")
    private String webSite;

    @ManyToOne 
    @JoinColumn(name = "Person_id", referencedColumnName = "id") 
    private Person person;
    
    
    	
	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Integer getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Integer idActivity) {
		this.idActivity = idActivity;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
   
}
