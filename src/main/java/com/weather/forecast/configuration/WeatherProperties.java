package com.weather.forecast.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weather.forecast.api")

public class WeatherProperties {

	public String key;
	public String url;
	
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getURL() {
		return this.key;
	}

	public void setURL(String URL) {
		this.url = URL;
	}
}
