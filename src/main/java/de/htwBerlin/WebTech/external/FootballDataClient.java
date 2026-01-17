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

    // ✅ NEW: Match Results / Matches in a date range
    public String getMatchesJson(String competitionCode, String dateFrom, String dateTo) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/competitions/{code}/matches")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build(competitionCode)
                )
                .retrieve()
                .body(String.class);
    }
}
