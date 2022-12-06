package ca.on.hojat.gamenews.api.igdbcalypse.serialization

object ApicalypseSerializerFactory {

    fun create(): ApicalypseSerializer {
        return ApicalypseSerializerImpl()
    }
}
