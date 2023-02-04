package me.damlet.betterrisingblocks.betterrisingblocks

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin
import me.damlet.betterrisingblocks.commands.RisingBlocksCommand
import me.damlet.betterrisingblocks.commands.RisingBlocksTab
import me.damlet.betterrisingblocks.states.game.*
import me.damlet.betterrisingblocks.states.states.ScheduledStateSeries
import org.bukkit.Material
import org.bukkit.entity.Player
import java.time.Duration

object GameManager {

    val players = mutableSetOf<BRPlayer>()

    var material = Material.LAVA
    var risingTime = 10.0
    var currentY = -64

    lateinit var mainState: ScheduledStateSeries

    fun init() {
        mainState = ScheduledStateSeries()

        mainState.add(PregameState())
        mainState.add(StartGameState())
        mainState.add(GracePeriodState(Duration.ofMinutes(10)))
        mainState.add(PVPState(Duration.ofMinutes(5)))
        mainState.add(RisingBlocksState(false))
        mainState.add(AnnounceWinnerState())

        mainState.start()

        plugin.getCommand("risingblocks")!!.setExecutor(RisingBlocksCommand())
        plugin.getCommand("risingblocks")!!.tabCompleter = RisingBlocksTab()
    }

    fun getBRPlayer(player: Player): BRPlayer? {
        for (brPlayer in players) {
            if (brPlayer.player.name == player.name) return brPlayer
        }
        return null
    }
}