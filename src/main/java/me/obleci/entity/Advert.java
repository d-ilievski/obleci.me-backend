package me.obleci.entity;

import lombok.Data;
import me.obleci.dto.AdvertCreationBean;

import javax.persistence.*;

/**
 * Created by Daniel on 06.12.2017.
 */
@Entity
@Table(name = "adverts")
@Data
public class Advert {

	public enum AdvertStatus {
		ACTIVE,
		INACTIVE,
		REMOVED
	}

	public Advert(AdvertCreationBean advertCreationBean) {
		this.address = advertCreationBean.getAddress();
		this.description = advertCreationBean.getDescription();
		this.name = advertCreationBean.getName();
		this.latitude = advertCreationBean.getLatitude();
		this.longitude = advertCreationBean.getLongitude();
	}

	public Advert() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;

	@Column(name = "status")
	private AdvertStatus advertStatus;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	// TODO Implement location
}
