package com.nitor.rcteg.service;

import com.nitor.rcteg.entity.Device;

public interface DeviceService {

	void deviceLocationUpdate(Device device) throws InterruptedException;

}
