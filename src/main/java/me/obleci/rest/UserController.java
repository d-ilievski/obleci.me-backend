package me.obleci.rest;

		import me.obleci.dto.UserAuthenticationBean;
		import me.obleci.dto.UserBean;
		import me.obleci.dto.UserRegistrationBean;
		import me.obleci.service.UserService;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.web.bind.annotation.*;

		import javax.validation.Valid;
		import javax.ws.rs.core.Response;
		import java.util.List;

/**
 * Created by Daniel on 05.12.2017.
 */
@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserBean> listAll() {

		return userService.getAllUsers();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseStatus Response register(@RequestBody @Valid UserRegistrationBean userRegistrationBean) {

		UserBean ub = userService.create(userRegistrationBean);

		if (ub != null)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseStatus Response login(@RequestBody @Valid UserAuthenticationBean userAuthenticationBean) {

		if (userAuthenticationBean != null)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseStatus Response logout() {

		if (true)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();

	}

}
