package me.obleci.service;

import me.obleci.dto.AdvertCreationBean;
import me.obleci.dto.AdvertBean;
import me.obleci.dto.ItemBean;
import me.obleci.dto.ItemCreationBean;

import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */
public interface AdvertService {

	AdvertBean create(String username, AdvertCreationBean advertCreationBean);
	ItemBean addItem(String username, ItemCreationBean itemCreationBean);
	List<AdvertBean> myAds(String username);
}
