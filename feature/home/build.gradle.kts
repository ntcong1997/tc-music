plugins {
    id("tcmusic.android.feature")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.feature.home"
}

dependencies {
    implementation(libs.com.google.accompanist.accompanist.pager)
    implementation(libs.com.google.accompanist.accompanist.pager.indicators)

    implementation(libs.androidx.paging.paging.compose)
}