package me.damlet.betterrisingblocks.commands

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RisingBlocksCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size == 2) {
            if (args[0].lowercase() == "block") {
                val material = Material.getMaterial(args[1].uppercase()) ?: return false
                if (!material.isBlock) return false

                GameManager.material = material
                sender.sendMessage(Component.text("Block set to $material").color(NamedTextColor.GREEN))
            } else if (args[0].lowercase() == "time") {
                val num = args[1].toDoubleOrNull() ?: return false

                GameManager.risingTime = num
                sender.sendMessage(Component.text("Rising time set to $num").color(NamedTextColor.GREEN))
            }
        } else if (args.size == 1) {
            if (args[0].lowercase() == "skip") {
                GameManager.mainState.skip()
            }
        }
        return true
    }

}