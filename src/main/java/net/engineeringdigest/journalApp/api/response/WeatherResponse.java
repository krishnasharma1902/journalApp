package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {

    public Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        public String observationTime;

        @JsonProperty("temperature")
        public int temperature;

        @JsonProperty("feelslike")
        public int feelslike;

    }
}
