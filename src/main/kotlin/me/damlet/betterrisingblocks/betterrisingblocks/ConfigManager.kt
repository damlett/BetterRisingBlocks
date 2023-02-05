package me.damlet.betterrisingblocks.betterrisingblocks

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin

object ConfigManager {

    var gracePeriodTime: Int
        get() = plugin.config.getInt("times.grace-period")
        set(value) {
            plugin.config.set("times.grace-period", value)
            plugin.saveConfig()
        }

    var pvpTime: Int
        get() = plugin.config.getInt("times.pvp")
        set(value) {
            plugin.config.set("times.pvp", value)
            plugin.saveConfig()
        }

    var borderTime: Int
        get() = plugin.config.getInt("times.border")
        set(value) {
            plugin.config.set("times.border", value)
            plugin.saveConfig()
        }

    fun keepInventory(state: String): Boolean {
        return plugin.config.getBoolean("keep-inventory.$state")
    }
}