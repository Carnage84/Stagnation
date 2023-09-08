package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Spirit Scorpion familiar interaction dialogue.
 */
@Initializable
class SpiritScorpionDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritScorpionDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_SCORPION_6837, NPCs.SPIRIT_SCORPION_6838)
    }
}

class SpiritScorpionDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_SCORPION_6837)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey, boss, how about we go to the bank?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "And do what?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well, we could open by shouting, 'Stand and deliver!'").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "Why does everything with you end with something getting held up?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "That isn't true! Give me one example.").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "How about the post office?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "How about another?").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "Those junior White Knights? The ones selling the gnome crunchies?").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL, "That was self defence.").also { stage++ }
                9 -> playerl(FacialExpression.FRIENDLY, "No! No more hold-ups, stick-ups, thefts, or heists, you got that?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Say hello to my little friend!").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "What?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "My little friend: you ignored him last time you met him.").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "So, who is your friend?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "If I tell you, what is the point?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey, boss, I've been thinking.").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "That's never a good sign.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "See, I heard about this railway...").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "We are not robbing it!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I might not have wanted to suggest that, boss...").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Then what were you going to suggest?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "That isn't important right now.").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "I thought as much.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Why do we never go to crossroads and rob travelers?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "There are already highwaymen at the good spots.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Maybe we need to think bigger.").also { stage = END_DIALOGUE }
            }
        }
    }
}