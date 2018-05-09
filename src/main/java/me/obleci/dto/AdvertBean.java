package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.Advert;

import javax.persistence.Column;

/**
 * Created by Daniel on 14.12.2017.
 */
@Data
public class AdvertBean {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("n")
	private String name;

	@JsonProperty("d")
	private String description;

	@JsonProperty("a")
	private String address;

	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("lng")
	private String longitude;

	@JsonProperty("active")
	private Advert.AdvertStatus active;

	public AdvertBean(Advert advert) {
		this.id = advert.getId();
		this.name = advert.getName();
		this.description = advert.getDescription();
		this.address = advert.getAddress();
		this.latitude = advert.getLatitude();
		this.longitude = advert.getLongitude();
		this.active = advert.getAdvertStatus();
	}
}
