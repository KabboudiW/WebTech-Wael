package de.htwBerlin.WebTech.service;

import de.htwBerlin.WebTech.entity.WeeklyPlayerStats;
import de.htwBerlin.WebTech.repository.WeeklyPlayerStatsRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class WeeklyPlayerStatsServiceTest {

    @Test
    void getWeeklyTopPlayers_withLeagueAndSearch_shouldUseCombinedRepoMethod() {
        WeeklyPlayerStatsRepository repo = mock(WeeklyPlayerStatsRepository.class);
        WeeklyPlayerStatsService service = new WeeklyPlayerStatsService(repo);

        when(repo.findByWeekAndLeagueIgnoreCaseAndPlayerNameContainingIgnoreCaseOrWeekAndLeagueIgnoreCaseAndTeamNameContainingIgnoreCase(
                anyString(), anyString(), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(List.of(new WeeklyPlayerStats(
                1L,"A","TeamA","BL1","2026-W03",9.0,1,0,2,0
        )));

        service.getWeeklyTopPlayers("2026-W03", "rating", "BL1", "A");

        verify(repo, times(1))
                .findByWeekAndLeagueIgnoreCaseAndPlayerNameContainingIgnoreCaseOrWeekAndLeagueIgnoreCaseAndTeamNameContainingIgnoreCase(
                        eq("2026-W03"), eq("BL1"), eq("A"),
                        eq("2026-W03"), eq("BL1"), eq("A")
                );

        verify(repo, never()).findByWeek(anyString());
        verify(repo, never()).findByWeekAndLeagueIgnoreCase(anyString(), anyString());
    }
}
