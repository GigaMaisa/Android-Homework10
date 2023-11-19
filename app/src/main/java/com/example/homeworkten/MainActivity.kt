package com.example.homeworkten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkten.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: SettingsAdapter
    private var settingsList = mutableListOf<SettingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        initializeRecycler()
        fillSettingsList()
        clickDarkMode()
    }

    private fun initializeRecycler() {
        recyclerView = binding.rvSettings
        recyclerAdapter = SettingsAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setData(settingsList)
    }

    private fun clickDarkMode() {
        recyclerAdapter.onDarkModeClick = {
            DarkModeUtils.changeDesignToDarkMode(it)
        }
    }

    private fun fillSettingsList() {
        settingsList.also {
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_profile, "Edit Profile"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_location, "Address"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_notification, "Notification"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_wallet, "Payment"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_security, "Security"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_language, "Language", SettingType.LANGUAGE_TYPE))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_dark_mode, "Dark Mode", SettingType.DARK_MODE_TYPE))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_privacy_policy, "Privacy Policy"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_help_center, "Help Center"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_people, "Invite Friends"))
            it.add(SettingData(generateRandomNumber(), R.drawable.ic_logout, "Logout"))
        }
    }

    private fun generateRandomNumber(): Int {
        val random = Random(1000)
        return random.nextInt()
    }
}