package com.weather.forecast.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forecast.models.WeatherEntry;
import com.weather.forecast.models.WeatherForecast;
import com.weather.forecast.service.IWeatherForecastService;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

	 
	private final IWeatherForecastService weatherForecastService; 
	private WeatherForecast weatherForecast;
	
		
	public WeatherForecastController(IWeatherForecastService weatherForecastService)
	{
		this.weatherForecastService=weatherForecastService; 
		
	}
	
	
	@GetMapping(value="/forecast/{city}")
	public WeatherForecast getWeatherForecastSummary(@PathVariable String city) {
		String weatherForecastResponse = null;
		weatherForecast = weatherForecastService.getWeatherForecast(city);
		
	//	weatherForecastResponse = translateWeatherForecastResponse(weatherForecast);
		//return "04/10 Use sunscreen lotion <br/> 05/10 Carry umberlla";
		return weatherForecast;
		/*
		 * 1. 04/10 Use sunscreen lotion 1. 05/10 Carry umberlla 1. 06/10 Use sunscreen
		 * lotion
		 */
	}


	/*
	 * private String translateWeatherForecastResponse(WeatherForecast
	 * weatherForecastResponse) { List<WeatherEntry> entries =
	 * weatherForecastResponse.getEntries(); Instant currentDate` = Instant.now();
	 * entries.forEach(entry -> { if (entry.getTimestamp().equals(otherInstant)) {
	 * 
	 * } }); return ""; }
	 */
}
