package ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization

interface ApicalypseSerializer {
    fun serialize(clazz: Class<*>): String
}
