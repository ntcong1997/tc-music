plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.common"
}

dependencies {
    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core)
}