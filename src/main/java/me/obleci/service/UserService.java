package me.obleci.service;

import me.obleci.dto.UserBean;
import me.obleci.dto.UserRegistrationBean;

import java.util.List;

/**
 * Created by Daniel on 05.12.2017.
 */
public interface UserService {

	List<UserBean> getAllUsers();

	UserBean create(UserRegistrationBean userRegistrationBean);


}
