package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import java.time.Duration

class AnnounceWinnerState : GameState() {

    override val duration: Duration
        get() = Duration.ZERO

    override fun onEnd() {

    }

    override fun onStart() {
        GameManager.players.forEach { player ->
            player.playSound(player.location, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)

            if (player.gameMode == GameMode.SPECTATOR) return@forEach

            broadcast(player.displayName().append(
                Component.text(" has won!").color(NamedTextColor.AQUA)
            ))
        }
    }

    override fun onUpdate() {

    }

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        e.isCancelled = true
    }
}