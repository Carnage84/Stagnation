package content.quest.member.trollstronghold.npc

import config.Items
import config.NPCs
import core.api.isQuestInProgress
import core.api.produceGroundItem
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Twig NPC used in Troll stronghold quest.
 */
@Initializable
class TwigNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return TwigNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TWIG_1126, NPCs.TWIG_1128)
    }

    override fun finalizeDeath(killer: Entity?) {
        if (isQuestInProgress(killer as Player, "Troll Stronghold", 8, 10)) {
            produceGroundItem(killer, Items.CELL_KEY_1_3136, 1, this.location)
        }
        super.finalizeDeath(killer)
    }
}