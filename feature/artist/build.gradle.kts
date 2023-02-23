plugins {
    id("tcmusic.android.feature")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.feature.artist"
}

dependencies {
    testImplementation(project(":core:data"))
    testImplementation(project(":core:player"))

    // Coroutines
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Turbine
    testImplementation(libs.app.cash.turbine)
}