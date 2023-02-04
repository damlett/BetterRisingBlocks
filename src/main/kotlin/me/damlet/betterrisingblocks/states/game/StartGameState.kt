package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.player.PlayerAliveState
import me.damlet.betterrisingblocks.states.states.GameState
import org.bukkit.Bukkit
import java.time.Duration

class StartGameState: GameState() {
    override val duration: Duration
        get() = Duration.ZERO

    override fun onEnd() {

    }

    override fun onStart() {
        GameManager.players.forEach { brPlayer ->
            brPlayer.status.changeState(PlayerAliveState(brPlayer))
        }
        GameManager.currentY = -64
        Bukkit.getWorlds()[0].worldBorder.setSize(300.0, 5)
    }

    override fun onUpdate() {

    }
}