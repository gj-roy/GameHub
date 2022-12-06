package ca.on.hojat.gamenews.api.igdbcalypse.serialization

interface ApicalypseSerializer {
    fun serialize(clazz: Class<*>): String
}
