package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.anyInInventory
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
 * Represents the Void Spinner familiar interaction dialogue.
 */
@Initializable
class VoidSpinnerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, VoidSpinnerDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.VOID_SPINNER_7333, NPCs.VOID_SPINNER_7334)
    }
}

class VoidSpinnerDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VOID_SPINNER_7333)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Let's go play hide an' seek!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Okay, you hide and I'll come find you.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You'll never find me!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What a disaster that would be...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "My mummy told me I was clever.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Aren't you meant to be the essence of a spinner? How do you have a mother?").also { stage++ }

                2 -> npcl(FacialExpression.OLD_NORMAL, "What you mean, 'essence'?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Never mind, I don't think it matters.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "My logimical powers has proved me smarterer than you!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'm coming to tickle you!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No! You've got so many tentacles!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I'm coming to tickle you!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Aieee!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Where's the sweeties?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "They are wherever good spinners go.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yay for me!").also { stage = END_DIALOGUE }
            }
        }
        
        if (anyInInventory(player!!, Items.PURPLE_SWEETS_4561, Items.PURPLE_SWEETS_10476)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You have sweeties for spinner?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sweeties? No sweeties here.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You do! You do! Gimmie sweeties!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I don't have any sweeties!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "What you hiding in your backpack, then?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "That? Oh, that's...erm...worms! Yes, worms. Purple worms.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Yucky!").also { stage = END_DIALOGUE }
            }
        }
    }
}