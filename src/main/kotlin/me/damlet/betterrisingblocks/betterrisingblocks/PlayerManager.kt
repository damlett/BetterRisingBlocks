package me.damlet.betterrisingblocks.betterrisingblocks

import org.bukkit.entity.Player

object PlayerManager {

    fun reset(player: Player, clear: Boolean) {
        player.health = player.healthScale
        player.saturation = 5f
        player.foodLevel = 20
        player.fallDistance = 0f
        player.fireTicks = 0
        player.freezeTicks = 0
        if (clear) player.inventory.contents = null
    }
}