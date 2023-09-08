package content.quest.member.toweroflife.dialogue

import config.NPCs
import content.quest.member.toweroflife.TowerOfLifeListener
import core.api.getAttribute
import core.api.getQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Transmute dialogue plugin for Tower of Life quest.
 */
@Initializable
class TransmuteTOFDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, "Tower Of Life")) {
            in 0..2 -> when (stage) {
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Hi there. What is this place?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Ah, this be the Tower of Life! Beautiful, isn't it?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "I prefer ordinary houses.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "Pah, you have no taste.").also { stage = END_DIALOGUE }
            }

            3 -> if(getAttribute(player, TowerOfLifeListener.ENTER_TOL, 0) == 1) when(stage) {
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "I'm a builder!").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "I question why you are so pleased about this.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Well, I think it's a positive step in my career as a courageous adventurer.").also { stage = END_DIALOGUE }
            } else when (stage){
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "I must say, the inside of the tower is really something to behold.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Why, thanks. You'll find various pieces of machinery to fix in there. You can no doubt find the materials in there, too.").also { stage = END_DIALOGUE }
            }

            6 -> if(getAttribute(player, TowerOfLifeListener.QUEST_CUTSCENE, true)) when (stage) {
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "I can't believe what you guys have done.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Marvellous.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "No. Scary!").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "That too.").also { stage = END_DIALOGUE }
            }

            8 -> when(stage){
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Well, I've sat down and had a good chat with the Homunculus.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "And?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "You'll soon find out!").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "Huh?").also { stage = END_DIALOGUE }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TRANSMUTE_THE_ALCHEMIST_5585)
    }
}