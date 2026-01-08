# Life Management System

Life Management System je Java Swing desktop aplikacija koja omogućava korisnicima
praćenje ličnih navika i aktivnosti kroz više trackera.
Aplikacija podržava više korisnika, autentikaciju, personalizaciju teme
i čuvanje podataka u MongoDB bazi.

## Korištene tehnologije:
- Java (Swing GUI)
- MongoDB
- IntelliJ IDEA GUI Designer
- Git & GitHub
- 
## Setup projekta

### Potrebni alati
- Java JDK 8 ili noviji
- MongoDB (lokalno pokrenut)
- IntelliJ IDEA

### Pokretanje MongoDB-a
MongoDB mora biti pokrenut na:
mongodb://localhost:27017

### Pokretanje aplikacije
- Otvoriti projekat u IntelliJ IDEA
- Pokrenuti klasu `Main`
- Aplikacija se pokreće sa Login ekranom

## Autentikacija korisnika
- Registracija novog korisnika
- Login postojećeg korisnika
- Svaki korisnik ima vlastite podatke
- Podaci su vezani za korisničko ime

## Personalizacija (Teme)
- Korisnik bira temu (boju pozadine)
- Tema se primjenjuje nakon prijave
- Promjena teme i korisničkog imena zahtijeva unos lozinke
  
## Trackeri

### Finance Tracker
- Evidencija prihoda i rashoda
- Svaki korisnik vidi samo svoje finansijske podatke
- Prikaz ukupnog prihoda, rashoda i salda
- Dugme za povratak na glavni meni

### Sleep Tracker
- Unos broja sati spavanja po datumu
- Pregled historije spavanja
- Izračun prosječnog spavanja

### Study Tracker
- Unos broja sati učenja po datumu
- Pregled unosa po korisniku

### Mood Tracker
- Unos raspoloženja (1–5)
- Prikaz u tabeli
- Vizualni prikaz prosječnog raspoloženja pomoću JProgressBar-a

### Kalendar
- Unos događaja po datumu
- Kategorije: Study, Sleep, Mood, Ostalo
- Pregled svih događaja po korisniku

## Arhitektura
- MVC princip (Model – View – Manager)
- Svaki tracker ima:
  - Model klasu (*Unos*)
  - Manager klasu (rad sa bazom)
  - Formu (GUI)

## Sigurnost i izolacija podataka
- Podaci su filtrirani po korisničkom imenu
- Jedan korisnik ne može vidjeti podatke drugog korisnika
- Osjetljive promjene zahtijevaju unos lozinke

## Autor

Ime i prezime: Sanela Halilković 
Predmet: Programiranje u Javi  
Akademska godina: 2025/2026

