package de.htwBerlin.WebTech;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.dto.WeeklyTopResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weekly")
public class WeeklyLeaderboardController {

    @GetMapping("/top")
    public WeeklyTopResponse top(
            @RequestParam(defaultValue = "CURRENT") String week,
            @RequestParam(defaultValue = "rating") String metric
    ) {
        // Week calculation...
        if ("CURRENT".equalsIgnoreCase(week)) {
            var now = java.time.LocalDate.now();
            var weekFields = java.time.temporal.WeekFields.ISO;
            int w = now.get(weekFields.weekOfWeekBasedYear());
            int y = now.get(weekFields.weekBasedYear());
            week = String.format("%d-W%02d", y, w);
        }

        // Erweiterte Dummy-Daten je nach Metrik
        var rows = switch (metric.toLowerCase()) {
            case "goals" -> List.of(
                    new PlayerRow(15L, "Goal Scorer", "FC Goals", 5.0),
                    new PlayerRow(16L, "Net Finder", "SV Strike", 4.0)
            );
            case "assists" -> List.of(
                    new PlayerRow(20L, "Pass Master", "FC Assist", 7.0),
                    new PlayerRow(21L, "Play Maker", "SV Create", 6.0)
            );
            case "chances" -> List.of(
                    new PlayerRow(25L, "Chance Creator", "FC Build", 12.0),
                    new PlayerRow(26L, "Setup King", "SV Play", 10.0)
            );
            case "missed" -> List.of(
                    new PlayerRow(30L, "Miss Expert", "FC Unlucky", 8.0),
                    new PlayerRow(31L, "Close Call", "SV Almost", 7.0)
            );
            default -> List.of(
                    new PlayerRow(10L, "Alex Example", "FC Sample", 8.2),
                    new PlayerRow(11L, "Jamie Demo", "SV Demo", 7.9)
            );
        };

        return new WeeklyTopResponse(week, metric, rows);
    }

}