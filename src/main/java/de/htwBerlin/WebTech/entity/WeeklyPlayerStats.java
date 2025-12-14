package de.htwBerlin.WebTech.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "weekly_player_stats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"player_id", "week"})
        }
)
public class WeeklyPlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    private String playerName;
    private String teamName;
    private String week;

    private double rating;
    private int goals;
    private int assists;
    private int chances;
    private int missed;

    protected WeeklyPlayerStats() {}

    public Long getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getTeamName() { return teamName; }
    public String getWeek() { return week; }
    public double getRating() { return rating; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getChances() { return chances; }
    public int getMissed() { return missed; }
}
