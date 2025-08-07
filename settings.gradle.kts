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

rootProject.name = "My Task Management"
include(":app")
include(":feature:login")
include(":feature:settings")
include(":feature:taskdetails")
include(":feature:addtask")
include(":core:ui")
include(":core:data")
include(":core:database")
include(":core:common")
include(":core:domain")
include(":core:designsystem")
