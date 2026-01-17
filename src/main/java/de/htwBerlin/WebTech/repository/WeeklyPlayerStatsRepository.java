package de.htwBerlin.WebTech.repository;

import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklyPlayerStatsRepository
        extends JpaRepository<WeeklyPlayerStats, Long> {
    List<WeeklyPlayerStats> findByWeek(String week);
    List<WeeklyPlayerStats> findByWeekAndLeagueIgnoreCase(String week, String league);
    List<WeeklyPlayerStats> findByWeekAndPlayerNameContainingIgnoreCaseOrWeekAndTeamNameContainingIgnoreCase(
            String week1, String playerName,
            String week2, String teamName
    );

    List<WeeklyPlayerStats> findByWeekAndLeagueIgnoreCaseAndPlayerNameContainingIgnoreCaseOrWeekAndLeagueIgnoreCaseAndTeamNameContainingIgnoreCase(
            String week1, String league1, String playerName,
            String week2, String league2, String teamName
    );
}
