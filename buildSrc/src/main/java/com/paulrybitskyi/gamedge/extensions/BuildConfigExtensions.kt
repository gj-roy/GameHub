package com.paulrybitskyi.gamedge.extensions

import com.android.build.api.dsl.VariantDimension

fun VariantDimension.stringField(name: String, value: String) {
    buildConfigField("String", name, "\"$value\"")
}
