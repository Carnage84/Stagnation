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
 * Represents the Smoke Devil familiar interaction dialogue.
 */
@Initializable
class SmokeDevilDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SmokeDevilDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SMOKE_DEVIL_6865, NPCs.SMOKE_DEVIL_6866)
    }
}

class SmokeDevilDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SMOKE_DEVIL_6865)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"When are you going to be done with that?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Soon, I hope.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Good, because this place is too breezy.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What do you mean?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I mean, it's tricky to keep hovering in this draft.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Ok, we'll move around a little if you like.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Yes please!").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when (stage) {
                0  -> npcl(FacialExpression.OLD_NORMAL,"Hey!").also { stage++ }
                1  -> playerl(FacialExpression.NEUTRAL, "Yes?").also { stage++ }
                2  -> npcl(FacialExpression.OLD_NORMAL,"Where are we going again?").also { stage++ }
                3  -> playerl(FacialExpression.NEUTRAL, "Well, I have a lot of things to do today, so we might go a lot of places.").also { stage++ }
                4  -> npcl(FacialExpression.OLD_NORMAL,"Are we there yet?").also { stage++ }
                5  -> playerl(FacialExpression.NEUTRAL, "No, not yet.").also { stage++ }
                6  -> npcl(FacialExpression.OLD_NORMAL,"How about now?").also { stage++ }
                7  -> playerl(FacialExpression.NEUTRAL, "No.").also { stage++ }
                8  -> npcl(FacialExpression.OLD_NORMAL,"Are we still not there?").also { stage++ }
                9  -> playerl(FacialExpression.NEUTRAL, "NO!").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL,"Okay, just checking.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Ah, this is the life!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Having a good time up there?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Yeah! It's great to feel the wind in your tentacles.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Sadly, I don't know what that feels like.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Why not?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "No tentacles for a start.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Well, nobody's perfect.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Why is it always so cold here?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't think it's that cold.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"It is compared to back home.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "How hot is it where you are from?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I can never remember. What is the vaporisation point of steel again?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Pretty high.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "No wonder you feel cold here...").also { stage = END_DIALOGUE }
            }
        }
    }
}