package de.htwBerlin.WebTech.repository;

import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklyPlayerStatsRepository
        extends JpaRepository<WeeklyPlayerStats, Long> {
    List<WeeklyPlayerStats> findByWeek(String week);
}
