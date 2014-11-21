package ouc.jeep.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="manager")
public class Manager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 112566463154065449L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="user_name",length=32)
    private String userName;
    
    @Column(name="age")
    private Integer age;
    
    @Column(name="nice_name",length=32)
    private String niceName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	
	public Manager() {
		super();
	}

	public Manager(Integer id, String userName, Integer age, String niceName) {
		super();
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.niceName = niceName;
	}
}

