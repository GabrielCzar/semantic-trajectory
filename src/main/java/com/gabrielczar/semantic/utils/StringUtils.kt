/*
 * Copyright (c) 2018 Gabriel César
 */

package com.gabrielczar.semantic.utils

import com.gabrielczar.semantic.utils.StringUtils.Companion.LOGGER
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 *
 * @author GabrielCzar
 * @since 1.0.0
 *
 * */

class StringUtils {
    companion object {
        val LOGGER : Logger = LoggerFactory.getLogger(StringUtils::class.java)
    }
}

private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

fun convertDateStringToLong (value : String) : Long {
    try {
        val parsedDate = dateFormat.parse(value)
        return Timestamp(parsedDate.time).time
    } catch (e: ParseException) {
        LOGGER.error(e.message)
    }
    return 0L
}
