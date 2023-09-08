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
 * Represents the Bunyip familiar interaction dialogue.
 */
@Initializable
class BunyipDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, BunyipDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BUNYIP_6813, NPCs.BUNYIP_6814)
    }
}

class BunyipDialogueFile : DialogueFile() {

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
        npc = NPC(NPCs.BUNYIP_6813)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Where are we going and why is it not to the beach?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, we have a fair few places to go, but I suppose we could go to the beach if we get time.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Bonza! I'll get my board ready!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, even if we do go to the beach I don't know if we'll have time for that.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Awww, that's a drag...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey Bruce, can we go down to the beach t'day?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I have a lot of things to do today but maybe later.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Bonza!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pass me another bunch of shrimps, mate!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't know if I want any more water runes.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Righty, but I do know that I want some shrimps!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "A fair point.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Sigh...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What's the matter?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I'm dryin' out in this sun, mate.").also { stage++ }
                3 -> playerl(FacialExpression.ASKING, "Well, what can I do to help?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Well, fish oil is bonza for the skin, ya know.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Oh, right, I think I see where this is going.").also { stage = END_DIALOGUE }
            }
        }

        if (anyInInventory(player!!, *fishes)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I see you've got some fish there, mate.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yeah, but I might cook them up before I give them to you!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Humans...always ruining good fishes.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "You know, some people prefer them cooked.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Yeah. We call 'em freaks.").also { stage = END_DIALOGUE }
            }
        }
    }
}