package com.boot.spring.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="active")
    private boolean active;
    @Column(name="roles")
    private String roles;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    
    @OneToOne(mappedBy="user",cascade=CascadeType.ALL, orphanRemoval = true)
    private Patient patient; 
    
    @OneToOne(mappedBy="user",cascade=CascadeType.ALL, orphanRemoval = true)
    private Doctor doctor;
    
    @OneToOne(mappedBy="user",cascade=CascadeType.ALL, orphanRemoval = true)
    private Manager manager;

    public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		setRoles("ROLE_MANAGER");
		this.manager = manager;
		manager.setUser(this);
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		setRoles("ROLE_DOCTOR");
		this.doctor = doctor;
		doctor.setUser(this);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
        setRoles("ROLE_USER");
		this.patient = patient;
		patient.setUser(this);
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = true;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
}
