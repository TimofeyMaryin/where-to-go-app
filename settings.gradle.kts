pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "WhereToGoApplication"

include(":app")
include(":common-ui")
include(":core")
include(":domain")

include(":feature-auth")
include(":feature-profile")

include(":presentation:auth")
include(":presentation:main")
include(":presentation:utils")
