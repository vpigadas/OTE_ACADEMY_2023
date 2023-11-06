package gr.ote.academy.mvvm.exceptions

import gr.ote.academy.BuildConfig

data class NetworkException(
    val status: Int,
    override val message: String? = null,
    override val cause: Throwable? = null,
    val enableSuppression: Boolean = true,
    val writableStackTrace: Boolean = BuildConfig.DEBUG
) : Exception(message, cause, enableSuppression, writableStackTrace)