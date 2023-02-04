package me.damlet.betterrisingblocks.states.player

import me.damlet.betterrisingblocks.betterrisingblocks.BRPlayer
import me.damlet.betterrisingblocks.states.states.PlayerState
import org.bukkit.GameMode

class PlayerAliveState(val brPlayer: BRPlayer) : PlayerState(brPlayer.player) {

    override fun onEnd() {

    }

    override fun onStart() {
        player.gameMode = GameMode.SURVIVAL
        brPlayer.eliminated = false
        brPlayer.reset(false)
    }

    override fun onUpdate() {

    }

}