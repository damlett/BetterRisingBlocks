package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.ConfigManager
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import java.time.Duration

class PVPState(override val duration: Duration) : GameState() {


    override fun onEnd() {

    }

    override fun onStart() {
        broadcast(
            Component.text("PVP\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text("PVP has been enabled. The blocks will rise in ${ConfigManager.pvpTime} minutes. Keep inventory is ${if (ConfigManager.keepInventory("pvp")) "on" else "off"}.").color(NamedTextColor.DARK_AQUA)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
        )
    }

    override fun onUpdate() {

    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        e.keepInventory = ConfigManager.keepInventory("pvp")
    }
}