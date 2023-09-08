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
 * Represents the Fruit Bat familiar interaction dialogue.
 */
@Initializable
class FruitBatDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, FruitBatDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FRUIT_BAT_6817)
    }
}

class FruitBatDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.FRUIT_BAT_6817)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Squeekasqueek squeek? (How much longer do you want me for?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't really know at the moment, it all depends what I want to do today.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Squeakdqueesqueak. (This place is fun!)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Glad you think so!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Squeeksqueekasqueek? (Where are we going?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Oh, we're likely to go to a lot of places today.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Squeeksqueekasqueek squee? (Can you smell lemons?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No, why do you ask?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Squeaksqueak squeaksqueesqueak. (Must just be thinking about them.)").also { stage = END_DIALOGUE }
            }
        }

        if (amountInInventory(player!!, Items.PAPAYA_FRUIT_5972) >= 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Squeeksqueekasqueeksquee? (Can I have a papaya?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No, I have a very specific plan for them.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Squeek? (What?)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I was just going to grate it over some other vegetables and eat it. Yum.").also { stage = END_DIALOGUE }
            }
        }
    }
}