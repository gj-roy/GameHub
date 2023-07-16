package ca.on.hojat.gamenews.core.data.api.igdbcalypse.serialization

object ApicalypseSerializerFactory {

    fun create(): ApicalypseSerializer {
        return ApicalypseSerializerImpl()
    }
}
