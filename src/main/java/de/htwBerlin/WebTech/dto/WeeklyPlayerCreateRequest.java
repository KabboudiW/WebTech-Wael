package de.htwBerlin.WebTech.dto;

public record WeeklyPlayerCreateRequest(
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
) {}
