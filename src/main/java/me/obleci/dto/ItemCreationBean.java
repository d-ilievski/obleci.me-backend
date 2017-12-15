package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.Advert;
import me.obleci.entity.Item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Daniel on 14.12.2017.
 */
@Data
public class ItemCreationBean {

	@JsonProperty("n")
	private String name;

	@JsonProperty("d")
	private String description;

	@JsonProperty("a_id")
	private Long advertId;

	@JsonProperty("ip")
	private String imagePath;

	@JsonProperty("u_id")
	@JsonIgnore
	private Long userId;



	public ItemCreationBean() {}

	public ItemCreationBean(Item i) {
		name = i.getName();
		description = i.getDescription();
		advertId = i.getAdvertId();
		imagePath = i.getImagePath();
		userId = i.getUserId();
	}
}
