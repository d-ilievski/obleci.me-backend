package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.User;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;

/**
 * Created by Daniel on 06.12.2017.
 */
@Data
public class UserRegistrationBean {

	@JsonProperty("u")
	private String username;

	@JsonProperty("p")
	private String password;

	@JsonProperty("n")
	private String name;

	@JsonProperty("s")
	private String surname;

	@JsonProperty("e")
	@Email
	private String email;
}
