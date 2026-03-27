## Auteurs

- Mathis Laronde
- Zack Hebert

## Description
What2Play est une application Android permettant de recommander de la musique à partir d’un questionnaire interactif.  
L’utilisateur répond à différentes questions afin de déterminer ses préférences musicales et obtenir une suggestion adaptée.

## Fonctionnalités principales
- Quiz musical interactif
- Ajout d’artistes et de musiques
- Recommandation musicale personnalisée
- Lecture d’extraits via Spotify
- Gestion des paramètres (langue)

## Fonctionnement
L’application repose sur un système de questions permettant de cerner les goûts de l’utilisateur.  
À la fin du questionnaire, une musique est recommandée en fonction des réponses.

### Activités principales
- MainActivity : menu principal
- LoadingActivity : initialisation des données
- SettingsActivity : gestion des paramètres

### Activités du quiz
- GenreActivity
- EmojiActivity
- SliderActivity
- SpinnerActivity
- SwipeActivity
- ListenActivity
- QuizEndActivity

### Base de données
- AppDatabase (Room)
- DAO (TrackDao, ArtistDao, etc.)
- Entities (Track, Artist, Genre, ...)

## Technologies utilisées
- Java / Android SDK
- Room (SQLite)
- Spotify (WebView embeds)