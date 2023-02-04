package me.damlet.betterrisingblocks.states.player

import me.damlet.betterrisingblocks.betterrisingblocks.BRPlayer
import me.damlet.betterrisingblocks.states.states.PlayerState
import org.bukkit.GameMode

class PlayerDeadState(val brPlayer: BRPlayer) : PlayerState(brPlayer.player) {

    override fun onEnd() {

    }

    override fun onStart() {
        player.gameMode = GameMode.SPECTATOR
        brPlayer.eliminated = true
    }

    override fun onUpdate() {

    }

}