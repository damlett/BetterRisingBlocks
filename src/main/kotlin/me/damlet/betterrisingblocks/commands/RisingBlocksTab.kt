package me.damlet.betterrisingblocks.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class RisingBlocksTab: TabCompleter {

    private val firstArgs = listOf("block", "time", "skip")

    private val blocks = mutableListOf<String>()
    private val nums = listOf("0.01", "1", "10", "100")

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        if (blocks.isEmpty()) {
            for (material in Material.values()) {
                if (material.isBlock) {
                    blocks.add(material.name.lowercase())
                }
            }
        }

        val strings: MutableList<String> = mutableListOf()

        if (args.size == 1) {
            for (arg in firstArgs) {
                if (arg.startsWith(args[0])) strings.add(arg)
            }
        } else if (args.size == 2) {
            if (args[0].equals("block", true)) {
                for (arg in blocks) {
                    if (arg.startsWith(args[1])) strings.add(arg)
                }
            } else if (args[0].equals("time", true)) {
                for (arg in nums) {
                    if (arg.startsWith(args[1])) strings.add(arg)
                }
            }
        }

        return strings
    }

}