/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.android.build.gradle.LibraryExtension
import com.example.tcmusic.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("tcmusic.android.library")
                apply("tcmusic.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "com.example.tcmusic.core.testing.TcMusicTestRunner"
                }
                configureGradleManagedDevices(this)
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:domain"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":core:testing"))
                add("testImplementation", project(":core:data"))
                add("testImplementation", project(":core:player"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":core:testing"))

                add("implementation", libs.findLibrary("io.coil.kt.coil").get())
                add("implementation", libs.findLibrary("io.coil.kt.coil.compose").get())

                add("implementation", libs.findLibrary("androidx.hilt.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.lifecycle.runtime.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.lifecycle.viewmodel.compose").get())

                add("implementation", libs.findLibrary("org.jetbrains.kotlinx.kotlinx.coroutines.android").get())

                add("testImplementation", libs.findLibrary("org.jetbrains.kotlinx.kotlinx.coroutines.test").get())

                add("androidTestImplementation", libs.findLibrary("androidx.compose.ui.ui.test.junit4").get())
                add("debugImplementation", libs.findLibrary("androidx.compose.ui.ui.test.manifest").get())
            }
        }
    }
}
