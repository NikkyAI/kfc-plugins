package com.github.turansky.kfc.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateExportAlias : DefaultTask() {
    @get:Input
    var export: String? = null

    @get:OutputFile
    val entry: File
        get() = getEntry()

    private fun getEntry(createMode: Boolean = false): File =
        project.jsPackageDir
            .resolve("kotlin-export-alias")
            .also { if (createMode) it.mkdir() }
            .resolve("index.js")

    @TaskAction
    private fun generate() {
        val content = alias(
            moduleName = project.jsProjectId,
            export = checkNotNull(export)
        )
        getEntry(true).writeText(content)
    }
}

// language=JavaScript
private fun alias(
    moduleName: String,
    export: String
): String = """
        |import * as source from '$moduleName'
        |
        |export default source.$export
    """.trimMargin()