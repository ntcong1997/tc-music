plugins {
    id("tcmusic.android.feature")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.feature.search"
}

dependencies {
    implementation(libs.androidx.paging.paging.compose)
}