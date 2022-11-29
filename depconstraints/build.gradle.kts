plugins {
    id("java-platform")
    id("maven-publish")
}

val accompanist = "0.25.1"
val activityCompose = "1.6.0"
val appcompat = "1.5.1"
val cameraX = "1.1.0"
val coil = "2.1.0"
val compose = Versions.COMPOSE
val compressor = "3.0.1"
val constraintLayout = "2.1.4"
val coroutines = "1.6.4"
val core = "1.9.0"
val coreSplashScreen = "1.0.0-rc01"
val dataStore = "1.0.0"
val espresso = "3.3.0"
val googleServicesAuth = "20.2.0"
val gson = "2.9.1"
val hilt = Versions.HILT
val junit = "4.13.2"
val junitExt = "1.1.2"
val lifecycle = "2.5.1"
val material = "1.6.1"
val okhttp = "4.10.0"
val paging = "3.1.1"
val pagingCompose = "1.0.0-alpha16"
val retrofit = "2.9.0"
val room = "2.4.3"
val timber = "5.0.1"

dependencies {
    constraints {
        api("${Libs.ACTIVITY_COMPOSE}:$activityCompose")
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.CAMERAX_CORE}:$cameraX")
        api("${Libs.CAMERAX_LIFECYCLE}:$cameraX")
        api("${Libs.CAMERAX_VIEW}:$cameraX")
        api("${Libs.COIL_BASE}:$coil")
        api("${Libs.COIL_COMPOSE}:$coil")
        api("${Libs.COMPOSE_LIVE_DATA}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_TEST}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING_PREVIEW}:$compose")
        api("${Libs.COMPRESSOR}:$compressor")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.CORE_SPLASH_SCREEN}:$coreSplashScreen")
        api("${Libs.COROUTINES}:$coroutines")
        api("${Libs.COROUTINES_TEST}:$coroutines")
        api("${Libs.DATA_STORE_PREFERENCES}:$dataStore")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.GOOGLE_SERVICES_AUTH}:$googleServicesAuth")
        api("${Libs.GSON}:$gson")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_NAVIGATION_COMPOSE}:1.0.0")
        api("${Libs.HILT_TESTING}:$hilt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_COMPOSE}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.MATERIAL_ICONS_EXTENDED}:$compose")
        api("${Libs.NAVIGATION_COMPOSE}:${Versions.NAVIGATION}")
        api("${Libs.OKHTTP_LOGGING}:$okhttp")
        api("${Libs.PAGER}:$accompanist")
        api("${Libs.PAGER_INDICATORS}:$accompanist")
        api("${Libs.PAGING}:$paging")
        api("${Libs.PAGING_COMPOSE}:$pagingCompose")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_CONVERTER}:$retrofit")
        api("${Libs.ROOM_COMPILER}:$room")
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_PAGING}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.SWIPE_REFRESH}:$accompanist")
        api("${Libs.TIMBER}:$timber")
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}