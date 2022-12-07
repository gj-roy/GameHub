package ca.on.hojat.gamenews.shared.api.gamespot.articles.entities

import ca.on.hojat.gamenews.shared.api.gamespot.articles.entities.ApiImageType.Companion.asImageType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ImageTypeSerializer::class)
enum class ApiImageType(val rawType: String) {
    UNKNOWN("unknown"),
    SQUARE_TINY("square_tiny"),
    SQUARE_SMALL("square_small"),
    SCREEN_TINY("screen_tiny"),
    ORIGINAL("original");

    internal companion object {

        fun String.asImageType(): ApiImageType {
            return values().find { it.rawType == this } ?: UNKNOWN
        }
    }
}

internal object ImageTypeSerializer : KSerializer<ApiImageType> {

    override val descriptor = PrimitiveSerialDescriptor(
        checkNotNull(ImageTypeSerializer::class.qualifiedName),
        PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: ApiImageType) {
        encoder.encodeString(value.rawType)
    }

    override fun deserialize(decoder: Decoder): ApiImageType {
        return decoder.decodeString().asImageType()
    }
}
