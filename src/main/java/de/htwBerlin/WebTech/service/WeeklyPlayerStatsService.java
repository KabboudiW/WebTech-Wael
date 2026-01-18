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
                req.league(),
                req.week(),
                req.rating(),
                req.goals(),
                req.assists(),
                req.chances(),   // neu
                req.missed()     // neu
        );
        return repo.save(stats);
    }
    public List<PlayerRow> getWeeklyTopPlayers(String week, String metric, String league, String search) {

        // 1) Daten holen: mit oder ohne league-filter
        List<WeeklyPlayerStats> stats;

        boolean hasLeague = league != null && !league.isBlank();
        boolean hasSearch = search != null && !search.isBlank();

        if (!hasLeague && !hasSearch) {
            stats = repo.findByWeek(week);
        } else if (hasLeague && !hasSearch) {
            stats = repo.findByWeekAndLeagueIgnoreCase(week, league);
        } else if (!hasLeague) {
            stats = repo.findByWeekAndPlayerNameContainingIgnoreCaseOrWeekAndTeamNameContainingIgnoreCase(
                    week, search,
                    week, search
            );
        } else {
            stats = repo.findByWeekAndLeagueIgnoreCaseAndPlayerNameContainingIgnoreCaseOrWeekAndLeagueIgnoreCaseAndTeamNameContainingIgnoreCase(
                    week, league, search,
                    week, league, search
            );
        }


        // 2) Mapping + Sortierung + Top10
        String m = (metric == null) ? "rating" : metric.toLowerCase();

        return stats.stream()
                .map(stat -> {
                    double value = switch (m) {
                        case "goals" -> stat.getGoals();
                        case "assists" -> stat.getAssists();
                        case "chances" -> stat.getChances();
                        case "missed" -> stat.getMissed();
                        default -> stat.getRating();
                    };

                    return new PlayerRow(
                            stat.getPlayerId(),
                            stat.getPlayerName(),
                            stat.getTeamName(), // kommt in PlayerRow als "team"
                            value
                    );
                })
                .sorted((a, b) -> Double.compare(b.value(), a.value()))
                .limit(5)
                .toList();
    }
    public void deleteAll() {
        repo.deleteAll();
    }

}

