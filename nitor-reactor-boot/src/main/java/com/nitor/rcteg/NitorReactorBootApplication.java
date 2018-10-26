package com.nitor.rcteg;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nitor.rcteg.entity.Device;
import com.nitor.rcteg.handler.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.bus.Event;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
public class NitorReactorBootApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger("NitorReactorBootApplication");
	private final EventBus eventBus;
	private final EventHandler eventHandler;

	@Autowired
	public NitorReactorBootApplication(EventBus eventBus, EventHandler eventHandler) {
		super();
		this.eventBus = eventBus;
		this.eventHandler = eventHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(NitorReactorBootApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		eventBus.on($("eventHandler"), eventHandler);
		// Publish messages here
		for (int i = 0; i < 10; i++) {
			Device device = new Device();
			device.setDeviceId(String.valueOf(i));
			device.setDeviceName(String.valueOf(i).concat("th Device"));
			eventBus.notify("eventHandler", Event.wrap(device));
			LOG.info("Published Device number {}.", i);
		}

	}
}
