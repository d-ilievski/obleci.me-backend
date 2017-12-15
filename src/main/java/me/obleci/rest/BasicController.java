package me.obleci.rest;

import me.obleci.dao.UserDao;
import me.obleci.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Daniel on 05.12.2017.
 */
@RestController
public class BasicController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index() {

		User u = userDao.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "Hello "+ u.getName() + " " + u.getSurname() +"!";
	}

}
