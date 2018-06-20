package me.obleci.service;

import me.obleci.dto.*;
import me.obleci.entity.Advert;

import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */
public interface AdvertService {

	AdvertBean create(String username, AdvertCreationBean advertCreationBean);
	ItemBean addItem(String username, ItemCreationBean itemCreationBean);
	List<AdvertBean> myAds(String username);

    List<ItemBean> itemsByAd(long id);
	List<ItemBean> activeItemsByAd(long id);

	ItemBean itemChangeStatus(ItemAvailableBean itemAvailableBean);

	Advert.AdvertStatus getAdStatus(long id);

    List<AdvertBean> getAdsByLocation(String lat, String lng, Integer d);
}
