package de.htwBerlin.WebTech.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FootballDataClient {

    private final WebClient webClient;

    public FootballDataClient(@Value("${footballData.token}") String token) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.football-data.org/v4")
                .defaultHeader("X-Auth-Token", token)
                .build();
    }

    public String getStandingsJson(String competitionCode) {
        return webClient.get()
                .uri("/competitions/{code}/standings", competitionCode)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getScorersJson(String competitionCode) {
        return webClient.get()
                .uri("/competitions/{code}/scorers", competitionCode)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
