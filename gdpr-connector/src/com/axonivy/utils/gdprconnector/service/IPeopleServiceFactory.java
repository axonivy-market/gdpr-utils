package com.axonivy.utils.gdprconnector.service;

public abstract class IPeopleServiceFactory <T extends Object, U extends Object> {
	public abstract IPeopleService<T, U> getService();
}
