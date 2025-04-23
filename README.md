# 🧪 Android Playground – News App Demo

This is a sample **Android playground project** demonstrating modern Android development practices using Kotlin, Jetpack Compose, Hilt, and Retrofit. The app fetches news articles from [Mediastack API](https://mediastack.com/) and displays them in a clean, responsive UI.

---

## 📦 Project Structure

:app → Entry point of the application
:core-android → Core utilities, constants, shared components
:design-system → Common UI components, themes, typography
:features:newsfeed → Feature module for news listing and logic

---

## 🧱 Tech Stack

| Tool/Library      | Purpose                                       |
|-------------------|-----------------------------------------------|
| Kotlin 2.0        | Programming language                          |
| Jetpack Compose   | Modern UI toolkit                             |
| Retrofit + Moshi  | Networking and JSON serialization             |
| Hilt (via KSP)    | Dependency Injection                          |
| Coil              | Image loading in Compose                      |
| AtomicReference   | Lightweight in-memory cache for API response  |
| Gradle TOML       | Centralized dependency management             |

---

## 🔧 Architecture Highlights

- **Modularization**: Clearly separated app and feature modules to support scalability.
- **Hilt DI Setup**: Common dependencies like `Retrofit` and `NewsApiService` are provided at the `SingletonComponent` level.
- **Repository Pattern**: Repository abstracts data fetching logic and maps raw API data to UI-friendly models.
- **Caching**: `AtomicReference` is used for fast in-memory caching of API results (only when `useCache = true`).
- **Jetpack Compose**: Used for rendering the UI in a declarative, responsive way.

---

## 🖼️ UI Showcase

<img src="https://via.placeholder.com/400x800?text=News+List+UI" alt="News List Screenshot" width="300"/>

> Based on a white-background, card-based layout using Material 3.

---

## 🚀 How to Run

1. Clone the repository  
   ```bash
   git clone https://github.com/your-username/android-news-playground.git

   Open in Android Studio (Hedgehog or newer)

2. Add your Mediastack API key inside NewsRepositoryImpl.kt

3. newsApiService.getNews("your_api_key")

4. Run the app on emulator or device.

 📌 TODOs / Coming Soon
 Unit Tests for Repository and Mapper

 UI tests using Compose Testing

 Support dark theme

 Pagination for articles

