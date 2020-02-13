package com.github.turansky.kfc.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.plugin.KotlinJsPluginWrapper
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin as StandardMavenPublishPlugin

private val JS_SOURCES_JAR_TASK = "JsSourcesJar"

class MavenPublishPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins.withType<KotlinJsPluginWrapper> {
            plugins.apply(StandardMavenPublishPlugin::class)

            plugins.withType<StandardMavenPublishPlugin> {
                configure<PublishingExtension> {
                    publications {
                        register("mavenKotlin", MavenPublication::class) {
                            from(components["kotlin"])
                            artifact(tasks.named<Jar>(JS_SOURCES_JAR_TASK).get())
                        }
                    }
                }
            }
        }
    }
}
