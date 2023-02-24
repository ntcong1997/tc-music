plugins {
    id("tcmusic.android.feature")
    id("tcmusic.android.library.compose")
}

android {
    namespace = "com.example.tcmusic.feature.search"
}

dependencies {
    testImplementation(project(":core:common"))

    // Paging 3
    implementation(libs.androidx.paging.paging.compose)
}