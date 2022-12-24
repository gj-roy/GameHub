package ca.on.hojat.gamenews.core.data.api.igdbcalypse.serialization

interface ApicalypseSerializer {
    fun serialize(clazz: Class<*>): String
}
