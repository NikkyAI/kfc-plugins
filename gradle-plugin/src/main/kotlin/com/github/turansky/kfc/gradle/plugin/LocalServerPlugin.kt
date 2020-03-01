package com.github.turansky.kfc.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinJsPluginWrapper
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.tasks.KotlinJsDce

open class LocalServerExtension {
    var root: String? = null
}

class LocalServerPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins.withType<KotlinJsPluginWrapper> {
            plugins.apply(WebpackPlugin::class)

            val extension = extensions.create<LocalServerExtension>("localServer")

            tasks {
                useModularJsTarget()

                configureEach<KotlinJsDce> {
                    enabled = false
                }

                configureEach<KotlinWebpack> {
                    if (name !in DEVELOPMENT_RUN_TASKS) {
                        enabled = false
                    }

                    outputFileName = LOCAL_SERVER_JS
                    sourceMaps = false
                }
            }

            afterEvaluate {
                val localServerRoot = extension.root
                    ?: return@afterEvaluate

                tasks.configureEach<PatchWebpackConfig> {
                    patch("output", outputConfiguration(localServerRoot))
                }
            }
        }
    }
}
