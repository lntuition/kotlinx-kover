/*
 * Copyright 2017-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.kover.gradle.plugin.commons

import kotlinx.kover.gradle.plugin.dsl.KoverNames.DEFAULT_HTML_REPORT_NAME
import kotlinx.kover.gradle.plugin.dsl.KoverNames.DEFAULT_VERIFY_REPORT_NAME
import kotlinx.kover.gradle.plugin.dsl.KoverNames.DEFAULT_XML_REPORT_NAME
import org.gradle.configurationcache.extensions.capitalized

/**
 * Name of the default Kover variant.
 */
internal const val DEFAULT_KOVER_VARIANT_NAME = ""

/**
 * Name of task to find online instrumentation agent jar file.
 */
internal const val FIND_JAR_TASK = "koverFindJar"

/**
 * Name for task for generating Kover artifact.
 */
internal fun artifactGenerationTaskName(variant: String) = "koverGenerateArtifact${variant.capitalized()}"

/**
 * Name for HTML reporting task for specified report namespace.
 */
internal fun htmlReportTaskName(variant: String) = "$DEFAULT_HTML_REPORT_NAME${variant.capitalized()}"

/**
 * Name for XML reporting task for specified report namespace.
 */
internal fun xmlReportTaskName(variant: String) = "$DEFAULT_XML_REPORT_NAME${variant.capitalized()}"

/**
 * Name for verifying task for specified report namespace.
 */
internal fun verifyTaskName(variant: String) = "$DEFAULT_VERIFY_REPORT_NAME${variant.capitalized()}"

/**
 * Name of raw report for specified test task name (without directory path).
 */
internal fun rawReportName(taskName: String, toolVendor: CoverageToolVendor): String {
    return "${taskName}.${toolVendor.rawReportExtension}"
}

/**
 * Name of transitive configuration to collect classpath of selected JVM runtime instrumentation agent.
 */
internal const val JVM_AGENT_CONFIGURATION_NAME = "koverJvmAgent"

/**
 * Name of transitive configuration to collect classpath of selected JVM report generator.
 */
internal const val JVM_REPORTER_CONFIGURATION_NAME = "koverJvmReporter"

/**
 * Name of the Gradle configuration for sharing Kover artifact.
 */
internal fun localArtifactConfigurationName(namespace: String): String = "koverArtifact${namespace.capitalized()}"

/**
 * Name of the Gradle configuration for collecting Kover artifacts from dependencies.
 */
internal fun externalArtifactConfigurationName(namespace: String): String = "koverExternalArtifacts${namespace.capitalized()}"
