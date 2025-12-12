# 📘 SeekhoAnime

SeekhoAnime is a modern Android application built using **Jetpack Compose**, **Clean Architecture**, **Hilt**, **Retrofit**, and **Paging 3**, designed to display anime lists and detailed anime information using the public **Jikan API (MyAnimeList)**.

The project follows clean, scalable architectural patterns and modern UI practices that align with current Android development standards.

---

## 🚀 Overview

SeekhoAnime demonstrates:
- Clean code architecture
- Scalable UI and data flow
- Reactive streams via Kotlin Flows
- Comprehensive error handling
- Professional UI/UX using Jetpack Compose

---

## ✨ Features Implemented

### 🏠 Home Screen (Anime List)
- Fetches **Top Anime** using Jikan API.
- Uses **Paging 3** for infinite scrolling.
- Smooth **shimmer effect** while loading.
- Attractive anime card UI.
- Handles all states:
  - Loading
  - Error (with retry)
  - Empty
- Clicking an anime opens the Anime Detail Page.

---

### 🎬 Anime Detail Page
Displays everything a user expects in an anime details experience:

#### ✔ Video Player  
- Plays the **YouTube trailer** if available.  
- Falls back to poster image when no trailer exists.

#### ✔ Anime Metadata  
- Title  
- Synopsis / Plot (scrollable)  
- Genres (Chip UI)  
- Main Cast (horizontal scroll list)  
- Number of Episodes  
- Rating  
- Release Year  
- Studios  

#### ✔ Error & Loading  
- Full-screen shimmer for detail loading.  
- Friendly error message with retry option.

---

## 📌 Assumptions Made

### API-Related
- Jikan API response formats remain consistent.  
- Some entries may have missing trailer, cast, genres — handled safely.  
- Characters are fetched from `/anime/{id}/characters` endpoint.

### UI/UX
- App should show **poster instead of video** when trailer is unavailable.  
- Detail screen requires **rich metadata** (genres, cast, studios).  
- Loading skeletons improve UX over blank screens.  
- Exit confirmation popup is shown only on the Home screen.

### Architecture/Behavior
- Domain layer must stay framework-independent.  
- State flows should drive all UI changes.  
- Error states must be distinguishable (API error vs. No Internet).  
- Paging should be cancellable + restartable on retry.

---

## 🧱 Folder Structure

```
SeekhoAnime/
│
├── data/
│   ├── remote/        # Retrofit DTOs & API services
│   ├── local/         # Room DB, DAO (optional)
│   ├── repository/    # Repository implementations
│   └── paging/        # Paging 3 source & configs
│
├── domain/
│   ├── model/         # Clean domain models
│   ├── repository/    # Repository interfaces
│   └── usecase/       # Use cases for business logic
│
├── ui/
│   ├── home/          # Home screen + cards + paging list
│   ├── detail/        # Detail screen with video, cast, genres
│   ├── components/    # Common reusable Compose components
│   ├── theme/         # Colors, typography, shapes
│   └── navigation/    # Compose navigation graph
│
├── di/                # All Hilt modules
└── common/            # Utilities (Network checker, Resource wrapper)
```

---

## 🧰 Tech Stack

### **Frontend**
- Kotlin + Coroutines  
- Jetpack Compose  
- Material 3  
- Accompanist (System UI Controller, Shimmer)

### **Architecture**
- Clean Architecture  
- MVVM  
- Unidirectional Data Flow  
- Repository Pattern  

### **Networking**
- Retrofit  
- OkHttp Logging  
- Gson  

### **Dependency Injection**
- Hilt  

### **Data Handling**
- Paging 3  
- Room (for optional caching)  

---

## 📦 Installation & Setup

### Requirements
- Android Studio Flamingo or newer  
- JDK 17  
- Minimum SDK: 23  
- Target SDK: 34+  

### Steps
```bash
git clone https://github.com/Avikal/SeekhoAnime.git
cd SeekhoAnime
open in Android Studio
Build → Run
```

---

## 🔮 Future Enhancements
- Search anime functionality  
- Favorites with Room caching  
- Full offline support  
- Episode list per anime  
- ExoPlayer for official trailers  
- Downloadable wallpapers  
- Scheduling reminders  
- Multi-language support  

---

## ❤️ Acknowledgements

- Jikan API (https://jikan.moe)  
- Jetpack Compose Team  
- Google Android Dev  
- Coil  
- Accompanist  
- Open-source community  

Screenshot

<img width="1080" height="2400" alt="Screenshot_20251213_004705" src="https://github.com/user-attachments/assets/24038045-6ded-4d66-96d5-c682ac44dffb" />

<img width="1080" height="2400" alt="Screenshot_20251213_004734" src="https://github.com/user-attachments/assets/6e776423-cf3d-472a-9500-9a4015e3b9bc" />
