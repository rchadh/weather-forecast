package com.weather.forecast.models;

public class Weather extends WeatherEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3697351416270105846L;
	
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}