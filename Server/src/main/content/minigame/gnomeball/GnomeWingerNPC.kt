package content.minigame.gnomeball

import config.Animations
import config.Items
import config.NPCs
import core.api.animate
import core.api.getAttribute
import core.api.setAttribute
import core.api.spawnProjectile
import core.game.global.action.EquipHandler
import core.game.node.entity.npc.NPC
import core.game.node.entity.npc.NPCBehavior
import core.game.node.entity.player.Player
import core.game.node.item.Item

private class GnomeWingerNPC : NPCBehavior(NPCs.GNOME_WINGER_633) {
    lateinit var player : Player
    override fun tick(self: NPC): Boolean {
        player = getAttribute(self, "gnomeball:pass-player", null) ?: return false

        val pass = getAttribute(self, "gnomeball:passing", false)
        var delay = getAttribute(self, "gnomeball:pass-timer", 10)

        if(pass && delay == 0) {
            pass(player, self)
            setAttribute(self, "gnomeball:pass-timer", 10)
            setAttribute(self, "gnomeball:in-pass", false)
        }

        if(pass) setAttribute(self, "gnomeball:pass-timer", (delay - 1))
        return true
    }

    fun pass(player: Player, self: NPC) {
        val projectile = PassGnomeBallHandler.BallProjectile
        self.face(player)
        animate(self, Animations.BALLER_THROW_201)
        spawnProjectile(
            self.location,
            player.location,
            projectile.id,
            projectile.endHeight,
            projectile.startHeight,
            0,
            projectile.speed,
            10
        )
        EquipHandler.handleEquip(player, Item(Items.GNOMEBALL_751))
    }
}
