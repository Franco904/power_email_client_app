package com.example.power_email_client.core.presentation.utils

import java.util.Calendar
import java.util.concurrent.TimeUnit

typealias Timestamp = Long

fun Timestamp.toPresentationText(): String {
    val now = Calendar.getInstance().timeInMillis
    val intervalMillis = now - this

    val secsAgo = TimeUnit.MILLISECONDS.toSeconds(intervalMillis)
    if (secsAgo == 0L) return "Now"

    val minsAgo = secsAgo / 60
    val hoursAgo = minsAgo / 60
    val daysAgo = hoursAgo / 24
    val weeksAgo = daysAgo / 7
    val monthsAgo = daysAgo / 12
    val yearsAgo = daysAgo / 365

    val time = when {
        yearsAgo > 0 -> "$yearsAgo years"
        monthsAgo > 0 -> "$monthsAgo months"
        weeksAgo > 0 -> "$weeksAgo weeks"
        daysAgo > 0 -> "$daysAgo days"
        hoursAgo > 0 -> "$hoursAgo hours"
        minsAgo > 0 -> "$minsAgo mins"
        else -> "$secsAgo seconds"
    }

    return "$time ago"
}