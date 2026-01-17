package de.htwBerlin.WebTech.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FootballDataClient {

    private final RestClient restClient;

    public FootballDataClient(@Value("${footballData.token}") String token) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.football-data.org/v4")
                .defaultHeader("X-Auth-Token", token)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String getScorersJson(String competitionCode) {
        return restClient.get()
                .uri("/competitions/{code}/scorers", competitionCode)
                .retrieve()
                .body(String.class);
    }
}
