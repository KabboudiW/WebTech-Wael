package de.htwBerlin.WebTech;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.dto.WeeklyTopResponse;
import de.htwBerlin.WebTech.dto.WeeklyPlayerCreateRequest;
import de.htwBerlin.WebTech.service.WeeklyPlayerStatsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://webtech-wael-frontend.onrender.com"})
@RestController
@RequestMapping("/api/weekly")
public class WeeklyLeaderboardController {
    private final WeeklyPlayerStatsService service;
    @Value("${admin.token:}")
    private String adminToken;

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
    @DeleteMapping("/reset")
    public ResponseEntity<Void> reset(
            @RequestHeader(name = "X-Admin-Token", required = false) String token
    ) {
        // Wenn kein ADMIN_TOKEN gesetzt ist: Endpoint praktisch deaktivieren
        if (adminToken == null || adminToken.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Token prüfen
        if (token == null || !adminToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}