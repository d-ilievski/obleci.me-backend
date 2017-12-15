package me.obleci.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Daniel on 05.12.2017.
 */
@Entity
@Table(name = "users")
@Data
public class User {

	public enum UserType{
		REGULAR,
		ADMIN
	}

	public enum UserStatus{
		ACTIVE,
		BANNED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "type")
	private UserType userType;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private UserStatus userStatus;

}
