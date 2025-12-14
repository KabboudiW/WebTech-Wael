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

    // Getter-Methoden
    public Long getId() { return id; }
    public Long getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getTeamName() { return teamName; }
    public String getWeek() { return week; }
    public double getRating() { return rating; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
}
