package me.damlet.betterrisingblocks.listeners

import me.damlet.betterrisingblocks.betterrisingblocks.GameManager
import me.damlet.betterrisingblocks.states.game.RisingBlocksState
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent

class DeathListener : Listener {

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        e.deathMessage(
            getDeathIcon(e.player.lastDamageCause?.cause)
                .append(Component.text(" ")
                    .append(e.player.displayName().color(NamedTextColor.WHITE))
                    .append(getDeathMessage(e.player.lastDamageCause?.cause, e.player.killer).color(NamedTextColor.WHITE)))
        )
    }

    private fun getDeathIcon(cause: EntityDamageEvent.DamageCause?): Component {
        return when (cause) {
            EntityDamageEvent.DamageCause.ENTITY_ATTACK
                , EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK ->
                Component.text("⚔").color(NamedTextColor.WHITE)
            EntityDamageEvent.DamageCause.FIRE
                , EntityDamageEvent.DamageCause.FIRE_TICK
                , EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
                , EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
                , EntityDamageEvent.DamageCause.LAVA
                , EntityDamageEvent.DamageCause.LIGHTNING ->
                Component.text("\uD83D\uDD25").color(TextColor.color(255, 154, 54))
            EntityDamageEvent.DamageCause.VOID ->
                Component.text("⬛").color(NamedTextColor.BLACK)
            else ->
                Component.text("☠").color(NamedTextColor.WHITE)
        }
    }

    private fun getDeathMessage(cause: EntityDamageEvent.DamageCause?, killer: Player?): Component {
        if (killer != null) {
            return when (cause) {
                EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.FIRE ->
                    Component.text(" was burnt whilst fighting ")
                        .append(killer.displayName())
                EntityDamageEvent.DamageCause.LAVA ->
                    Component.text(" was in lava whilst fighting ")
                        .append(killer.displayName())
                EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ->
                    Component.text(" blew up whilst fighting ")
                        .append(killer.displayName())
                EntityDamageEvent.DamageCause.VOID ->
                    Component.text(" was knocked into the void by ")
                        .append(killer.displayName())
                EntityDamageEvent.DamageCause.FALL ->
                    Component.text(" was hit of a cliff by ")
                        .append(killer.displayName())
                else ->
                    Component.text(" was killed by ")
                        .append(killer.displayName())
            }
        } else {
            return when (cause) {
                EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.FIRE ->
                    Component.text(" was burnt")
                EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ->
                    Component.text(" blew up")
                EntityDamageEvent.DamageCause.VOID ->
                    Component.text(" fell into the void")
                EntityDamageEvent.DamageCause.FALL ->
                    Component.text(" fell off a cliff")
                EntityDamageEvent.DamageCause.LAVA ->
                    Component.text(" fell into lava")
                else ->
                    Component.text(" died")
            }
        }
    }
}