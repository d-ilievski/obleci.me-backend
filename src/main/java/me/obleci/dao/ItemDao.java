package me.obleci.dao;

import me.obleci.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Daniel on 14.12.2017.
 */
public interface ItemDao extends JpaRepository<Item, Long> {

}
