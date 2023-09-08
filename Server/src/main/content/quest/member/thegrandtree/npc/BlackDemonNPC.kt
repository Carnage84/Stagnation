package content.quest.member.thegrandtree.npc

import config.NPCs
import content.quest.member.thegrandtree.TheGrandTree
import core.api.setQuestStage
import core.game.interaction.InteractionListener
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Handles Black Demon fight in the grand tree quest.
 */
@Initializable
class BlackDemonNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id,location), InteractionListener {

    override fun construct(id: Int, location: Location?, vararg objects: Any?): AbstractNPC {
        return BlackDemonNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BLACK_DEMON_677)
    }

    override fun defineListeners() {
    }

    override fun finalizeDeath(killer: Entity?) {
        // In the event that this npcID is used somewhere else...
        if(killer!!.asPlayer().location.regionId == 9882) {
            setQuestStage(killer!!.asPlayer(), TheGrandTree.questName, 98)
            this.isRespawn = false
        }
        super.finalizeDeath(killer)
    }
}
