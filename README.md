# WebTech-Wael
Team Mitglieder: Wael Kabboudi

Ich baue ein Weekly Football Leaderboard

Ein wöchentliches Ranking-System für Fußballspieler basierend auf verschiedenen Performance-Metriken.

# Projektbeschreibung:
Das Weekly Football Leaderboard zeigt die besten Spieler pro Kalenderwoche in fünf Kategorien:

   1- Best Rated: Höchste durchschnittliche Match-Bewertung

   2- Goals: Meiste Tore

   3- Assists: Meiste Vorlagen

   4- Chances Created: Meiste kreierte Torchancen

   5- Missed Chances: Meiste vergebene Chancen

# Tech Stack
Backend: Java 25 mit Spring Boot 3.5.6
Build Tool: Gradle 9.0.0

# Domain Model
 Player

    id: Eindeutige Spieler-ID

    name: Spielername

    team: Vereinsname

    position: Spielposition

 PlayerWeekStat

    week: Kalenderwoche (YYYY-WW)

    playerId: Referenz auf Spieler

    rating: Durchschnittsbewertung (0.0-10.0)

    goals: Anzahl Tore

    assists: Anzahl Vorlagen

    chancesCreated: Anzahl kreierter Chancen

    missedChances: Anzahl vergebener Chancen


# Rating
http://localhost:8080/api/weekly/top
# Aktuelle Woche, beste Torschützen
http://localhost:8080/api/weekly/top?metric=goals

#  meiste Assists
http://localhost:8080/api/weekly/top?metric=assists

# Meiste vergebene Chancen
http://localhost:8080/api/weekly/top?metric=missed



