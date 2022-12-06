package ca.on.hojat.gamenews.api.igdbcalypse.serialization.fieldserializers

internal object FieldSerializerFactory {

    fun create(
        fieldChain: List<String>,
        children: List<FieldSerializer>
    ): FieldSerializer {
        return if (children.isEmpty()) {
            SingleFieldSerializerImpl(fieldChain)
        } else {
            CompositeFieldSerializer(children)
        }
    }
}
