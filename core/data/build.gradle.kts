plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    // Hilt Test
    implementation(libs.com.google.dagger.hilt.android.testing)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)
}