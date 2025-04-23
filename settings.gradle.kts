pluginManagement {
    plugins {
        id("com.google.dagger.hilt.android") version "2.56.2"
        id("com.google.devtools.ksp") version "2.0.0-1.0.24"
    }
    repositories {
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidPlayground"
include(":app", ":design-system", ":core-android")
include(":features:newsfeed")
