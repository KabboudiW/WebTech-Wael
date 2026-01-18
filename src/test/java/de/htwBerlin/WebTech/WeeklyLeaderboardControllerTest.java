package de.htwBerlin.WebTech;

import de.htwBerlin.WebTech.dto.PlayerRow;
import de.htwBerlin.WebTech.service.WeeklyPlayerStatsService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeeklyLeaderboardController.class)
class WeeklyLeaderboardControllerTest {

    @Autowired MockMvc mvc;

    @MockitoBean
    WeeklyPlayerStatsService service;

    @Test
    void top_shouldReturn200_andCallServiceWithParams() throws Exception {
        when(service.getWeeklyTopPlayers(anyString(), anyString(), any(), any()))
                .thenReturn(List.of(
                        new PlayerRow(1L, "A", "TeamA", 9.1),
                        new PlayerRow(2L, "B", "TeamB", 8.7)
                ));

        mvc.perform(get("/api/weekly/top")
                        .param("week", "2026-W03")
                        .param("metric", "goals")
                        .param("league", "BL1")
                        .param("search", "haaland"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.week").value("2026-W03"))
                .andExpect(jsonPath("$.metric").value("goals"))
                .andExpect(jsonPath("$.rows").isArray())
                .andExpect(jsonPath("$.rows.length()").value(2));

        ArgumentCaptor<String> week = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> metric = ArgumentCaptor.forClass(String.class);

        verify(service).getWeeklyTopPlayers(week.capture(), metric.capture(), eq("BL1"), eq("haaland"));
        assertThat(week.getValue()).isEqualTo("2026-W03");
        assertThat(metric.getValue()).isEqualTo("goals");
    }
    @Test
    void top_whenWeekIsCurrent_shouldResolveToIsoWeek() throws Exception {
        when(service.getWeeklyTopPlayers(anyString(), anyString(), any(), any()))
                .thenReturn(List.of());

        mvc.perform(get("/api/weekly/top")
                        .param("week", "CURRENT")
                        .param("metric", "rating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metric").value("rating"))
                // week should be like 2026-W03
                .andExpect(jsonPath("$.week").value(org.hamcrest.Matchers.matchesPattern("\\d{4}-W\\d{2}")));

        verify(service).getWeeklyTopPlayers(anyString(), eq("rating"), isNull(), isNull());
    }

}
