package ca.on.hojat.gamenews.shared.database.articles

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ca.on.hojat.gamenews.shared.core.JsonConverter
import ca.on.hojat.gamenews.shared.database.articles.entities.DbImageType
import ca.on.hojat.gamenews.shared.database.common.RoomTypeConverter
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

@ProvidedTypeConverter
@BindType(contributesTo = BindType.Collection.SET)
internal class ArticlesTypeConverter @Inject constructor(
    private val jsonConverter: JsonConverter
) : RoomTypeConverter {

    @TypeConverter
    fun fromImageUrls(imageUrls: Map<DbImageType, String>): String {
        return jsonConverter.toJson(imageUrls)
    }

    @TypeConverter
    fun toImageUrls(json: String): Map<DbImageType, String> {
        return (jsonConverter.fromJson(json) ?: emptyMap())
    }
}
