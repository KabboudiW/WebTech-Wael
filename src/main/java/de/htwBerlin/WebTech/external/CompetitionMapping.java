package de.htwBerlin.WebTech.external;

import java.util.Map;

public class CompetitionMapping {
    // football-data.org competition codes (v4)
    public static final Map<String, String> MAP = Map.of(
            "EPL", "PL",
            "BL1", "BL1",
            "LALIGA", "PD",
            "SERIEA", "SA",
            "LIGUE1", "FL1"
    );
}
