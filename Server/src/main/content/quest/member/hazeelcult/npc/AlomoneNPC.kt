package content.quest.member.hazeelcult.npc

import config.Items
import config.NPCs
import content.quest.member.hazeelcult.HazeelCult
import content.quest.member.hazeelcult.HazeelCultListeners.Companion.ALOMONE
import core.api.isQuestInProgress
import core.api.produceGroundItem
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Alomone NPC.
 */
@Initializable
class AlomoneNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return AlomoneNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ALOMONE_891)
    }

    override fun handleTickActions() {
        super.handleTickActions()
        ALOMONE.respawnTick = 60
    }

    override fun finalizeDeath(killer: Entity?) {
        if (killer is Player) {
            if (isQuestInProgress(killer as Player, HazeelCult.questName, 3, 4)) {
                produceGroundItem(killer, Items.CARNILLEAN_ARMOUR_2405, 1, this.location)
            }
            super.finalizeDeath(killer)
        }
    }
}