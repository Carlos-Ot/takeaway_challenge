# Takeaway

An Android App that read a local JSON file with a list of restaurants to experiment Android Libraries and Architectures.

## Architecture
This app was built under a MVVM Architecture following the Clean Architecture Principles.

## Tech Stack
- Gradle Kotlin DSL for managing modules;
- Koin for Dependency Injection;
- Android Jetpack Libraries: 
  - Room 
  - LiveData
  - ViewModel
  - DataBinding
  - Navigation Component
  - Material Design Components
  - Fragment
  - Test
  - Annotation
- Kotlin Coroutines
- Moshi for JSON handling
- Detekt & KtLint for static analysis
- Google Truth for Test Assertions
- MockK for Test Mocking
- GitHub Actions for CI

## Quick Start

### Pre-Requisites
- Android Version 8.0+
- Android Studio 3.2.0+

### How to Run?
Import the project to Android Studio, run the Sync Project With Gradle Files task, and hit the Play Button

### Running Unit Tests from CLI
Just run the following command:
`./gradlew testDebugUnitTest`

