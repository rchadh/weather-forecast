package com.weather.forecast.service;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.forecast.models.WeatherForecast;

@Service
public class WeatherForecastServiceImpl implements IWeatherForecastService {

	@Value("${weather.forecast.api.url}")
	private String apiURL;

	@Value("${weather.forecast.api.key}")
	private String apiKey;

	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    @Override
    public WeatherForecast getWeatherForecast(String cityName) {
    	if (!validParameters(cityName)) {
    		throw new ValidationException("Invalid Parameters for City");
    	}
    	RestTemplate restTemplate = new RestTemplate();
    	return  restTemplate.getForObject(getURL(apiURL, cityName, apiKey), WeatherForecast.class);
    }

    private String getURL(String weatherURL, String cityName, String apiKey) {
    	return String.format(weatherURL, cityName, apiKey);
    }
    
    private boolean validParameters(String city) {
		return city != null && !"".equals(city) && apiKey != null && !"".equals(apiKey) && apiURL != null
				&& !"".equals(apiURL);
	}
}
