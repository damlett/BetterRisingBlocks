package me.damlet.betterrisingblocks.commands

import me.damlet.betterrisingblocks.states.game.PregameState
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class StartCommand(val pregameState: PregameState) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isNotEmpty()) return false

        pregameState.start = true
        return true
    }

}