package com.weather.forecast.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forecast.models.WeatherEntry;
import com.weather.forecast.models.WeatherForecast;
import com.weather.forecast.service.IWeatherForecastService;

import org.apache.commons.text.StringEscapeUtils;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

	 
	private final IWeatherForecastService weatherForecastService; 
	private WeatherForecast weatherForecast;
	
		
	public WeatherForecastController(IWeatherForecastService weatherForecastService)
	{
		this.weatherForecastService=weatherForecastService; 
		
	}
	
	
	@GetMapping(value="/forecast/{city}" )
	public WeatherForecast getWeatherForecastSummary(@PathVariable String city) {
		WeatherForecast weatherForecastResponse = null;
		weatherForecast = weatherForecastService.getWeatherForecast(city);
		weatherForecastResponse = translateWeatherForecastResponse(weatherForecast);
		
		//String escapedHTML = StringEscapeUtils.escapeHtml4(weatherForecastResponse);
		//model.addAttribute("WeatherForecast",escapedHTML);
		return weatherForecastResponse;		
	}



	
	public WeatherForecast translateWeatherForecastResponse(WeatherForecast
	  weatherForecastResponse) { 
		 List<WeatherEntry> entries =  weatherForecastResponse.getEntries(); 
		WeatherForecast wForecast= new WeatherForecast();
		 Instant currentDate = Instant.now();
		 Date weatherDate;
		 double maxTemp=40.0;
		 String RAIN="Rain";
		 String results="";
		 
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
		 		 
	      wForecast.setName(weatherForecastResponse.getName());
	      System.out.println("City :: "+weatherForecastResponse.getName());
		
	      for(WeatherEntry entry : entries) {
			 	
			 	 weatherDate=Date.from(entry.getTimestamp());
			 	
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				 String formattedDate = formatter.format(weatherDate);
				 results="";
				 
				 WeatherEntry ob = new WeatherEntry();
				 
			   if (formattedDate.equals(D1)||formattedDate.equals(D2)||formattedDate.equals(D3))
			   {
				  
			 							 
				 results=results+"Date :: "+ formattedDate + "||  Max temp :: "+ entry.getMax_temperature()+"  || Min temp :: "+entry.getMin_temperature();
								 
				 if (entry.getMax_temperature()> maxTemp) {
					 results=results+"|| Suggestion :: Sun Screen Lotion ";
					
				 }
				 if (entry.getWeatherMain().equals(RAIN))
				 {
				    results=results+"|| Suggestion :: Carry Umberlla ";
				   
				 }
				
				 System.out.println(results);			 
				 			
				 wForecast.setEntriesObject(ob); 		 
				 
			   }
	  
				   
			}
		 
		  
		 return wForecast; 
	  
	 }
	 
}
