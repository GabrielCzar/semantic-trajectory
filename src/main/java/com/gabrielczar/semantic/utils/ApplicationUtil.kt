/*
 * Copyright (c) 2018 Gabriel César
 */

package com.gabrielczar.semantic.utils;

import java.security.SecureRandom
import java.util.*

/**
 * General util functions
 *
 * @author GabrielCzar
 * @since 1.0.0
 *
 * */

fun generateToken() : String {
    return Base64
            .getUrlEncoder()
            .withoutPadding()
            .encodeToString(ByteArray(12).apply { SecureRandom().nextBytes(this) })
}

