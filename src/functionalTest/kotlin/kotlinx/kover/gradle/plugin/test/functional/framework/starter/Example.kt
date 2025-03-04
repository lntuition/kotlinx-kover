/*
 * Copyright 2017-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.kover.gradle.plugin.test.functional.framework.starter

import kotlinx.kover.gradle.plugin.test.functional.framework.checker.*
import kotlinx.kover.gradle.plugin.test.functional.framework.common.*
import kotlinx.kover.gradle.plugin.test.functional.framework.runner.*
import kotlinx.kover.gradle.plugin.util.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*
import java.io.*
import java.lang.reflect.*
import java.nio.file.*
import java.util.stream.*

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Test
@Extensions(ExtendWith(ExampleGradleTest::class))
internal annotation class ExamplesTest(
    val exampleDir: String,
    val commands: Array<String> = ["build"]
)

private const val EXAMPLES_DIR = "examples"

internal fun allExamples(): List<File> {
    return File(EXAMPLES_DIR).subdirs().flatMap { it.subdirs() }
}

private class ExampleGradleTest : DirectoryBasedGradleTest() {
    override fun readAnnotationArgs(element: AnnotatedElement?): RunCommand {
        val annotation = (element?.getAnnotation(ExamplesTest::class.java)
            ?: error("Test not marked by '${ExamplesTest::class.qualifiedName}' annotation"))

        val exampleDir = File(EXAMPLES_DIR, annotation.exampleDir)
        val exampleName = exampleDir.name
        val commands = annotation.commands.toList()

        return RunCommand(exampleName, exampleDir, commands)
    }

    override val testType: String = "Example"
}



