package me.obleci;

import me.obleci.security.SecurityConstants;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import me.obleci.security.SecurityConstants.*;

import java.util.UUID;

/**
 * Created by Daniel on 13.12.2017.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Override public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

	}
}
