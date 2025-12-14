package de.htwBerlin.WebTech.service;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.dto.WeeklyPlayerCreateRequest;
import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import de.htwBerlin.WebTech.repository.WeeklyPlayerStatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyPlayerStatsService {

    private final WeeklyPlayerStatsRepository repo;

    public WeeklyPlayerStatsService(WeeklyPlayerStatsRepository repo) {
        this.repo = repo;
    }

    public WeeklyPlayerStats create(WeeklyPlayerCreateRequest req) {
        WeeklyPlayerStats stats = new WeeklyPlayerStats(
                req.playerId(),
                req.playerName(),
                req.teamName(),
                req.week(),
                req.rating(),
                req.goals(),
                req.assists()
        );
        return repo.save(stats);
    }
    public List<PlayerRow> getWeeklyTopPlayers(String week, String metric) {
        var stats = repo.findByWeek(week); // nur nach Woche filtern

        return stats.stream()
                .map(stat -> {
                    double value;
                    switch (metric.toLowerCase()) {
                        case "goals" -> value = stat.getGoals();
                        case "assists" -> value = stat.getAssists();
                        default -> value = stat.getRating();
                    }
                    return new PlayerRow(stat.getPlayerId(), stat.getPlayerName(), stat.getTeamName(), value);
                })
                .sorted((a, b) -> Double.compare(b.value(), a.value())) // absteigend
                .toList();
    }

}

