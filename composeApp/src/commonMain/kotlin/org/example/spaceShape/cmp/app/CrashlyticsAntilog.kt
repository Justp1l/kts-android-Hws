package org.example.spaceShape.cmp.app

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

expect class CrashlyticsAntilog() : Antilog {
    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    )
}