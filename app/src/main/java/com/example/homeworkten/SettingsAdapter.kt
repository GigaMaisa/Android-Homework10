package com.example.homeworkten

import android.view.LayoutInflater
import com.example.homeworkten.databinding.RecyclerSimpleItemBinding
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkten.databinding.RecyclerDarkModeItemBinding
import com.example.homeworkten.databinding.RecyclerLanguageItemBinding

class SettingsAdapter : ListAdapter<SettingData ,RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<SettingData>() {
        override fun areItemsTheSame(oldItem: SettingData, newItem: SettingData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SettingData, newItem: SettingData): Boolean {
            return oldItem == newItem
        }
    })
{
    companion object {
        const val NORMAL_SETTING = 0
        const val LANGUAGE_SETTING = 1
        const val DARK_MODE_SETTING = 2
    }

    fun setData(list: List<SettingData>) {
        submitList(list)
    }

    var onDarkModeClick: ((Boolean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LANGUAGE_SETTING -> LanguageSettingViewHolder(RecyclerLanguageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            DARK_MODE_SETTING -> DarkModeSettingViewHolder(RecyclerDarkModeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> {SettingViewHolder(RecyclerSimpleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SettingViewHolder -> holder.bindData()
            is LanguageSettingViewHolder -> holder.bind()
            is DarkModeSettingViewHolder -> {
                holder.bind()
                holder.changeToDarkMode()
            }
        }
    }

    inner class SettingViewHolder(private val binding: RecyclerSimpleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() = with(binding) {
            with(currentList[adapterPosition]) {
                ivIcon.setImageResource(icon)
                tvSettingName.text = settingText

                if (settingText == "Logout") {
                    tvSettingName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red))
                }
            }
        }
    }

    inner class LanguageSettingViewHolder(private val binding: RecyclerLanguageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val setting = currentList[adapterPosition]
            with(binding) {
                ivIcon.setImageResource(setting.icon)
                tvSettingName.text = setting.settingText
                tvLanguage.text = binding.root.context.getString(R.string.english_us)
            }
        }
    }

    inner class DarkModeSettingViewHolder(private val binding: RecyclerDarkModeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val setting = currentList[adapterPosition]
            with(binding) {
                ivIcon.setImageResource(setting.icon)
                tvSettingName.text = setting.settingText
                DarkModeUtils.isDarkModeChecked(toggleSwitch)
            }
        }

        fun changeToDarkMode() {
            binding.toggleSwitch.setOnCheckedChangeListener {_, isChecked ->
                onDarkModeClick?.invoke(isChecked)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].settingType == SettingType.NORMAL_TYPE) {
            return NORMAL_SETTING
        } else if (currentList[position].settingType == SettingType.LANGUAGE_TYPE) {
            return LANGUAGE_SETTING
        }
        return DARK_MODE_SETTING
    }
}