package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.Item;

/**
 * Created by Daniel on 14.12.2017.
 */
@Data
public class ItemBean {

	@JsonProperty("id")
	private Long id;

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

	@JsonProperty("s")
	private Item.ItemStatus status;

	public ItemBean() {

	}

	public ItemBean(Item i) {
		id = i.getId();
		name = i.getName();
		description = i.getDescription();
		advertId = i.getAdvertId();
		imagePath = i.getImagePath();
		userId = i.getUserId();
		status = i.getStatus();
	}


}
