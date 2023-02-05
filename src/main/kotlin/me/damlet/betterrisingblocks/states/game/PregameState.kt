package me.damlet.betterrisingblocks.states.game

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin
import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.betterrisingblocks.PlayerManager
import me.damlet.betterrisingblocks.commands.StartCommand
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.time.Duration

open class PregameState : GameState() {

    override val duration: Duration
        get() = Duration.ZERO

    var start = false

    override fun onEnd() {
        plugin.getCommand("start")!!.setExecutor(null)
    }

    override fun onStart() {
        val world = Bukkit.getWorlds()[0]

        players.forEach { player ->
            player.teleport(world.spawnLocation)
            player.gameMode = GameMode.ADVENTURE

            PlayerManager.reset(player, false)
        }

        world.worldBorder.center = world.spawnLocation
        world.worldBorder.size = 10.0

        broadcast(
            Component.text("WAITING...\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text("Waiting for the game to begin...").color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
        )

        plugin.getCommand("start")!!.setExecutor(StartCommand(this))
    }

    override fun onUpdate() {

    }

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        e.isCancelled = true
    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val world = Bukkit.getWorlds()[0]
        val player = e.player

        player.sendMessage(
            Component.text("WAITING...\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(
                    Component.text("Waiting for the game to begin...").color(NamedTextColor.DARK_AQUA)
                        .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE)
                )
        )

        player.teleport(world.spawnLocation)
        player.gameMode = GameMode.ADVENTURE
        PlayerManager.reset(player, false)
    }

    override fun isReadyToEnd(): Boolean {
        return start
    }

}