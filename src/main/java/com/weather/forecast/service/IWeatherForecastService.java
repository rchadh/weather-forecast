package com.weather.forecast.service;

import com.weather.forecast.models.WeatherForecast;

public interface IWeatherForecastService
{

	public WeatherForecast getWeatherForecast(String city);
	
}
