package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.betterrisingblocks.PlayerManager
import me.damlet.betterrisingblocks.states.states.GameState
import org.bukkit.Bukkit
import org.bukkit.GameMode
import java.time.Duration

class StartGameState: GameState() {
    override val duration: Duration
        get() = Duration.ZERO

    override fun onEnd() {

    }

    override fun onStart() {
        GameManager.players.forEach { player ->
            player.gameMode = GameMode.SURVIVAL
            PlayerManager.reset(player, true)
        }

        GameManager.currentY = -64
        Bukkit.getWorlds()[0].worldBorder.setSize(300.0, 5)
    }

    override fun onUpdate() {

    }
}