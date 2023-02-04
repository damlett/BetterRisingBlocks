package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.BRPlayer
import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import java.time.Duration

class PVPState(override val duration: Duration) : GameState() {


    override fun onEnd() {

    }

    override fun onStart() {
        broadcast(
            Component.text("PVP\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text("PVP has been enabled. The lava will rise in 5 minutes").color(NamedTextColor.DARK_AQUA)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
        )
    }

    override fun onUpdate() {

    }
}