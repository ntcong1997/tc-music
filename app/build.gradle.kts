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
    implementation(Libs.CORE_KTX)
    implementation(Libs.CORE_SPLASH_SCREEN)

    // UI
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)
    implementation(Libs.MATERIAL_ICONS_EXTENDED)
    implementation(Libs.PAGER)
    implementation(Libs.PAGER_INDICATORS)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Architecture Components
    implementation(Libs.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)

    // Compose
    implementation(Libs.COMPOSE_LIVE_DATA)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_UI_TOOLING)
    implementation(Libs.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libs.SWIPE_REFRESH)
    androidTestImplementation(Libs.COMPOSE_TEST)
    debugImplementation(Libs.COMPOSE_UI_TOOLING)

    // Google
    implementation(Libs.GOOGLE_SERVICES_AUTH)

    // Navigation
    implementation(Libs.NAVIGATION_COMPOSE)

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