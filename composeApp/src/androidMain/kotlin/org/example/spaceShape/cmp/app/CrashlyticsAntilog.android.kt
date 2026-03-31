package org.example.spaceShape.cmp.app

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

actual class CrashlyticsAntilog : Antilog() {
    private val crashlytics = FirebaseCrashlytics.getInstance()
    actual override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        val fullMessage = buildString {
            tag?.let { append(it) }
            message?.let { append(it) }
        }
        crashlytics.log("${priority.name}: $fullMessage")

        if (priority >= LogLevel.ERROR && throwable != null) {
            crashlytics.recordException(throwable)
        }
    }

}