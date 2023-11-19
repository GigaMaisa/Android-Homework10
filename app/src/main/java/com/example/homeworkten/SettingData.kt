package com.example.homeworkten

data class SettingData(
    val id: Int,
    val icon: Int,
    val settingText: String,
    val settingType: SettingType = SettingType.NORMAL_TYPE
)

enum class SettingType {
    NORMAL_TYPE,
    LANGUAGE_TYPE,
    DARK_MODE_TYPE,
}
