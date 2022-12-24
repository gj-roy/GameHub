package ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization.fieldserializers

import ca.on.hojat.gamenews.core.data.api.igdbcalypse.Constants

internal class SingleFieldSerializerImpl(
    private val fieldChain: List<String>
) : FieldSerializer {

    override fun serialize(): String {
        return fieldChain.joinToString(Constants.HIERARCHICAL_FIELD_SEPARATOR)
    }
}
