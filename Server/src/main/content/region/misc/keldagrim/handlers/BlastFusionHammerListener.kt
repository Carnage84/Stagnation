package content.region.misc.keldagrim.handlers

import config.Items
import config.NPCs
import content.region.misc.keldagrim.dialogue.BlastFusionHammerDialogue
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles exchange the Blast fusion hammer with Blast Furnace Foreman NPC.
 */
class BlastFusionHammerListener : InteractionListener {
    companion object{
        val FOREMAN = NPCs.BLAST_FURNACE_FOREMAN_2553
        val FUSION_HAMMER = Items.BLAST_FUSION_HAMMER_14478
    }
    override fun defineListeners() {
        onUseWith(IntType.NPC, FUSION_HAMMER, FOREMAN){ player, _, npc ->
            openDialogue(player, BlastFusionHammerDialogue(), npc)
            return@onUseWith true
        }
    }

}