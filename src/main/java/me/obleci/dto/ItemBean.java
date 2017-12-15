package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.obleci.entity.Item;

/**
 * Created by Daniel on 14.12.2017.
 */
public class ItemBean {

	@JsonProperty("n")
	private String name;

	@JsonProperty("d")
	private String description;

	@JsonProperty("a_id")
	private Long advertId;

	@JsonProperty("ip")
	private String imagePath;

	@JsonProperty("u_id")
	private Long userId;

	public ItemBean() {

	}

	public ItemBean(Item i) {
		name = i.getName();
		description = i.getDescription();
		advertId = i.getAdvertId();
		imagePath = i.getImagePath();
		userId = i.getUserId();
	}


}
