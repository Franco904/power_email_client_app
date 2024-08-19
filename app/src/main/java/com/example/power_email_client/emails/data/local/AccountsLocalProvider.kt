package com.example.power_email_client.emails.data.local

import com.example.power_email_client.R
import com.example.power_email_client.core.domain.models.Account

object AccountsLocalProvider {
    val defaultAccount get() = allAccounts.first()

    private val allAccounts = listOf(
        Account(
            id = 4L,
            fullName = "Tracy Alvarez",
            email = "tracealvie@altostrat.com",
            pictureId = R.drawable.avatar_2,
        ),
        Account(
            id = 5L,
            fullName = "Allison Trabucco",
            email = "atrabucco222@altostrat.com",
            pictureId = R.drawable.avatar_3,
        ),
        Account(
            id = 6L,
            fullName = "Ali ors",
            email = "aliconnors@altostrat.com",
            pictureId = R.drawable.avatar_5,
        ),
        Account(
            id = 7L,
            fullName = "Alberto Williams",
            email = "albertowilliams124@altostrat.com",
            pictureId = R.drawable.avatar_0,
        ),
        Account(
            id = 8L,
            fullName = "Kim len",
            email = "alen13@altostrat.com",
            pictureId = R.drawable.avatar_7,
        ),
        Account(
            id = 9L,
            fullName = "Google Express",
            email = "express@altostrat.com",
            pictureId = R.drawable.avatar_express,
        ),
        Account(
            id = 10L,
            fullName = "Sandra Adams",
            email = "sandraadams@altostrat.com",
            pictureId = R.drawable.avatar_1,
        ),
        Account(
            id = 11L,
            fullName = "Trevor Hansen",
            email = "trevorhandsen@altostrat.com",
            pictureId = R.drawable.avatar_8,
        ),
        Account(
            id = 12L,
            fullName = "Sean Holt",
            email = "sholt@altostrat.com",
            pictureId = R.drawable.avatar_6,
        ),
        Account(
            id = 13L,
            fullName = "Frank Hawkins",
            email = "fhawkank@altostrat.com",
            pictureId = R.drawable.avatar_4,
        )
    )

    fun findById(accountId: Long): Account {
        return allAccounts.find { it.id == accountId } ?: defaultAccount
    }
}