package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.BRPlayer
import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.time.Duration

class GracePeriodState(override val duration: Duration) : GameState() {

    override fun onEnd() {

    }

    override fun onStart() {
        broadcast(
            Component.text("GRACE PERIOD\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text("Get stuff, pvp is off and will be turned on in 10 minutes").color(NamedTextColor.DARK_AQUA)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
        )
    }

    override fun onUpdate() {

    }

    @EventHandler
    fun onPVP(e: EntityDamageByEntityEvent) {
        if (e.entity !is Player) return
        if (e.damager !is Player) return

        e.isCancelled = true
    }
}