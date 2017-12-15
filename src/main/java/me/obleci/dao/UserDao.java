package me.obleci.dao;

import me.obleci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel on 05.12.2017.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
