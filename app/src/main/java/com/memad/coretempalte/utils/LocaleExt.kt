package com.memad.coretempalte.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.provider.Settings.Global.getString
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.memad.coretempalte.R
import com.memad.coretempalte.utils.Constants.LANG_PREF
import java.util.*
import javax.inject.Inject


fun Context.recreateTask() {
    this.packageManager
        .getLaunchIntentForPackage(this.packageName)
        ?.let { intent ->
            val restartIntent = Intent.makeRestartActivityTask(intent.component)
            this.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
        }
}

/*
private val locales = arrayOf("العربية", "English")

@Inject
lateinit var preferencesHelper: SharedPreferencesHelper

private fun openLanguageSelectorDialog(
    currentLang: Int
) {
    val builder = MaterialAlertDialogBuilder(requireContext())
    builder.setTitle(getString(R.string.language))


    builder.setSingleChoiceItems(locales, currentLang) { _, which ->
        when (which) {
            0 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("ar")
                AppCompatDelegate.setApplicationLocales(appLocale)
                preferencesHelper.save(LANG_PREF, 0)
                requireActivity().recreate()
            }

            1 -> {
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
                AppCompatDelegate.setApplicationLocales(appLocale)
                preferencesHelper.save(LANG_PREF, 1)
                requireActivity().recreate()
            }
        }
    }
    builder.create().apply {
        show()
    }
}*/


class LocaleUtil  {
    companion object {
        val supportedLocales = listOf("en", "ar")
        const val OPTION_PHONE_LANGUAGE = "sys"

        private fun getLocaleFromPrefCode(prefCode: String): Locale{
            val localeCode = if(prefCode != OPTION_PHONE_LANGUAGE) {
                prefCode
            } else {
                val systemLang =
                    ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)?.language
                if(systemLang in supportedLocales){
                    systemLang
                } else {
                    "en"
                }
            }
            return Locale(localeCode)
        }

        fun getLocalizedConfiguration(prefLocaleCode: String): Configuration {
            val locale = getLocaleFromPrefCode(prefLocaleCode)
            return getLocalizedConfiguration(locale)
        }

        private fun getLocalizedConfiguration(locale: Locale): Configuration {
            val config = Configuration()
            return config.apply {
                config.setLayoutDirection(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    config.setLocale(locale)
                    val localeList = LocaleList(locale)
                    LocaleList.setDefault(localeList)
                    config.setLocales(localeList)
                } else {
                    config.setLocale(locale)
                }
            }
        }

        fun getLocalizedContext(baseContext: Context, prefLocaleCode: String): Context {
            val currentLocale = getLocaleFromPrefCode(prefLocaleCode)
            val baseLocale = getLocaleFromConfiguration(baseContext.resources.configuration)
            Locale.setDefault(currentLocale)
            return if (!baseLocale.toString().equals(currentLocale.toString(), ignoreCase = true)) {
                val config = getLocalizedConfiguration(currentLocale)
                baseContext.createConfigurationContext(config)
                baseContext
            } else {
                baseContext
            }
        }

        fun applyLocalizedContext(baseContext: Context, prefLocaleCode: String) {
            val currentLocale = getLocaleFromPrefCode(prefLocaleCode)
            val baseLocale = getLocaleFromConfiguration(baseContext.resources.configuration)
            Locale.setDefault(currentLocale)
            if (!baseLocale.toString().equals(currentLocale.toString(), ignoreCase = true)) {
                val config = getLocalizedConfiguration(currentLocale)
                baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            }
        }

        @Suppress("DEPRECATION")
        private fun getLocaleFromConfiguration(configuration: Configuration): Locale {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                configuration.locales.get(0)
            } else {
                configuration.locale
            }
        }

        fun getLocalizedResources(resources: Resources, prefLocaleCode: String): Resources {
            val locale = getLocaleFromPrefCode(prefLocaleCode)
            val config = resources.configuration
            @Suppress("DEPRECATION")
            config.locale = locale
            config.setLayoutDirection(locale)

            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
            return resources
        }
    }
}

class Storage(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)

    fun getPreferredLocale(): String {
        return preferences.getString("preferred_locale", LocaleUtil.OPTION_PHONE_LANGUAGE)!!
    }

    fun setPreferredLocale(localeCode: String) {
        preferences.edit().putString("preferred_locale", localeCode).apply()
    }
}