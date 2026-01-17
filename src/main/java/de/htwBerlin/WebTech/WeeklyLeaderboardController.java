package de.htwBerlin.WebTech;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.dto.WeeklyTopResponse;
import org.springframework.web.bind.annotation.*;
import de.htwBerlin.WebTech.dto.WeeklyPlayerCreateRequest;
import de.htwBerlin.WebTech.service.WeeklyPlayerStatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://webtech-wael-frontend.onrender.com"})
@RestController
@RequestMapping("/api/weekly")
public class WeeklyLeaderboardController {
    private final WeeklyPlayerStatsService service;

    public WeeklyLeaderboardController(WeeklyPlayerStatsService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public WeeklyTopResponse top(
            @RequestParam(defaultValue = "CURRENT") String week,
            @RequestParam(defaultValue = "rating") String metric,
            @RequestParam(required = false) String league,
            @RequestParam(required = false) String search

    ) {
        if ("CURRENT".equalsIgnoreCase(week)) {
            var now = java.time.LocalDate.now();
            var weekFields = java.time.temporal.WeekFields.ISO;
            int w = now.get(weekFields.weekOfWeekBasedYear());
            int y = now.get(weekFields.weekBasedYear());
            week = String.format("%d-W%02d", y, w);
        }

        // Fetch real data from the service
        List<PlayerRow> rows = service.getWeeklyTopPlayers(week, metric, league, search);


        return new WeeklyTopResponse(week, metric, rows);
    }
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody WeeklyPlayerCreateRequest request
    ) {
        service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}