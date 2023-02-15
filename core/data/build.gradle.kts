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

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Gson
    implementation(libs.com.squareup.retrofit2.converter.gson)
}