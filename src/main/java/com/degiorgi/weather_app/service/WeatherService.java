package com.degiorgi.weather_app.service;

import com.degiorgi.weather_app.model.City;
import com.degiorgi.weather_app.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class WeatherService {


    
    private final WebClient webClient;
    
    public WeatherService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.open-meteo.com/v1")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();
    }
    
    public List<City> getCities() {
        return Arrays.asList(
            new City("Roma", 41.9028, 12.4964),
            new City("Milano", 45.4642, 9.1900),
            new City("Napoli", 40.8518, 14.2681),
            new City("Torino", 45.0703, 7.6869),
            new City("Palermo", 38.1157, 13.3613),
            new City("Genova", 44.4056, 8.9463),
            new City("Bologna", 44.4949, 11.3426),
            new City("Firenze", 43.7696, 11.2558),
            new City("Bari", 41.1171, 16.8719),
            new City("Catania", 37.5079, 15.0830)
        );
    }
  
    public Mono<WeatherData> getWeatherData(City city) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(13);

    String url = String.format(
        "/forecast?latitude=%f&longitude=%f&start_date=%s&end_date=%s&daily=temperature_2m_max,temperature_2m_min&timezone=Europe%%2FRome",
        city.getLatitude(),
        city.getLongitude(),
        startDate.format(DateTimeFormatter.ISO_DATE),
        endDate.format(DateTimeFormatter.ISO_DATE)
    );

    System.out.println("🔵 URL chiamato: " + url);

    return webClient.get()
        .uri(url)
        .retrieve()
        .bodyToMono(String.class) // <--- otteniamo la risposta come stringa prima
        .doOnNext(response -> System.out.println(" Risposta API: " + response))
        .flatMap(response -> {
            // Proviamo a deserializzare "a mano" per vedere l'errore preciso
            try {
                ObjectMapper mapper = new ObjectMapper();
                WeatherData data = mapper.readValue(response, WeatherData.class);
                return Mono.just(data);
            } catch (Exception e) {
                System.err.println("Errore di parsing JSON: " + e.getMessage());
                e.printStackTrace();
                return Mono.error(new RuntimeException("Errore nel parsing dei dati meteo"));
            }
        });
}
}