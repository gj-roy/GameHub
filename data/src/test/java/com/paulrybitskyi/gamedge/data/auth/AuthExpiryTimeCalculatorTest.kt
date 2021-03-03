/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.gamedge.data.auth

import com.paulrybitskyi.gamedge.core.providers.TimestampProvider
import com.paulrybitskyi.gamedge.data.auth.datastores.local.AUTH_TOKEN_TTL_DEDUCTION
import com.paulrybitskyi.gamedge.data.auth.datastores.local.AuthExpiryTimeCalculator
import com.paulrybitskyi.gamedge.data.auth.entities.OauthCredentials
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


private const val CURRENT_TIMESTAMP = 10_000L


internal class AuthExpiryTimeCalculatorTest {


    private lateinit var calculator: AuthExpiryTimeCalculator


    @Before
    fun setup() {
        calculator = AuthExpiryTimeCalculator(
            timestampProvider = FakeTimestampProvider()
        )
    }


    @Test
    fun `Calculates expiry time successfully`() {
        val credentials = OauthCredentials(
            accessToken = "access_token",
            tokenType = "Bearer",
            tokenTtl = 5000L
        )

        val expiryTime = calculator.calculateExpiryTime(credentials)
        val expected = (CURRENT_TIMESTAMP + TimeUnit.SECONDS.toMillis(credentials.tokenTtl) - AUTH_TOKEN_TTL_DEDUCTION)

        assertEquals(expected, expiryTime)
    }


    private class FakeTimestampProvider : TimestampProvider {

        override fun getUnixTimestamp(timeUnit: TimeUnit): Long {
            return CURRENT_TIMESTAMP
        }

    }


}