package com.nitor.rcteg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nitor.rcteg.entity.Device;

@Service
public class DeviceServiceImpl implements DeviceService {
	private final Logger LOG = LoggerFactory.getLogger("DeviceServiceImpl");

	@Override
	public void deviceLocationUpdate(Device device) throws InterruptedException {
		LOG.info("Device data: {}", device.toString());

		Thread.sleep(3000);

		LOG.info("Device Location Updated!", device.getDeviceId());
	}
}
