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
 * Represents the Spirit Mosquito familiar interaction dialogue.
 */
@Initializable
class SpiritMosquitoDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritMosquitoDialogueFile())
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_MOSQUITO_7331, NPCs.SPIRIT_MOSQUITO_7332)
    }
}

class SpiritMosquitoDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_MOSQUITO_7331)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You have lovely ankles.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Am I meant to be pleased by that?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Thin skin. Your delicious blood is easier to get too.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I knew I couldn't trust you.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Oh come on, you won't feel a thing...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "How about that local sports team?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Which one? The gnomeball team?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I must confess: I have no idea.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Why did you ask, then?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I was just trying to be friendly.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Just trying to get to my veins, more like!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Have you ever tasted pirate blood?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Why would I drink pirate blood?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "How about dwarf blood?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I don't think you quite understand...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Gnome blood, then?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'm soooo hungry!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What would you like to eat?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well, if you're not too attached to your elbow...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "You can't eat my elbow! You don't have teeth!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Tell me about it. Cousin Nigel always makes fun of me. Calls me 'No-teeth'.").also { stage = END_DIALOGUE }
            }
        }
    }
}