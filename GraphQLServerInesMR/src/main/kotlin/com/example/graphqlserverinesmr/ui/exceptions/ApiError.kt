package com.example.graphqlserverinesmr.ui.exceptions

import java.time.LocalDateTime

data class ApiError(
        val message: String,
        val time: LocalDateTime
)