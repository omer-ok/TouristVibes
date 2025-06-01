**TouristVibe - Android App**

**Overview**

TouristVibe is an Android application designed to help users discover and capture places with pleasant vibes across London. The app allows users to explore, save, and share their favorite locations in the city.

**Technologies Used**

**Core Technologies**

**Kotlin:** Primary programming language for the project

**Jetpack Compose:** Modern declarative UI toolkit

**MVI (Model-View-Intent):** Architecture pattern for state management

**Coroutines: For asynchronous programming**

**Flow:** For reactive streams of data

**Libraries
Coil: Image loading library**

**Hilt:** Dependency injection framework

**Room:** Local database persistence

**DataStore:** For storing key-value pairs or typed objects

**Material Design 3:** Modern Material Design components and theming

**Features**

Discover pleasant locations across London

Save favorite places locally

View detailed information about each location

Beautiful UI with Material Design 3 components

Offline capabilities with Room database

Smooth image loading with Coil

**Architecture**

The app follows the MVI (Model-View-Intent) architecture pattern:

**Model:** Represents the state of the UI

**View:** Composable functions that display the UI

**Intent:** User actions that trigger state changes

**Setup Instructions**

**Prerequisites**

Android Studio (latest version recommended)

Android SDK (API level 21 or higher)

Kotlin plugin

Installation
Clone the repository

Open the project in Android Studio

Sync the project with Gradle files

Build and run the app

Configuration
Add your API keys (if any) in local.properties file:

MAPS_API_KEY=your_api_key_here
Folder Structure
touristvibe/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/touristvibe/
│   │   │   │   ├── data/            # Data layer (repositories, data sources)
│   │   │   │   ├── di/              # Dependency injection modules
│   │   │   │   ├── domain/          # Business logic and use cases
│   │   │   │   ├── ui/              # Presentation layer (composables, view models)
│   │   │   │   ├── utils/           # Utility classes and extensions
│   │   │   │   └── TouristVibeApp.kt # Main application class
│   │   │   └── res/                 # Resources (drawables, strings, etc.)
│   │   └── test/                    # Unit tests
├── build.gradle                     # Project level Gradle config
└── settings.gradle                  # Project settings
Dependencies
See app/build.gradle for the complete list of dependencies including:

Jetpack Compose

Hilt

Room

Coil

Material Design 3

Navigation Component

And more...

**Contributing**
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

**License**
[Specify your license here, e.g., MIT, Apache 2.0]

**Screenshots**
[Optionally, you can add screenshots of your app here]


**Contact**
For any questions or feedback, please contact [ok.omerkhan19@gmail.com]
