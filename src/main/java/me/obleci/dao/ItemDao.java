package me.obleci.dao;

import me.obleci.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */
public interface ItemDao extends JpaRepository<Item, Long> {
    List<Item> findAllByAdvertId(Long advertId);
    List<Item> findAllByAdvertIdAndStatus(Long advertId, Item.ItemStatus status);
    List<Item> findAllByAdvertIdAndStatusIsNot(Long advertId, Item.ItemStatus status);
}
