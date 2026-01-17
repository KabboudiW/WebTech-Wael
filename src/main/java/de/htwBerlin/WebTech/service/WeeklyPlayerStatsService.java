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
                req.assists(),
                req.chances(),   // neu
                req.missed()     // neu
        );
        return repo.save(stats);
    }

    List<WeeklyPlayerStats> findByWeek(String week) {
        repo.findAll()
    }
    public List<PlayerRow> getWeeklyTopPlayers(String week, String metric) {
        return this.findByWeek(week) // hole alle Spieler der Woche
                .stream()
                .map(stat -> {
                    double value;
                    switch (metric.toLowerCase()) {
                        case "goals" -> value = stat.getGoals();
                        case "assists" -> value = stat.getAssists();
                        case "chances" -> value = stat.getChances(); // hier musst du evtl. eine neue Spalte in WeeklyPlayerStats hinzufügen
                        case "missed" -> value = stat.getMissed(); // auch neue Spalte nötig
                        default -> value = stat.getRating(); // "Best Rated"
                    }
                    return new PlayerRow(
                            stat.getPlayerId(),
                            stat.getPlayerName(),
                            stat.getTeamName(),
                            value
                    );
                })
                .sorted((a, b) -> Double.compare(b.value(), a.value())) // absteigend sortieren
                .limit(10) // Top 10 Spieler
                .toList();
    }

}

