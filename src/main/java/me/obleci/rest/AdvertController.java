package me.obleci.rest;

		import me.obleci.dto.AdvertCreationBean;
		import me.obleci.dto.AdvertBean;
		import me.obleci.dto.ItemBean;
		import me.obleci.dto.ItemCreationBean;
		import me.obleci.service.AdvertService;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.security.core.context.SecurityContextHolder;
		import org.springframework.web.bind.annotation.RequestBody;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.RequestMethod;
		import org.springframework.web.bind.annotation.RestController;

		import javax.validation.Valid;
        import java.util.List;

/**
 * Created by Daniel on 14.12.2017.
 */

@RestController
@RequestMapping(value = "/ad")
public class AdvertController {

	@Autowired
	private AdvertService advertService;


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public AdvertBean create(@RequestBody @Valid AdvertCreationBean advertCreationBean) {

		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		AdvertBean ab = advertService.create(username, advertCreationBean);

		if(ab != null)
			return ab;
		else
		    return null;
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ResponseEntity addItem(@RequestBody @Valid ItemCreationBean itemCreationBean) {

		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ItemBean ib = advertService.addItem(username, itemCreationBean);

		if(ib != null)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.CONFLICT);
	}

    @RequestMapping(value = "/myAds", method = RequestMethod.GET)
    public List<AdvertBean> myAds() {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<AdvertBean> list = advertService.myAds(username);

        if(list != null)
            return list;
        else
            return null;
    }
}
