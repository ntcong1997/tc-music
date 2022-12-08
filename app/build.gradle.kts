plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        applicationId = "com.example.tcmusic"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }



    buildTypes {
        getByName("release") {
            isDebuggable = false

            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":model"))

    // Core
    implementation(libs.androidx.core.core.ktx)
    implementation(libs.androidx.core.core.splashscreen)

    // UI
    implementation(libs.androidx.activity.activity.compose)
    implementation(libs.androidx.appcompat.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.compose.material.material.icons.extended)
    implementation(libs.com.google.accompanist.accompanist.pager)
    implementation(libs.com.google.accompanist.accompanist.pager.indicators)

    // Kotlin
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk7)

    // Architecture Components
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.lifecycle.runtime.ktx)
    kapt(libs.androidx.lifecycle.lifecycle.compiler)

    // Compose
    implementation(libs.androidx.compose.material.material)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.ui.tooling)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.com.google.accompanist.accompanist.swiperefresh)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)

    // Navigation
    implementation(libs.androidx.navigation.navigation.compose)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_NAVIGATION_COMPOSE)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Coil
    implementation(Libs.COIL_BASE)
    implementation(Libs.COIL_COMPOSE)

    // Paging 3
    implementation(Libs.PAGING)
    implementation(Libs.PAGING_COMPOSE)

    // Retrofit & Okhttp
    implementation(Libs.RETROFIT)
    implementation(Libs.OKHTTP_LOGGING)

    // Utils
    implementation(Libs.COMPRESSOR)
    api(Libs.TIMBER)
    implementation(Libs.GSON)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.EXT_JUNIT)

    // Local unit tests
    testImplementation(Libs.JUNIT)
}