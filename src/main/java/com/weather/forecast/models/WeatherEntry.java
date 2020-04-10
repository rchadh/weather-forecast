package com.weather.forecast.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1946606549082807706L;

	private Instant timestamp;

	private double min_temperature;
	
		private double max_temperature;

	private String weatherMain;


	@JsonProperty("timestamp")
	public Instant getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long unixTime) {
		this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		this.min_temperature = Double.parseDouble(main.get("temp_min").toString());
		this.max_temperature = Double.parseDouble(main.get("temp_max").toString());
	}
	
	public double getMin_temperature() {
		return min_temperature;
	}

	
	public double getMax_temperature() {
		return max_temperature;
	}

	public String getWeatherMain() {
		return this.weatherMain;
	}

	public void setWeatherMain(String weatherMain) {
		this.weatherMain = weatherMain;
	}

	
	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherMain((String) weather.get("main"));
	}

}