// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    id("io.github.gmazzo.test.aggregation.coverage") version "2.4.7"
    id("io.github.gmazzo.test.aggregation.results") version "2.4.7"
}

testAggregation {
    coverage {
        include("dev/ryan/*/**")
        exclude(
            "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*",
            "**/*hilt*", "**/*Hilt*.*", "dev/ryan/core/ui/**", "dev/ryan/core/designsystem/**"
        )
    }
}

val testTasks = listOf("testDebugUnitTest")
testTasks.forEach { taskName ->
    tasks.register(taskName) {
        finalizedBy("jacocoAggregateReport", "testAggregatedReport")
    }
}
