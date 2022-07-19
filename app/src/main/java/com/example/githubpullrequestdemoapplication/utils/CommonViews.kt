package com.example.githubpullrequestdemoapplication.utils

import android.content.Context
import com.example.mentalhealthpatient.ui.view.commonviews.classes.LoadingUtils
import java.text.SimpleDateFormat
import java.util.*

object CommonViews {
    @JvmStatic
    fun startLoading(context: Context, isCancelable: Boolean = true) {
        LoadingUtils.showDialog(context, isCancelable)
    }

    @JvmStatic
    fun hideLoading() {
        LoadingUtils.hideDialog()
    }

    @JvmStatic
    fun convertMilliSecondsToDate(finalResult: Long?, pattern: String?): String {
        val date = Date(finalResult!!)
        val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
        formatter.timeZone = TimeZone.getDefault()
        return formatter.format(date)
    }
}