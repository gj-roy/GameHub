package ca.on.hojat.gamenews.core.data.api.igdb.extractors

import ca.on.hojat.gamenews.core.data.api.igdb.common.errorextractors.TwitchErrorMessageExtractor
import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.Json
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

internal class TwitchErrorMessageExtractorTest {

    private lateinit var sut: TwitchErrorMessageExtractor

    @Before
    fun setup() {
        sut = TwitchErrorMessageExtractor(Json)
    }

    @Test
    fun `Extracts twitch error message successfully`() {
        val responseBody = """
            {
                "status":403,
                "message": "invalid client secret"
            }
        """.trimIndent()

        assertThat(sut.extract(responseBody)).isEqualTo("invalid client secret")
    }

    @Test
    fun `Throws exception when twitch response body is not json`() {
        assertThrows(IllegalStateException::class.java) {
            sut.extract("hello there")
        }
    }

    @Test
    fun `Throws exception when twitch response body does not have message field`() {
        val responseBody = """
            {
                "status":403,
                "error": "invalid client secret"
            }
        """.trimIndent()

        assertThrows(IllegalStateException::class.java) {
            sut.extract(responseBody)
        }
    }
}
