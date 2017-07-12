package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * company entity
 * 
 * @author janke
 */
@Entity
@Table(name="company")
public class Company implements Serializable{

	private static final long serialVersionUID = 123L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "id")
	private Integer id;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_key")
	private String companyKey;
	
	@Column(name="company_assess")
	private Double companyAssess;
	
	public Company(){
		
	}
	
	public Company(String companyName, String companyKey) {
		this.companyName = companyName;
		this.companyKey = companyKey;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public  String getCompanyName() {
		return companyName;	
	}
	
	public void setCompanyKey(String companyKey) {
		this.companyKey = companyKey;
	}
	
	public  String getCompanyKey() {
		return companyKey;
	}
	
	public void setCompanyAssess(Double companyAssess) {
		this.companyAssess = companyAssess;
	}
	
	public Double getCompanyAssess() {
		return companyAssess;
	}
	
}
