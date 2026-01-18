# 🏆 Weekly Football Leaderboard

**Teammitglied:**  
Wael Kabboudi

---

## 📌 Projektbeschreibung

Das **Weekly Football Leaderboard** ist eine Webanwendung zur Darstellung eines **wöchentlichen Rankings von Fußballspielern** sowie von **Spielergebnissen pro Liga und Woche**.

Die Anwendung kombiniert:
- **Eigene Spielerstatistiken** (Datenbank)
- **Externe Spielerdaten / Spielergebnisse** (Football API)

Ziel ist es, Spielerleistungen übersichtlich zu vergleichen und gleichzeitig reale Spielergebnisse pro Liga darzustellen.

---

## 📊 Spielerstatistiken – Ranking-Kategorien

Das Leaderboard unterstützt folgende **5 Performance-Metriken**:

1. **Best Rated** – Höchste durchschnittliche Match-Bewertung
2. **Goals** – Meiste erzielte Tore
3. **Assists** – Meiste Torvorlagen
4. **Chances Created** – Meiste kreierte Torchancen
5. **Missed Chances** – Meiste vergebene Torchancen

Für jede Kategorie werden die **Top-Spieler einer ausgewählten Kalenderwoche** angezeigt.

---

## ⚽ Spielergebnisse (Match Results)

Zusätzlich bietet die Anwendung eine Ansicht für **Spielergebnisse pro Liga und Woche**.

### Eigenschaften:
- Anzeige aller Spiele einer Liga für eine ausgewählte Woche
- Darstellung von:
    - Heimteam
    - Auswärtsteam
    - Endergebnis
- Daten werden **live aus einer externen Football API** abgerufen
- Umschaltbar im Frontend zwischen:
    - **Player Stats**
    - **Match Results**

👉 Spielergebnisse werden **nicht in der lokalen Datenbank gespeichert**, sondern dynamisch geladen.

---

## 🧱 Domain Model

### Player
- `id` – Eindeutige Spieler-ID
- `name` – Spielername
- `team` – Vereinsname
- `position` – Spielposition

### PlayerWeekStat
- `week` – Kalenderwoche (YYYY-WW)
- `playerId` – Referenz auf Player
- `rating` – Durchschnittsbewertung (0.0 – 10.0)
- `goals` – Anzahl erzielter Tore
- `assists` – Anzahl Vorlagen
- `chancesCreated` – Anzahl kreierter Chancen
- `missedChances` – Anzahl vergebener Chancen

---

## 🛠️ Tech Stack

### Backend
- Java 25
- Spring Boot 3.5.6
- Gradle 9.0.0
- REST API
- JPA / Datenbank
- Externe Football API (Spielergebnisse)

### Frontend
- Vue 3
- Vite
- TypeScript
- REST-basierte API-Integration

--