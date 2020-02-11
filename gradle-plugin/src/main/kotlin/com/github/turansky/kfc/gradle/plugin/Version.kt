package com.github.turansky.kfc.gradle.plugin

private val SNAPSHOT_SUFFIX = "-SNAPSHOT"
private val DELIMITER = "."

internal fun parseVersion(source: String): Version {
    val version = source.removeSuffix(SNAPSHOT_SUFFIX)

    val parts = version
        .split(DELIMITER)
        .map { it.toInt() }

    check(parts.size == 3)

    return Version(
        major = parts[0],
        minor = parts[1],
        patch = parts[2],
        snapshot = version != source
    )
}

internal data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val snapshot: Boolean
) {
    override fun toString(): String {
        val version = sequenceOf(major, minor, patch)
            .joinToString(DELIMITER)

        return if (snapshot) {
            "$version$SNAPSHOT_SUFFIX"
        } else {
            version
        }
    }
}
