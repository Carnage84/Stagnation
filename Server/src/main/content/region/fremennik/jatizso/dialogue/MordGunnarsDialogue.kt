package content.region.fremennik.jatizso.dialogue

import config.NPCs
import content.region.fremennik.rellekka.handlers.RellekkaUtils
import content.region.fremennik.rellekka.handlers.TravelDestination
import core.api.requireQuest
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Mord Gunnars dialogue plugin.
 */
@Initializable
class MordGunnarsDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return MordGunnarsDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if(npc.id == NPCs.MORD_GUNNARS_5481){
            npcl(FacialExpression.FRIENDLY, "Would you like to sail to Jatizso?")
        } else {
            npcl(FacialExpression.FRIENDLY, "Would you like to sail back to Rellekka?")
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> options("Yes, please.", "No, thanks.").also { stage++ }
            1 -> when(buttonId){
                1 -> playerl(FacialExpression.FRIENDLY, "Yes, please!").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "No, thank you.").also { stage = END_DIALOGUE }
            }

            2 -> {
                end()
                if (!requireQuest(player, "Fremennik Trials", "")) {
                    return true
                } else {
                    RellekkaUtils.sail(
                        player,
                        if (npc.id == NPCs.MORD_GUNNARS_5481) TravelDestination.RELLEKKA_TO_JATIZSO else TravelDestination.JATIZSO_TO_RELLEKKA
                    )
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MORD_GUNNARS_5482, NPCs.MORD_GUNNARS_5481)
    }

}
