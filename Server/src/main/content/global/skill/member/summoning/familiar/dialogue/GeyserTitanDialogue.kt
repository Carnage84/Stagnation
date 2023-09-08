package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.amountInInventory
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
 * Represents the Geyser Titan familiar interaction dialogue.
 */
@Initializable
class GeyserTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, GeyserTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GEYSER_TITAN_7339, NPCs.GEYSER_TITAN_7340)
    }
}
class GeyserTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.GEYSER_TITAN_7339)
        if (randomConversation == 1 && amountInInventory(player!!, Items.SHARK_385) >= 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Over the course of their lifetime a shark may grow and use 20,000 teeth.").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Wow! That is a whole load of teeth. I wonder what the Tooth Fairy would give for those?").also { stage = END_DIALOGUE }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Did you know a snail can sleep up to three years?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "I wish I could do that. Ah...sleep.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2 && amountInInventory(player!!, Items.SHARK_385) >= 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Unlike most animals, both the shark's upper and lower jaws move when they bite.").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Really?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yup. Chomp chomp.").also { stage = END_DIALOGUE }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Did you know that in one feeding a mosquito can absorb one-and-a-half times its own body weight in blood?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Eugh.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3 && amountInInventory(player!!, Items.SHARK_385) >= 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Did you know that sharks have the most powerful jaws of any animal on the planet?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "No, I didn't.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Full of facts, me.").also { stage = END_DIALOGUE }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Did you know that RuneScape gets 100 tons heavier every day, due to dust falling from space?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "What a fascinating fact.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4 && amountInInventory(player!!, Items.SHARK_385) >= 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Did you know that sharks normally eat alone?").also { stage++ }

                1 -> playerl(FacialExpression.FRIENDLY, "I see.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Sometimes one feeding shark attracts others and they all try and get a piece of the prey.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "They take a bite at anything in their way and may even bite each other!").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Ouch!").also { stage = END_DIALOGUE }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey mate, how are you?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Not so bad.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Did you know that during the average human life-span the heart will beat approximately 2.5 billion times?").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "Wow, that is a lot of non-stop work!").also { stage = END_DIALOGUE }
            }
        }
    }
}