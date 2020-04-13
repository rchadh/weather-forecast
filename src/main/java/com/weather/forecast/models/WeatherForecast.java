package com.weather.forecast.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6135342671414194584L;

	private String name;

	private List<WeatherEntry> entries;
	private WeatherEntry entriesObject; 

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("entries")
	public List<WeatherEntry> getEntries() {
		return this.entries;
	}

	@JsonSetter("list")
	public void setEntries(List<WeatherEntry> entries) {
		this.entries = entries;
	}

	@JsonProperty("city")
	public void setCity(Map<String, Object> city) {
		setName(city.get("name").toString());
	}

	public void setEntriesObject(WeatherEntry entries) {
		this.entriesObject = entries;
	}
	
	public WeatherEntry getEntriesObject() {
		return this.entriesObject;
	}

}