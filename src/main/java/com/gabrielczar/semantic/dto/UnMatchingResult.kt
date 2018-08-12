package com.gabrielczar.semantic.dto;

import com.gabrielczar.semantic.entities.UnMatchingEntry

data class UnMatchingResult (
    val key: String,
    val entries: MutableList<UnMatchingEntry>
)