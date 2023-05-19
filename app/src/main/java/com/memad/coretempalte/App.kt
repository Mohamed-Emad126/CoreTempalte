package com.memad.coretempalte

import android.app.Application
import android.content.Context
import com.memad.coretempalte.utils.LocaleUtil
import com.memad.coretempalte.utils.Storage
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    val storage : Storage by lazy {
        Storage(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtil.getLocalizedContext(base, Storage(base).getPreferredLocale()))
    }
}