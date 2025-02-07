package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeatherService {

    @Value("${weather.api.key}")       // Should not be used on static variables
    private String apiKey;

    @Autowired
    private AppCache appCache;

    private String API;

    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city){
        try {
            log.info(appCache.appCache.get(AppCache.keys.WEATHER_API.name()));
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.name()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);// Returned response will be deserialized into weatherresponse object
            WeatherResponse weatherResponse = response.getBody();
            return weatherResponse;
        }catch(Exception e){
            log.error("Exception in Weather Service.", e);
            return null;
        }
    }


}
