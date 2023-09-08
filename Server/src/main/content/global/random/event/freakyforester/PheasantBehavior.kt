package content.global.random.event.freakyforester

import config.Items
import config.NPCs
import core.api.getAttribute
import core.api.setAttribute
import core.game.node.entity.Entity
import core.game.node.entity.combat.BattleState
import core.game.node.entity.npc.NPC
import core.game.node.entity.npc.NPCBehavior
import core.game.node.item.Item

/**
 * Handles Pheasant NPC for Freaky forester random event.
 */
class PheasantBehavior : NPCBehavior(NPCs.PHEASANT_2459, NPCs.PHEASANT_2460, NPCs.PHEASANT_2461, NPCs.PHEASANT_2462) {
    override fun onDropTableRolled(self: NPC, killer: Entity, drops: ArrayList<Item>) {
        val assignedPheasant = getAttribute(killer, FreakUtils.freakTask, -1)
        if (assignedPheasant == -1) return
        drops.removeLast()
        if (assignedPheasant == self.id) {
            drops.add(Item (Items.RAW_PHEASANT_6178))
        } else {
            drops.add(Item (Items.RAW_PHEASANT_6179))
        }
        setAttribute(killer, FreakUtils.pheasantKilled, true)
    }

    override fun beforeDamageReceived(self: NPC, attacker: Entity, state: BattleState) {
        state.estimatedHit = 5
        state.secondaryHit = 0
    }

    override fun getXpMultiplier(self: NPC, attacker: Entity): Double {
        return 0.0
    }
}
