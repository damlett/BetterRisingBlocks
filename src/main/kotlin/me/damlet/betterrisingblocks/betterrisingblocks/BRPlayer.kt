package me.damlet.betterrisingblocks.betterrisingblocks

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin
import net.minikloon.fsmgasm.StateSwitch
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class BRPlayer(var player: Player) : Listener {

    val status: StateSwitch = StateSwitch()
    var eliminated = false
    private val id = player.uniqueId

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    fun reset(clear: Boolean) {
        player.health = player.healthScale
        player.saturation = 5f
        player.foodLevel = 20
        player.fallDistance = 0f
        player.fireTicks = 0
        player.freezeTicks = 0
        if (clear) player.inventory.contents = null
    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        if (e.player.uniqueId == id) player = e.player
    }

}