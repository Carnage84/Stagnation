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
 * Represents the Granite Lobster familiar interaction dialogue.
 */
@Initializable
class GraniteLobsterDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, GraniteLobsterDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GRANITE_LOBSTER_6849, NPCs.GRANITE_LOBSTER_6850)
    }
}

class GraniteLobsterDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.GRANITE_LOBSTER_6849)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"We shall heap the helmets of the fallen into a mountain!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "The outerlanders have insulted our heritage for the last time!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"The longhall will resound with our celebration!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"We march to war, Fremennik Player Name. Glory and plunder for all!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yes! We shall pile gold before the longhall of our tribesmen!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Fremennik Player Name, what is best in life?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Crush your enemies, see them driven before you, and hear the lamentation of their women!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I would have settled for raw sharks, but that's good too!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Ho, my Fremennik brother, shall we go raiding?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I suppose we could when I'm done with this.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Yes! To the looting and the plunder!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Clonkclonk clonk grind clonk. (Keep walking, outerlander. We have nothing to discuss.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Fair enough.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Clonkclonkclonk grind clonk grind? (It's nothing personal, you're just an outerlander, you know?)").also { stage = END_DIALOGUE }
            }
        }
    }
}