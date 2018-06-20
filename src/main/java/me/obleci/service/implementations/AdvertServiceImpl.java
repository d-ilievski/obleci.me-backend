package me.obleci.service.implementations;

import me.obleci.dao.AdvertDao;
import me.obleci.dao.ItemDao;
import me.obleci.dao.UserDao;
import me.obleci.dto.*;
import me.obleci.entity.Advert;
import me.obleci.entity.Item;
import me.obleci.entity.User;
import me.obleci.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

	@PersistenceContext
    EntityManager em;

	@Override public AdvertBean create(String username, AdvertCreationBean advertCreationBean) {

		User u = userDao.findByUsername(username);

		Advert advert = new Advert(advertCreationBean);
		advert.setUser(u);
		advert.setAdvertStatus(Advert.AdvertStatus.INACTIVE);

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
			i.setStatus(Item.ItemStatus.AVAILABLE);

            a.setAdvertStatus(Advert.AdvertStatus.ACTIVE);

			itemDao.save(i);
            advertDao.save(a);

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

	@Override
	public List<ItemBean> itemsByAd(long id) {
        List<Item> items = itemDao.findAllByAdvertIdAndStatusIsNot(id, Item.ItemStatus.DELETED);
		List<ItemBean> transformed = new ArrayList<>();

		for(Item i : items) {
			transformed.add(new ItemBean(i));
		}

		return transformed;
	}

	@Override
	public List<ItemBean> activeItemsByAd(long id) {
		List<Item> items = itemDao.findAllByAdvertIdAndStatus(id, Item.ItemStatus.AVAILABLE);
		List<ItemBean> transformed = new ArrayList<>();

		for(Item i : items) {
			transformed.add(new ItemBean(i));
		}

		return transformed;
	}

	@Override
	public ItemBean itemChangeStatus(ItemAvailableBean itemAvailableBean) {

		Item item = itemDao.findOne(itemAvailableBean.getId());
		item.setStatus(itemAvailableBean.getStatus());
        itemDao.save(item);

		//if(itemAvailableBean.getStatus() == Item.ItemStatus.NOT_AVAILABLE || itemAvailableBean.getStatus() == Item.ItemStatus.DELETED) {
		    List<Item> restAvailable = itemDao.findAllByAdvertIdAndStatus(item.getAdvertId(), Item.ItemStatus.AVAILABLE);
            Advert ad = advertDao.findOne(item.getAdvertId());
		    if(restAvailable.size() == 0) {
		        ad.setAdvertStatus(Advert.AdvertStatus.INACTIVE);
            } else {
                ad.setAdvertStatus(Advert.AdvertStatus.ACTIVE);
            }
            advertDao.save(ad);
        //}

		return new ItemBean(item);
	}

    @Override
    public Advert.AdvertStatus getAdStatus(long id) {
        return advertDao.findOne(id).getAdvertStatus();
    }

    @Override
    public List<AdvertBean> getAdsByLocation(String lat, String lng, Integer d) {

	    float latitude = Float.parseFloat(lat);
        float longitude = Float.parseFloat(lng);


        Query q = em.createNativeQuery(
                "SELECT * FROM(\n" +
                "SELECT *,(((acos(sin(("+latitude+"*pi()/180)) * sin((adverts.latitude*pi()/180))+cos(("+latitude+"*pi()/180)) * cos((adverts.latitude*pi()/180)) * cos((("+longitude+" - adverts.longitude)*pi()/180))))*180/pi())*60*1.1515*1.609344) as distance FROM adverts) t\n" +
                "WHERE distance <= "+d+"", Advert.class);

        List<Advert> ads = q.getResultList();
        List<AdvertBean> transformed = new ArrayList<>();

        for(Advert a : ads) {
            transformed.add(new AdvertBean(a));
        }

        return transformed;
    }
}
