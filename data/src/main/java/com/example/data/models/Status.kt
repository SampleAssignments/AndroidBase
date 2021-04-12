package com.example.data.models

data class Status(
    val asap_available: Boolean,
    val asap_minutes_range: List<Int>,
    val pickup_available: Boolean,
    val scheduled_available: Boolean,
    val unavailable_reason: Any
)