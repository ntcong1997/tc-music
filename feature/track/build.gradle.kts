plugins {
    id("tcmusic.android.feature")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.feature.track"
}

dependencies {
    implementation(libs.androidx.compose.material)
}