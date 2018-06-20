package me.obleci.security;

import java.util.UUID;

/**
 * Created by Daniel on 07.12.2017.
 */
public class SecurityConstants {

	public static final String SECRET = UUID.randomUUID().toString();
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/register";
	public static final String HOME_URL = "/";
	public static final String LOCAL_ADS = "/ad/getAds";
	public static final String KIOSK_ITEMS_BY_AD = "/ad/kiosk/item/{id}";

}