package me.obleci.dao;

import me.obleci.entity.Advert;
import me.obleci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */
public interface AdvertDao extends JpaRepository<Advert, Long> {

    List<Advert> findAllByUser(User user);

}
