pluginManagement {
    repositories {
        google()
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
include(":feature:profile")
include(":core:ui")
include(":core:data")
include(":core:database")
include(":core:common")
include(":core:domain")
include(":core:designsystem")
include(":feature:home")
include(":feature:task")
include(":feature:task:add")
include(":feature:task:details")
include(":feature:task:edit")
include(":feature:task:tasklist")
