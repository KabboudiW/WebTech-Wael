package de.htwBerlin.WebTech.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weekly_player_stats")
public class WeeklyPlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;
    private String playerName;
    private String teamName;
    private String week;

    private double rating;
    private int goals;
    private int assists;

    public WeeklyPlayerStats() {}

    public WeeklyPlayerStats(
            Long playerId,
            String playerName,
            String teamName,
            String week,
            double rating,
            int goals,
            int assists
    ) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamName = teamName;
        this.week = week;
        this.rating = rating;
        this.goals = goals;
        this.assists = assists;
    }
}
