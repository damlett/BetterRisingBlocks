package me.damlet.betterrisingblocks.states.states

import net.minikloon.fsmgasm.State
import org.bukkit.entity.Player
import java.time.Duration

/**
 * Root of player states.
 * Takes a BWPlayer.
 * Has protected field "player" which is set to bwPlayer.player
 */
abstract class PlayerState(val player: Player) : State()
{
    override val duration: Duration
        get() = Duration.ZERO
}