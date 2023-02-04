package me.damlet.betterrisingblocks.states.states

import me.damlet.betterrisingblocks.BetterRisingBlocks.Companion.plugin
import net.minikloon.fsmgasm.StateSeries
import org.bukkit.scheduler.BukkitTask
import java.util.*
import java.util.function.Consumer

/**
 *  Runs a series of states and automatically starts the next one once
 *  the current state's duration is elapsed.
 *
 *  "interval" is the frequency (in ticks) that it checks to see if the time is up.
 *  Set to 1 by default (every tick).
 *
 *  Stolen from minikloon's forum post.
 */
class ScheduledStateSeries @JvmOverloads constructor(private val interval: Long = 1) : StateSeries()
{
    private var scheduledTask: BukkitTask? = null
    private var onUpdate: MutableList<Runnable> = LinkedList()

    public override fun onStart()
    {
        super.onStart()

        /*
        This starts a timer that called onUpdate in the *StateSeries* (AND) the current State.
        StateSeries::onUpdate() has the logic to check whether the duration is up.
         */
        scheduledTask = plugin.server.scheduler.runTaskTimer(plugin, Runnable {
            update()
            onUpdate.forEach(Consumer { obj: Runnable -> obj.run() }) //Run each onUpdate function
            //Note, BukkitRunnable and Runnable are virtually identical, BukkitRunnable just has some extra methods.
        }, 0L, interval)
    }

    public override fun onEnd()
    {
        super.onEnd()
        scheduledTask!!.cancel()
    }

    fun addOnUpdate(runnable: Runnable)
    {
        onUpdate.add(runnable)
    }
}