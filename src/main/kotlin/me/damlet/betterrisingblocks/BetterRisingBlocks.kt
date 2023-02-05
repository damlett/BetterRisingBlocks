package me.damlet.betterrisingblocks

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.listeners.DeathListener
import org.bukkit.plugin.java.JavaPlugin

class BetterRisingBlocks : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()

        plugin = this

        server.pluginManager.registerEvents(DeathListener(), this)
        GameManager.init()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        lateinit var plugin: BetterRisingBlocks
    }

}