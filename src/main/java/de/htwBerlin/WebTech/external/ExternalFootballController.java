package de.htwBerlin.WebTech.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
@CrossOrigin(origins = {"http://localhost:5173", "https://webtech-wael-frontend.onrender.com"})
public class ExternalFootballController {

    private final FootballDataClient client;

    public ExternalFootballController(FootballDataClient client) {
        this.client = client;
    }

    @GetMapping("/scorers")
    public ResponseEntity<String> scorers(@RequestParam String league) {
        String code = CompetitionMapping.MAP.get(league);
        if (code == null) return ResponseEntity.badRequest().body("Unknown league: " + league);
        return ResponseEntity.ok(client.getScorersJson(code));
    }
}
