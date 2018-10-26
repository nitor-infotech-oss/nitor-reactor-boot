package com.nitor.rcteg.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitor.rcteg.entity.Device;
import com.nitor.rcteg.service.DeviceService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class EventHandler implements Consumer<Event<Device>> {

	private final Logger LOG = LoggerFactory.getLogger("EventHandler");
	
	private final DeviceService deviceService;

	@Autowired
	public EventHandler(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public void accept(Event<Device> t) {
		LOG.info("recieved event");
		Device d = t.getData();
		try {
			deviceService.deviceLocationUpdate(d);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

}
