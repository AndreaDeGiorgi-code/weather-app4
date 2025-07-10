package com.degiorgi.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherData {
    @JsonProperty("daily")
    private Daily daily;

    public static class Daily {
        @JsonProperty("time")
        private List<String> time;

        @JsonProperty("temperature_2m_max")
        private List<Double> temperatureMax;

        @JsonProperty("temperature_2m_min")
        private List<Double> temperatureMin;
    }
}