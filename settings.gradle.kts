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
    }
}

rootProject.name = "FoodDeliveryApp"
include(":app")

include(":core")
include(":core:designsystem")
include(":core:utils")
include(":core:widget")
include(":core:network")
include(":core:presentation")
include(":core:db")
include(":core:navigation")

include(":feature")
include(":feature:home")
include(":feature:home:api")
include(":feature:home:impl")
include(":feature:cart")
include(":feature:cart:api")
include(":feature:cart:impl")
include(":feature:profile")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
