@file:JvmName("AlarmManagerUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import ca.on.hojat.gamenews.shared.core.SdkInfo

@SuppressLint("NewApi")
fun AlarmManager.setAlarm(triggerAtMillis: Long, pendingIntent: PendingIntent) {
    if (SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
        setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    } else if (SdkInfo.IS_AT_LEAST_KITKAT && !SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
        setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    } else {
        set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }
}
