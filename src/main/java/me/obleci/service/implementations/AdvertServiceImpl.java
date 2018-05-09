package me.obleci.service.implementations;

import me.obleci.dao.AdvertDao;
import me.obleci.dao.ItemDao;
import me.obleci.dao.UserDao;
import me.obleci.dto.AdvertCreationBean;
import me.obleci.dto.AdvertBean;
import me.obleci.dto.ItemBean;
import me.obleci.dto.ItemCreationBean;
import me.obleci.entity.Advert;
import me.obleci.entity.Item;
import me.obleci.entity.User;
import me.obleci.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */

@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired UserDao userDao;
	@Autowired AdvertDao advertDao;
	@Autowired ItemDao itemDao;

	@Override public AdvertBean create(String username, AdvertCreationBean advertCreationBean) {

		User u = userDao.findByUsername(username);

		Advert advert = new Advert(advertCreationBean);
		advert.setUser(u);
		advert.setAdvertStatus(Advert.AdvertStatus.ACTIVE);

		try {
			advertDao.save(advert);
			return new AdvertBean(advert);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override public ItemBean addItem(String username, ItemCreationBean itemCreationBean) {

		User u = userDao.findByUsername(username);
		Advert a = advertDao.findOne(itemCreationBean.getAdvertId());

		if(a != null) {
			Item i = new Item();
			i.setAdvert(a);
			i.setDescription(itemCreationBean.getDescription());
			i.setImagePath(itemCreationBean.getImagePath());
			i.setName(itemCreationBean.getName());
			i.setUser(u);

			itemDao.save(i);

			return new ItemBean(i);
		}

		return null;
	}

	@Override
	public List<AdvertBean> myAds(String username) {

		User u = userDao.findByUsername(username);
		List<Advert> adverts = advertDao.findAllByUser(u);
		List<AdvertBean> transformed = new ArrayList<>();

		for(Advert a : adverts) {
			transformed.add(new AdvertBean(a));
		}

		return transformed;
	}
}
