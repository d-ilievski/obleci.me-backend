package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.User;
import org.hibernate.validator.constraints.Email;

import javax.ejb.EJB;

/**
 * Created by Daniel on 05.12.2017.
 */
@Data
public class UserBean {

	@JsonProperty(value = "u")
	private String username;

	@JsonProperty("n")
	private String name;

	@JsonProperty("s")
	private String surname;

	@JsonProperty("e")
	@Email
	private String email;

	public UserBean(User u) {

		this.username = u.getUsername();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.email = u.getEmail();
	}
}
