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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

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
	@NotEmpty(message = "Por favor, agrega tu nombre")
	@Size(max = 30, min = 3, message = "Tamaño inválido")
	private String firstname;

	@Column
	@NotEmpty(message = "Por favor, agrega tu Apellido")
	@Size(max = 30, min = 3, message = "Tamaño inválido")
	private String lastname;

	@Column
	@NotEmpty(message = "Por favor, agrega tu Email")
	@Email
	private String email;

	@Column
	@NotEmpty(message = "Por favor, agrega tu nombre de usuario")
	@Size(max = 15, min = 5, message = "Tamaño inválido")
	private String username;

	@Column
	@NotEmpty(message = "Por favor, agrega tu clave")
	@Size(max = 10, min = 5, message = "Tamaño inválido")
	private String password;

    @Transient
	@NotEmpty(message = "Por favor, confirma tu clave")
	@Size(max = 10, min = 5, message = "Tamaño inválido")
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", confirmpassword=" + confirmpassword
				+ ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmpassword, email, firstname, id, lastname, password, roles, username);
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
				&& Objects.equals(firstname, other.firstname) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
	}
	
    
}
