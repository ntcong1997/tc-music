plugins {
    id("tcmusic.android.library")
    id("tcmusic.android.hilt")
}

android {
    namespace = "com.example.tcmusic.core.testing"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:player"))
    implementation(project(":core:data"))

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)

    // Test runner
    implementation(libs.androidx.test.runner)

    // Hilt test
    implementation(libs.com.google.dagger.hilt.android.testing)

    // Paging 3
    implementation(libs.androidx.paging.paging.runtime)

    // Local unit tests
    implementation(libs.junit)
}