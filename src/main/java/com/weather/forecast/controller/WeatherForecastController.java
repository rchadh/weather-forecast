package com.weather.forecast.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forecast.models.ApiResponse;
import com.weather.forecast.models.WeatherEntry;
import com.weather.forecast.models.WeatherForecast;
import com.weather.forecast.service.IWeatherForecastService;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

	private final IWeatherForecastService weatherForecastService;
	private WeatherForecast weatherForecast;

	public WeatherForecastController(IWeatherForecastService weatherForecastService) {
		this.weatherForecastService = weatherForecastService;

	}

	@GetMapping(path = "/forecast/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getWeatherForecastSummary(@PathVariable String city) {
		ApiResponse apiResponse = new ApiResponse();

		weatherForecast = weatherForecastService.getWeatherForecast(city);

		apiResponse = translateWeatherForecastResponse(weatherForecast, apiResponse);

		// String escapedHTML = StringEscapeUtils.escapeHtml4(weatherForecastResponse);
		// model.addAttribute("WeatherForecast",escapedHTML);
		return apiResponse;
	}

	public ApiResponse translateWeatherForecastResponse(WeatherForecast weatherForecastResponse,
			ApiResponse apiResponse) {

		List<WeatherEntry> entriesApiResponse = new ArrayList<WeatherEntry>();
		apiResponse.setCityName(weatherForecastResponse.getName());

		List<WeatherEntry> entries = weatherForecastResponse.getEntries();

		Date weatherDate;
		double maxTemp = 40.0;
		String RAIN = "Rain";

		Date today = new Date();
		Date day1 = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String D1 = format.format(day1);

		// date for Day 2
		Date day2 = new Date(day1.getTime() + (1000 * 60 * 60 * 24));
		String D2 = format.format(day2);

		// date for Day 3
		Date day3 = new Date(day2.getTime() + (1000 * 60 * 60 * 24));
		String D3 = format.format(day3);

		for (WeatherEntry entry : entries) {

			WeatherEntry apiResponseEntry = new WeatherEntry();
			weatherDate = Date.from(entry.getTimestamp());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = formatter.format(weatherDate);

			if (formattedDate.equals(D1) || formattedDate.equals(D2) || formattedDate.equals(D3)) {

				apiResponseEntry.setMin_temperature(entry.getMin_temperature());
				apiResponseEntry.setMax_temperature(entry.getMax_temperature());
				apiResponseEntry.setDate(formattedDate);

				if (entry.getMax_temperature() > maxTemp && !entry.getWeatherMain().equals(RAIN)) {
					apiResponseEntry.setSuggestion("Carry Sun Screen Lotion");
				} else if (entry.getWeatherMain().equals(RAIN)) {
					apiResponseEntry.setSuggestion("Carry Umbrella");
				}
				entriesApiResponse.add(apiResponseEntry);
			}
			
		}
		apiResponse.setWeatherEntries(entriesApiResponse);
		return apiResponse;

	}

}
