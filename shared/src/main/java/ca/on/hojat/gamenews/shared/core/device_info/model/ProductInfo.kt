package ca.on.hojat.gamenews.shared.core.device_info.model

data class ProductInfo(
    val modelName: String,
    val productName: String,
    val manufacturerName: String
) {

    val name: String
        get() = "$modelName $productName $manufacturerName"
}
