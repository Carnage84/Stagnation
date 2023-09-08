package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.emote
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.emote.Emotes
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Spirit Cobra familiar interaction dialogue.
 */
@Initializable
class SpiritCobraDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritCobraDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_COBRA_6802, NPCs.SPIRIT_COBRA_6803)
    }
}

class SpiritCobraDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5, 6))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_COBRA_6802)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Do we have to do thissss right now?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yes, I'm afraid so.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"You are under my sssspell...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I will do as you ask...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Do we have to do thissss right now?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Not at all, I had just finished!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"You are feeling ssssleepy...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I am feeling sssso ssssleepy...").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"You will bring me lotssss of sssstuff!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What ssssort of sssstuff?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"What ssssort of sssstuff have you got?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "All kindsss of sssstuff.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Then just keep bringing sssstuff until I'm ssssatissssfied!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'm bored, do ssssomething to entertain me...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Errr, I'm not here to entertain you, you know.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You will do as I assssk...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Your will is my command...").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL,"I'm bored, do ssssomething to entertain me...").also { stage++ }
                2 -> emote(player!!, Emotes.DANCE).also {
                    playerl(FacialExpression.NEUTRAL, "I'll dance for you!")
                    end()
                    stage = END_DIALOGUE
                }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I am king of the world!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You know, I think there is a law against snakes being the king.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"My will is your command...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I am yours to command...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I am king of the world!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "All hail King Serpentor!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 6) {
            when (stage) {
                // With Ring of charos (a)
                0 -> npcl(FacialExpression.OLD_NORMAL,"You are under my power!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No, you are under my power!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"No, you are under my power!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, my power is greater!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Your power is the greater...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Your powers are no match for mine!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"You are convinced you have won this argument...").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "I won the argument...yay!").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL,"*Manic serpentine laughter*").also { stage = END_DIALOGUE }
            }
        }
    }
}