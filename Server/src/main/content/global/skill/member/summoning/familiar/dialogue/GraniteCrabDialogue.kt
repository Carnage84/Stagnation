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
 * Represents the Granite Crab familiar interaction dialogue.
 */
@Initializable
class GraniteCrabDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, GraniteCrabDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GRANITE_CRAB_6796, NPCs.GRANITE_CRAB_6797)
    }
}

class GraniteCrabDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))
    private val fishes = intArrayOf(
        Items.SHRIMPS_315,
        Items.TUNA_361,
        Items.SALMON_329,
        Items.PIKE_351,
        Items.BASS_365,
        Items.CRAYFISH_13433
    )
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.GRANITE_CRAB_6796)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Can I have some fish?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "No, I have to cook these for later.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Free fish, please?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No...I already told you you can't.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Can it be fish time soon?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Great...I get stuck with the only granite crab in existence that can't take no for an answer...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Rock fish now, please?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Not right now. I don't have any rock fish.").also { stage++ }
           }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"When can we go fishing? I want rock fish.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "When I need some fish. It's not that hard to work out, right?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I'm stealthy!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Errr... of course you are.").also { stage = END_DIALOGUE }
            }
        }

        if (anyInInventory(player!!, *fishes)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "That is not a rock fish...").also { stage = END_DIALOGUE }
            }
        }
    }
}