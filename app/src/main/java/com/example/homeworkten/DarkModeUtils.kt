package com.example.homeworkten

import android.annotation.SuppressLint
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

class DarkModeUtils {
    companion object {
        fun changeDesignToDarkMode(isDark: Boolean) {
            if (isDark) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        fun isDarkModeChecked(@SuppressLint("UseSwitchCompatOrMaterialCode") button: Switch) {
            button.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        }
    }
}