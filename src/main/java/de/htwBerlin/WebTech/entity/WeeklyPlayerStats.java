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
    private String League;
    private String week;

    private double rating;
    private int goals;
    private int assists;
    private int chances;
    private int missed;

    public WeeklyPlayerStats() {}

    public WeeklyPlayerStats(
            Long playerId,
            String playerName,
            String teamName,
            String league,
            String week,
            double rating,
            int goals,
            int assists,
            int chances,
            int missed
    ) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamName = teamName;
        this.League = league;
        this.week = week;
        this.rating = rating;
        this.goals = goals;
        this.assists = assists;
        this.chances = chances;
        this.missed = missed;
    }

    // Getter-Methoden
    public Long getId() { return id; }
    public Long getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getTeamName() { return teamName; }
    public String getLeague() { return League; }
    public String getWeek() { return week; }
    public double getRating() { return rating; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getChances() { return chances; }
    public int getMissed() { return missed; }
}
