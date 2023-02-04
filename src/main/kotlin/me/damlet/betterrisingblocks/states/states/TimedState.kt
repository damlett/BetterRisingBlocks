package me.damlet.betterrisingblocks.states.states

import net.minikloon.fsmgasm.State
import java.time.Duration

class TimedState(override val duration: Duration) : State()
{
    override fun onEnd()
    {
        println("Timed End")
    }

    override fun onStart()
    {
        println("Timed Start, waiting for ${duration.toSeconds()} seconds")
    }

    override fun onUpdate()
    {

    }
}