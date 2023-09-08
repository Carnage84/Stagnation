package content.quest.member.treegnomevillage.dialogue

import config.Items
import config.NPCs
import content.quest.member.treegnomevillage.TreeGnomeVillage
import core.api.getQuestStage
import core.api.inInventory
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Ballista dialogue plugin used in Tree Gnome Village quest.
 */
@Initializable
class RemsaiTGVDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (inInventory(player!!, Items.ORBS_OF_PROTECTION_588) || player.questRepository.getStage(TreeGnomeVillage.questName) > 40) {
            playerl(FacialExpression.FRIENDLY,"I've returned.").also { stage++ }
        } else if (player.questRepository.getStage(TreeGnomeVillage.questName) == 40){
            playerl(FacialExpression.ASKING,"Are you ok?").also { stage++ }
        } else {
            playerl(FacialExpression.FRIENDLY,"Hello Remsai.").also { stage++ }
        }
        return true
    }

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questStage = getQuestStage(player!!, TreeGnomeVillage.questName)
        when {
            inInventory(player!!, Items.ORBS_OF_PROTECTION_588) -> {
                when(stage) {
                    1 -> npcl(FacialExpression.FRIENDLY,"You're back, well done brave adventurer. Now the orbs are safe we can perform the ritual for the spirit tree. We can live in peace once again.").also { stage = END_DIALOGUE }
                }
            }
            inInventory(player!!, Items.ORB_OF_PROTECTION_587) -> {
                when(stage) {
                    1 -> npcl(FacialExpression.FRIENDLY,"Hello, did you find the orb?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"I have it here.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"You're our saviour.").also { stage = END_DIALOGUE }
                }
            }
            questStage < 40 -> {
                when(stage) {
                    1 -> npcl(FacialExpression.FRIENDLY,"Hello, did you find the orb?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"No, I'm afraid not.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"Please, we must have the orb if we are to survive.").also { stage = END_DIALOGUE }
                }
            }
            questStage == 40 -> {
                when(stage) {
                    1 -> npcl(FacialExpression.FRIENDLY,"Khazard's men came. Without the orb we were defenceless. They killed many and then took our last hope, the other orbs. Now surely we're all doomed. Without them the spirit tree is useless.").also { stage = END_DIALOGUE }
                }
            }
            questStage > 40 -> {
                when(stage) {
                    1 -> npcl(FacialExpression.FRIENDLY,"You're back, well done brave adventurer. Now the orbs are safe we can perform the ritual for the spirit tree. We can live in peace once again.").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.REMSAI_472)
    }
}