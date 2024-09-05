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
        yearsAgo > 0 -> if (yearsAgo > 1) "$yearsAgo years" else "1 year"
        monthsAgo > 0 -> if (monthsAgo > 1) "$monthsAgo months" else "1 month"
        weeksAgo > 0 -> if (weeksAgo > 1) "$weeksAgo weeks" else "1 week"
        daysAgo > 0 -> if (daysAgo > 1) "$daysAgo days" else "1 day"
        hoursAgo > 0 -> if (hoursAgo > 1) "$hoursAgo hours" else "1 hour"
        minsAgo > 0 -> if (minsAgo > 1) "$minsAgo mins" else "1 min"
        else -> if (secsAgo > 1) "$secsAgo seconds" else "1 second"
    }

    return "$time ago"
}
