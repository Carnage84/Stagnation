package content.quest.member.biohazard.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.tools.END_DIALOGUE

/**
 * Represents the Civilian Elena dialogue file for Biohazard quest.
 */
class CivilianElenaDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        playerl(FacialExpression.FRIENDLY, "Hello Elena.").also { stage++ }
        return true
    }
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questStage = getQuestStage(player, "Biohazard")
        when (stage) {
            0 -> if (questStage != 100) {
                openDialogue(player, ElenaBiohazardDialogue(), npc)
            } else {
                npcl(FacialExpression.FRIENDLY, "Hey, how are you?").also { stage = 1 }
            }
            1 -> playerl(FacialExpression.FRIENDLY, "Good thanks, yourself?").also { stage++ }
            2 -> npcl(FacialExpression.FRIENDLY, "Not bad, let me know when you hear from King Lathas again.").also { stage++ }
            3 -> playerl(FacialExpression.FRIENDLY, "Will do!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ELENA_3209)
    }
}