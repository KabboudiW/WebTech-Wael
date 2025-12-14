package de.htwBerlin.WebTech.service;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.repository.teamrepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyLeaderboardService {

    private final teamrepository repo;

    public WeeklyLeaderboardService(teamrepository repo) {
        this.repo = repo;
    }

    public List<PlayerRow> getTop(String week, String metric) {

        var stats = switch (metric.toLowerCase()) {
            case "goals" -> repo.topByGoals(week);
            case "assists" -> repo.topByAssists(week);
            default -> repo.topByRating(week);
        };

        return stats.stream()
                .limit(10)
                .map(s -> new PlayerRow(
                        s.getPlayerId(),
                        s.getPlayerName(),
                        s.getTeamName(),
                        metric.equals("goals") ? s.getGoals() :
                                metric.equals("assists") ? s.getAssists() :
                                        s.getRating()
                ))
                .toList();
    }
}
