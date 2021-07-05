package com.sisterag.springapp.entity;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7108185341961307550L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column
	private String frist_name;

	@Column
	private String last_name;

	@Column
	private String email;

	@Column
	private String user_name;

	@Column
	private String password;

    @Transient
	private String confirmpassword;

    @ManyToMany
    @JoinTable(name="user_roles",
    	joinColumns=@JoinColumn(name = "user_id"),
    	inverseJoinColumns=@JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrist_name() {
		return frist_name;
	}

	public void setFrist_name(String frist_name) {
		this.frist_name = frist_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", frist_name=" + frist_name + ", last_name=" + last_name + ", email=" + email
				+ ", user_name=" + user_name + ", password=" + password + ", confirmpassword=" + confirmpassword
				+ ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmpassword, email, frist_name, id, last_name, password, roles, user_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(confirmpassword, other.confirmpassword) && Objects.equals(email, other.email)
				&& Objects.equals(frist_name, other.frist_name) && Objects.equals(id, other.id)
				&& Objects.equals(last_name, other.last_name) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles) && Objects.equals(user_name, other.user_name);
	}
	
    
}
