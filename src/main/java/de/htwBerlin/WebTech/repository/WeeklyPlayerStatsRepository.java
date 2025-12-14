package de.htwBerlin.WebTech.repository;

import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyPlayerStatsRepository
        extends JpaRepository<WeeklyPlayerStats, Long> {
}
