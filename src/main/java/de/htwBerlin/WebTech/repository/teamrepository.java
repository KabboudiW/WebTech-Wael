package de.htwBerlin.WebTech.repository;

import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@repository
public interface teamrepository extends JpaRepository <WeeklyPlayerStats, Long>{

}
