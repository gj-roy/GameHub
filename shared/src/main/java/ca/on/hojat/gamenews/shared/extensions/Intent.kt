@file:JvmName("IntentUtils")
@file:Suppress("UNCHECKED_CAST")

package ca.on.hojat.gamenews.shared.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity

fun Intent.getStringExtra(key: String, default: String): String {
    return (getStringExtra(key) ?: default)
}

fun Intent.getStringExtraOrThrow(key: String): String {
    return checkNotNull(getStringExtra(key)) {
        "The intent does not contain a string value with the key: $key."
    }
}

fun Intent.getBundleExtra(key: String, default: Bundle): Bundle {
    return (getBundleExtra(key) ?: default)
}

fun Intent.getBundleExtraOrThrow(key: String): Bundle {
    return checkNotNull(getBundleExtra(key)) {
        "The intent does not contain a bundle value with the key: $key."
    }
}

fun <T> Intent.getSerializableExtra(key: String, default: T): T {
    return ((getSerializableExtra(key) as? T) ?: default)
}

fun <T : Any> Intent.getSerializableExtraOrThrow(key: String): T {
    return checkNotNull(getSerializableExtra(key) as? T) {
        "The intent does not contain a serializable value with the key: $key."
    }
}

fun <T : Parcelable> Intent.getParcelableExtra(key: String, default: T): T {
    return (getParcelableExtra(key) ?: default)
}

fun <T : Parcelable> Intent.getParcelableOrThrow(key: String): T {
    return checkNotNull(getParcelableExtra(key)) {
        "The intent does not contain a parcelable value with the key: $key."
    }
}

fun Intent.singleTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
}

fun Intent.newTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun Intent.multipleTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
}

fun Intent.clearTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

fun Intent.clearTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
}


fun Intent.attachNewTaskFlagIfNeeded(context: Context) {
    if (context !is ComponentActivity) {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}
