package me.obleci.service.implementations;

import me.obleci.dao.UserDao;
import me.obleci.dto.UserBean;
import me.obleci.dto.UserRegistrationBean;
import me.obleci.entity.User;
import me.obleci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 05.12.2017.
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override public List<UserBean> getAllUsers() {

		List<UserBean> list = new ArrayList<>();
		for(User ub : userDao.findAll()) {
			list.add(new UserBean(ub));
		}

		return list;
	}

	@Override public UserBean create(UserRegistrationBean userRegistrationBean) {

		User u = userDao.findByUsername(userRegistrationBean.getUsername());

		if(u == null) {
			u = new User();

			u.setEmail(userRegistrationBean.getEmail());
			u.setName(userRegistrationBean.getName());
			u.setSurname(userRegistrationBean.getSurname());
			u.setPassword(bCryptPasswordEncoder.encode(userRegistrationBean.getPassword()));
			u.setUsername(userRegistrationBean.getUsername());
			u.setUserStatus(User.UserStatus.ACTIVE);
			u.setUserType(User.UserType.REGULAR);

			userDao.save(u);
			return new UserBean(u);
		}

		return null;
	}

}
