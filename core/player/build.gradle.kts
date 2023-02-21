plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.player"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.android)

    // Media
    implementation(libs.androidx.media)

    // Exo Player
    implementation(libs.com.google.android.exoplayer.exoplayer.core)

    // Coil
    implementation(libs.io.coil.kt.coil)

    // Utils
    implementation(libs.com.jakewharton.timber)
}