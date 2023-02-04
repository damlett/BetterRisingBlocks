package me.damlet.betterrisingblocks.states.states

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin
import net.kyori.adventure.text.Component
import net.minikloon.fsmgasm.State
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitTask
import java.util.function.Consumer

abstract class GameState: State(), Listener {

    protected val listeners: MutableSet<Listener> = HashSet()
    protected val tasks: MutableSet<BukkitTask> = HashSet()

    override fun start() {
        super.start()
        register(this)
    }

    override fun end() {
        super.end()
        if (!super.ended) return
        listeners.forEach(Consumer { listener: Listener ->
            HandlerList.unregisterAll(
                listener
            )
        })
        tasks.forEach(Consumer { obj: BukkitTask -> obj.cancel() })
        listeners.clear()
        tasks.clear()
    }

    protected val players: Collection<Player>
        get()
        = Bukkit.getOnlinePlayers()

    protected fun broadcast(message: String) {
        players.forEach { p: Player -> p.sendMessage(message) }
    }

    protected fun broadcast(message: Component) {
        players.forEach { p: Player -> p.sendMessage(message) }
    }

    protected fun register(listener: Listener) {
        listeners.add(listener)
        plugin.server.pluginManager.registerEvents(listener, plugin)
    }

    protected fun schedule(runnable: Runnable, delay: Long) {
        val task = plugin.server.scheduler.runTaskLater(plugin, runnable, delay)
        tasks.add(task)
    }

    protected fun scheduleRepeating(runnable: Runnable, delay: Long, interval: Long) {
        val task = plugin.server.scheduler.runTaskTimer(plugin, runnable, delay, interval)
        tasks.add(task)
    }

}