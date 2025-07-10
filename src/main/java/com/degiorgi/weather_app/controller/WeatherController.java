package com.degiorgi.weather_app.controller;

import com.degiorgi.weather_app.model.City;
import com.degiorgi.weather_app.model.WeatherData;
import com.degiorgi.weather_app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<City> cities = weatherService.getCities();
        model.addAttribute("cities", cities);
        return "index";
    }
    
    @PostMapping("/weather")
    @ResponseBody
    public Mono<WeatherData> getWeatherData(@RequestParam String cityName) {
        try {
            City selectedCity = weatherService.getCities().stream()
                    .filter(city -> city.getName().equals(cityName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Città non trovata"));
            
            return weatherService.getWeatherData(selectedCity)
                    .doOnError(error -> {
                        System.err.println("Errore API: " + error.getMessage());
                        error.printStackTrace();
                    })
                    .onErrorResume(error -> {
                        System.err.println("Fallback per errore: " + error.getMessage());
                        return Mono.error(new RuntimeException("Errore nel caricamento dei dati meteo: " + error.getMessage()));
                    });
        } catch (Exception e) {
            System.err.println("Errore nel controller: " + e.getMessage());
            e.printStackTrace();
            return Mono.error(new RuntimeException("Errore interno: " + e.getMessage()));
        }
    }
}