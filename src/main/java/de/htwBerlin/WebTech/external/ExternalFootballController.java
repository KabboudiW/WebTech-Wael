package de.htwBerlin.WebTech.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

@RestController
@RequestMapping("/api/external")
@CrossOrigin(origins = {"http://localhost:5173", "https://webtech-wael-frontend.onrender.com"})
public class ExternalFootballController {

    private final FootballDataClient client;

    public ExternalFootballController(FootballDataClient client) {
        this.client = client;
    }

    @GetMapping("/matches")
    public ResponseEntity<String> matches(
            @RequestParam String league,
            @RequestParam(defaultValue = "CURRENT") String week
    ) {
        String competitionCode = CompetitionMapping.MAP.get(league);
        if (competitionCode == null) {
            return ResponseEntity.badRequest().body("Unknown league: " + league);
        }

        // week normalisieren
        if ("CURRENT".equalsIgnoreCase(week)) {
            var now = LocalDate.now();
            var wf = WeekFields.ISO;
            int w = now.get(wf.weekOfWeekBasedYear());
            int y = now.get(wf.weekBasedYear());
            week = String.format("%d-W%02d", y, w);
        }

        // ISO Week String "2026-W03" -> (monday..sunday)
        LocalDate monday = isoWeekMonday(week);
        LocalDate sunday = monday.plusDays(6);

        String json = client.getMatchesJson(
                competitionCode,
                monday.toString(),  // YYYY-MM-DD
                sunday.toString()
        );

        return ResponseEntity.ok(json);
    }

    private static LocalDate isoWeekMonday(String isoWeek) {
        // expected: "YYYY-Www"
        String[] parts = isoWeek.split("-W");
        int year = Integer.parseInt(parts[0]);
        int week = Integer.parseInt(parts[1]);

        WeekFields wf = WeekFields.ISO;
        // Start from Jan 4th (always in week 1), then set week and day-of-week
        LocalDate jan4 = LocalDate.of(year, 1, 4);
        return jan4
                .with(wf.weekOfWeekBasedYear(), week)
                .with(wf.dayOfWeek(), 1); // Monday
    }
}
