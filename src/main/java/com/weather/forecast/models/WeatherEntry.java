package com.weather.forecast.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1946606549082807706L;

	private Instant timestamp;

	private double min_temperature;
	
	private double max_temperature;

	private String weatherMain;
	
	private String suggestion;
	
	private String date;


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

	
	
	public void setMin_temperature(double minTemp) {
		this.min_temperature=minTemp;
	}

	public void setMax_temperature(double maxTemp) {
		this.max_temperature=maxTemp;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}