package de.htwBerlin.WebTech.dto;

import java.util.List;

public record WeeklyTopResponse(String week, String metric, List<PlayerRow> rows) {}
