plugins {
    id("tcmusic.android.application")
    id("tcmusic.android.application.compose")
    id("tcmusic.android.hilt")
}

android {
    defaultConfig {
        applicationId = "com.example.tcmusic"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "com.example.tcmusic.core.testing.TcMusicTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        // TODO: Convert it as a convention plugin once Flamingo goes out (https://github.com/android/tcmusic/issues/523)
        managedDevices {
            devices {
                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel4api30").apply {
                    device = "Pixel 4"
                    apiLevel = 30
                    // ATDs currently support only API level 30.
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
    namespace = "com.example.tcmusic"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:search"))
    implementation(project(":feature:playlists"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:artist"))
    implementation(project(":feature:track"))

    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:player"))
    implementation(project(":core:model"))

    // Lifecycle
    implementation(libs.androidx.lifecycle.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose)

    // Utils
    implementation(libs.com.jakewharton.timber)
}