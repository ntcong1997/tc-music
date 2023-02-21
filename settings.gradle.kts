pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TC Music"
include(":app")
include(":core:model")
include(":core:network")
include(":core:data")
include(":core:common")
include(":core:player")
include(":core:domain")
include(":core:designsystem")
include(":core:ui")
include(":core:testing")
include(":feature:track")
include(":feature:artist")
include(":feature:home")
include(":feature:search")
include(":feature:playlists")
include(":feature:settings")