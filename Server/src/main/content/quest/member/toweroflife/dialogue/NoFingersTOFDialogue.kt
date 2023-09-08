package content.quest.member.toweroflife.dialogue

import config.NPCs
import content.quest.member.toweroflife.TowerOfLifeListener.Companion.ENTER_TOL
import core.api.getAttribute
import core.api.getQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the No Fingers dialogue plugin for Tower of Life quest.
 */
@Initializable
class NoFingersTOFDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, "Tower Of Life")) {
            in 0..1 -> when (stage) {
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "You seem to like playing with danger, there.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "They don't call me 'No-fingers' for nothing!").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Well, correct me if I'm wrong, but you seem to have all your fingers.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "It may seem that way mate, but I think you'll find I don't have any.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "What?").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "These not be fingers. These 'ere be wooden fingers.").also { stage++ }
                6 -> playerl(FacialExpression.FRIENDLY, "Wooden fingers?").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "Yep.").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "That's not possible, how could you move them?").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "Magic wood.").also { stage++ }
                10 -> playerl(FacialExpression.FRIENDLY, "Yes, well I think I'd better be going now.").also { stage = END_DIALOGUE }
            }
            2 -> when(stage){
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Hello. I was just wondering... I don't suppose you have any spare clothing you could lend me?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "I do have many boots.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Ah, thanks.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "But there's no way I'm giving any to you.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Oh, but why? I could pay you!").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Nope. Only real builders can wear builders' boots, and you're not even close.").also { stage = END_DIALOGUE }
            }

            3 -> if(getAttribute(player, ENTER_TOL, 0) == 1) when(stage){
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Hi.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Don't suppose you've seen any boots around here? Size nine, steel toed, brown leather...").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Nope, not at all. I'd best be going. Have a nice day. Bye!").also { stage = END_DIALOGUE }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.NO_FINGERS_5590)
    }
}