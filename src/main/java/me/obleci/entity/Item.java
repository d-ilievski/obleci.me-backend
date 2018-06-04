package me.obleci.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 06.12.2017.
 */

@Entity
@Table(name = "items")
@Data
public class Item {

	public enum ItemStatus {
		AVAILABLE, NOT_AVAILABLE, DELETED;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Advert advert;

	@Column(name = "advert_id", insertable = false, updatable = false)
	private Long advertId;

	@Column(name = "image_path")
	private String imagePath;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;

	@Column(name = "status")
	private ItemStatus status;

}
