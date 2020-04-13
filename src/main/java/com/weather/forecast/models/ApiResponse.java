package com.weather.forecast.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1874525725770033887L;
	
	
	String cityName;
	List<WeatherEntry> weatherEntries;
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<WeatherEntry> getWeatherEntries() {
		return weatherEntries;
	}
	public void setWeatherEntries(List<WeatherEntry> weatherEntries) {
		this.weatherEntries = weatherEntries;
	}
	

}
