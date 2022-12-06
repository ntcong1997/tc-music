plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
        getByName("debug") {
            buildConfigField("String", "X_RapidAPI_Key", "\"cb0c3be532msh5393ffc0637e0ecp1c6024jsn0e148e1c8e2b\"")
            buildConfigField("String", "X_RapidAPI_Host", "\"shazam-core.p.rapidapi.com\"")
        }

        getByName("release") {
            buildConfigField("String", "X_RapidAPI_Key", "\"cb0c3be532msh5393ffc0637e0ecp1c6024jsn0e148e1c8e2b\"")
            buildConfigField("String", "X_RapidAPI_Host", "\"shazam-core.p.rapidapi.com\"")

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

    implementation(project(":domain"))
    implementation(project(":model"))
    testImplementation(project(":test-data"))

    implementation(Libs.CORE_KTX)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Coroutines
    api(Libs.COROUTINES)
    testImplementation(Libs.COROUTINES_TEST)

    // DataStore
    implementation(Libs.DATA_STORE_PREFERENCES)

    // Paging 3
    implementation(Libs.PAGING)

    // Retrofit & Okhttp
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_CONVERTER)
    implementation(Libs.OKHTTP_LOGGING)

    // Room database
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_PAGING)
    kapt(Libs.ROOM_COMPILER)

    // Utils
    api(Libs.TIMBER)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.EXT_JUNIT)

    // Local unit tests
    testImplementation(Libs.JUNIT)
}