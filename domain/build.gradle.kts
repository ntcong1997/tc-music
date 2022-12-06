plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))

    implementation(project(":model"))

    implementation(Libs.CORE_KTX)

    // UI
    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)

    // Coroutines
    api(Libs.COROUTINES)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_NAVIGATION_COMPOSE)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Retrofit & Okhttp
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_CONVERTER)
    implementation(Libs.OKHTTP_LOGGING)

    // Paging 3
    implementation(Libs.PAGING)

    // Utils
    api(Libs.TIMBER)
    implementation(Libs.GSON)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.EXT_JUNIT)

    // Local unit tests
    testImplementation(Libs.JUNIT)
}