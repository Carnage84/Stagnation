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
 * Represents the Albino Rat familiar interaction dialogue.
 */
@Initializable
class AlbinoRatDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, AlbinoRatDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ALBINO_RAT_6847, NPCs.ALBINO_RAT_6848)
    }
}

class AlbinoRatDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.ALBINO_RAT_6847)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey boss, we going to do anything wicked today?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I don't know why we would: I tend not to go around being wicked.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Not even a little?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well there was that one time... I'm sorry, no wickedness today.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Awwwwww...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey boss, can we go and loot something now?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, what did you have in mind?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I dunno - where are we headed?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I hadn't decided yet.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "When we get there, let's loot something nearby!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Sounds like a plan, certainly.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "So what we up to today, boss?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Oh I'm sure we'll find something to occupy our time.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Let's go robbin' graves again!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What do you mean 'again'?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Nuffin'...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You know, boss, I don't think you're totally into this whole 'evil' thing.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I wonder what gave you that impression?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well, I worked with a lot of evil people; some of the best.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Such as?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I'm not telling! I've got my principles to uphold.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "There is honour amongst thieves, it would seem.").also { stage = END_DIALOGUE }
            }
        }
    }
}