package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Daniel on 07.12.2017.
 */
@Data
public class UserAuthenticationBean {

	@JsonProperty(value = "u")
	private String username;

	@JsonProperty(value = "p")
	private String password;
}
