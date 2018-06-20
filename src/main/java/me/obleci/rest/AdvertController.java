package me.obleci.rest;

import me.obleci.dto.*;
import me.obleci.entity.Advert;
import me.obleci.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public List<ItemBean> itemsByAd(@PathVariable("id") long id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ItemBean> list = advertService.itemsByAd(id);

        if(list != null)
            return list;
        else
            return null;
    }

    @RequestMapping(value = "kiosk/item/{id}", method = RequestMethod.GET)
    public List<ItemBean> activeItemsByAd(@PathVariable("id") long id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ItemBean> list = advertService.activeItemsByAd(id);

        if(list != null)
            return list;
        else
            return null;
    }

    @RequestMapping(value = "/item/uploadImg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadImg(@RequestParam("file")MultipartFile file, @RequestParam("aId") Long aId) throws IOException {
	    String extension = "";
	    String fileName = ""+aId+"_"+advertService.itemsByAd(aId).size();

        int i = file.getOriginalFilename().lastIndexOf('.');

        if (i > 0) {
            extension = file.getOriginalFilename().substring(i);
            fileName += extension;
        }
	    File convertFile = new File("D:\\obleci.me\\obleci.me-backend\\images\\" + fileName);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("http://127.0.0.1:8887/" + fileName, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/image/{aId}_{iId}", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getImg(@PathVariable("aId") Long aId, @PathVariable("iId") Long iId) throws IOException {

        return "D:\\obleci.me\\obleci.me-backend\\images\\"+aId+"_"+iId+".jpg";
    }

    @RequestMapping(value = "/item/changeStatus", method = RequestMethod.POST)
    public ItemBean itemChangeStatus(@RequestBody @Valid ItemAvailableBean itemAvailableBean) throws IOException {
        ItemBean updated = advertService.itemChangeStatus(itemAvailableBean);
        return updated;
    }

    @RequestMapping(value = "/getStatus", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Object> getStatus(@QueryParam("id") long id) {

        Map<String, Advert.AdvertStatus> entity = new HashMap<>();
        entity.put("status", advertService.getAdStatus(id));

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAds", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Object> getAdsByLocation(@QueryParam("lat") String lat, @QueryParam("lng") String lng, @QueryParam("d") Integer d) {

	    List<AdvertBean> ads = advertService.getAdsByLocation(lat, lng, d);

        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
