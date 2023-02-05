package me.damlet.betterrisingblocks.states.game

import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.regions.CuboidRegion
import com.sk89q.worldedit.regions.Region
import com.sk89q.worldedit.world.block.BlockState
import me.damlet.betterrisingblocks.betterrisingblocks.ConfigManager
import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.states.GameState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration

class RisingBlocksState(private val border: Duration, private val solo: Boolean) : GameState() {
    override val duration: Duration
        get() = Duration.ZERO

    private val risingTask = object : BukkitRunnable() {

        var count = 0.0

        override fun run() {
            if (count > GameManager.risingTime) {
                val world = Bukkit.getWorlds()[0]

                WorldEdit.getInstance().newEditSession(BukkitWorld(world)).use { editSession ->
                    val region: Region = CuboidRegion(
                        BlockVector3.at((world.worldBorder.center.blockX - world.worldBorder.size / 2).toInt(),
                            GameManager.currentY,
                            (world.worldBorder.center.blockZ - world.worldBorder.size / 2).toInt()
                        ),
                        BlockVector3.at((world.worldBorder.center.blockX + world.worldBorder.size / 2).toInt(),
                            GameManager.currentY,
                            (world.worldBorder.center.blockZ + world.worldBorder.size / 2).toInt()
                        )
                    )
                    editSession.setBlocks(
                        region,
                        BlockState.get(GameManager.material.name.lowercase())
                    )
                    editSession.commit()
                    editSession.flushQueue()
                }

                GameManager.currentY = (GameManager.currentY + 1).coerceAtMost(318)
                count = 0.0
            }

            count += 0.25
        }

    }

    override fun onEnd() {

    }

    override fun onStart() {
        broadcast(
            Component.text("Lava Rising\n").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text("Hardcore mode is now ").color(NamedTextColor.DARK_AQUA)
                .append(Component.text("on").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .append(Component.text(". Blocks will begin to rise! The world border will shrink to 50 blocks in ${ConfigManager.borderTime} minutes. Keep inventory is ${if (ConfigManager.keepInventory("rising")) "on" else "off"}.").color(NamedTextColor.DARK_AQUA))
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
        )

        Bukkit.getWorlds()[0].worldBorder.setSize(50.0, border.seconds)

        scheduleRepeating(risingTask, 5, 5)
    }

    override fun onUpdate() {

    }

    override fun isReadyToEnd(): Boolean {
        var alivePlayers = 0
        for (player in GameManager.players) {
            if (player.gameMode != GameMode.SPECTATOR) alivePlayers++
        }
        return alivePlayers <= 1 && !solo
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        e.player.gameMode = GameMode.SPECTATOR

        e.keepInventory = ConfigManager.keepInventory("rising")
    }

}