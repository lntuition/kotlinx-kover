/*
 * Copyright 2017-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.kover.gradle.plugin.test.functional.framework.checker

import kotlinx.kover.gradle.plugin.commons.*
import kotlinx.kover.gradle.plugin.test.functional.framework.common.*
import kotlinx.kover.gradle.plugin.tools.*
import java.io.*

internal interface CheckerContext {
    val project: ProjectAnalysisData

    val output: String

    /**
     * Perform built-in checks.
     */
    fun prepare(buildErrorExpected: Boolean)

    fun subproject(path: String, checker: CheckerContext.() -> Unit)

    fun output(checker: String.() -> Unit)

    fun taskOutput(taskNameOrPath: String, checker: String.() -> Unit)

    fun file(name: String, checker: File.() -> Unit)

    fun xml(filename: String, checker: XmlReportChecker.() -> Unit)

    fun verification(checker: VerifyReportChecker.() -> Unit)

    val defaultRawReport: String
    fun checkReports(xmlPath: String, htmlPath: String, mustExist: Boolean)
    fun checkOutcome(taskNameOrPath: String, expectedOutcome: String)
    fun taskNotCalled(taskNameOrPath: String)
    fun checkDefaultReports(mustExist: Boolean = true)
    fun checkDefaultRawReport(mustExist: Boolean = true)
}

/**
 * Static info about Gradle project.
 */
internal interface ProjectAnalysisData {
    val path: String

    val rootDir: File

    val buildDir: File

    val definedKoverVersion: String?

    val toolVariant: CoverageToolVariant

    val language: ScriptLanguage

    val buildScript: String

    val kotlinPlugin: AppliedKotlinPlugin

    fun allProjects(): List<ProjectAnalysisData>
}


internal interface Counter {
    fun assertAbsent()

    fun assertFullyMissed()

    fun assertCovered()

    fun assertCoveredPartially()

    fun assertTotal(expectedTotal: Int)

    fun assertCovered(covered: Int, missed: Int)

    fun assertFullyCovered()
}

internal interface VerifyReportChecker {
    fun assertKoverResult(expected: String)
    fun assertJaCoCoResult(expected: String)
}

internal interface XmlReportChecker {
    fun classCounter(className: String, type: String = "INSTRUCTION"): Counter
    fun methodCounter(className: String, methodName: String, type: String = "INSTRUCTION"): Counter
}
