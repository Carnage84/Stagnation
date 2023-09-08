package content.region.misc.keldagrim.dialogue

import config.Items
import config.NPCs
import core.api.addItemOrDrop
import core.api.removeItem
import core.api.sendDialogue
import core.api.sendItemDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Handles dialogue for selling the Blast fusion hammer to Blast Furnace Foreman.
 */
@Initializable
class BlastFusionHammerDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.BLAST_FURNACE_FOREMAN_2553)
        when (stage) {
            0 -> npcl(FacialExpression.OLD_DEFAULT,"For that hammer I can offer you 1,000,000 coins for the trade-in. Do you accept?").also { stage++ }
            1 -> options("Yes, I'll take your offer.", "Sorry, I'll keep hold of the hammer for now.").also { stage++ }
            2 -> when(buttonID){
                1 -> playerl(FacialExpression.FRIENDLY, "Yes, I'll take your offer.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY,"Sorry, I'll keep hold of the hammer for now.").also { stage = END_DIALOGUE }
            }
            3 -> if(!removeItem(player!!, Items.BLAST_FUSION_HAMMER_14478)){
                sendDialogue(player!!, "You don't have required item in your inventory.")
                end()
                stage = END_DIALOGUE
            } else {
                sendItemDialogue(player!!, Items.BLAST_FUSION_HAMMER_14478, "You hand over the hammer: Blast fusion hammer. and get 1,000,000 coins.")
                addItemOrDrop(player!!, Items.COINS_995, 1000000)
                end()
            }
        }
    }

}