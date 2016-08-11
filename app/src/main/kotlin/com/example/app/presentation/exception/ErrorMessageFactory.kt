package com.example.app.presentation.exception

import android.content.Context
import com.example.app.BuildConfig
import com.example.app.R
import com.example.app.data.exception.NetworkConnectionException
import com.example.app.data.exception.ServerIssueException

/**
 * Factory used to create error messages from an Exception as a condition.
 */
object ErrorMessageFactory {

    /**
     * Creates a String representing an error message.

     * @param context Context needed to retrieve string resources.
     * *
     * @param exception An exception used as a condition to retrieve the correct error message.
     * *
     * @return [String] an error message.
     */
    fun create(context: Context, exception: Exception): String {

        if (BuildConfig.DEBUG) {
            return exception.message.toString()
        } else if (exception is NetworkConnectionException) {
            return context.getString(R.string.exception_message_no_connection)
        } else if (exception is ServerIssueException) {
            return context.getString(R.string.exception_message_server_issue)
        }

        return context.getString(R.string.exception_message_generic)
    }
}
