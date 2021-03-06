plugins {
    kotlin("js") apply false
}

allprojects {
    repositories {
        jcenter()
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlinx" && requested.name.startsWith("kotlinx-serialization-runtime")) {
                useVersion("0.20.0")
            }
        }
    }
}

tasks.wrapper {
    gradleVersion = "6.4.1"
    distributionType = Wrapper.DistributionType.ALL
}
