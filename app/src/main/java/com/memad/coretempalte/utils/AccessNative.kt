package com.memad.coretempalte.utils

object AccessNative {
    init {
        System.loadLibrary("api-keys")
    }

    external fun getApiKey(): String
}