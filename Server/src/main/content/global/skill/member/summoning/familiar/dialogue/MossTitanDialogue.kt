package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.api.sendDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Moss Titan familiar interaction dialogue.
 */
@Initializable
class MossTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, MossTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MOSS_TITAN_7357, NPCs.MOSS_TITAN_7358)
    }
}

class MossTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.MOSS_TITAN_7357)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Oh, look! A bug!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "It's quite a large bug.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"He's so cute! I wanna keep him.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, be careful.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I'm gonna call him Buggie and I'm gonna keep him in a box.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Don't get overexcited.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"I'm gonna feed him and we're gonna be so happy together!").also { stage++ }
                7 -> sendDialogue(player!!, "The Moss titan begins to bounce up and down.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL,"Aww...Buggie went squish.").also { stage++ }
                9 -> playerl(FacialExpression.NEUTRAL, "Sigh.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when (stage) {
                0  -> npcl(FacialExpression.OLD_NORMAL,"When you stamp on 'em, humies go squish.").also { stage++ }
                1  -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                2  -> npcl(FacialExpression.OLD_NORMAL,"When you punch 'em, humies go squish.").also { stage++ }
                3  -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                4  -> npcl(FacialExpression.OLD_NORMAL,"When you push 'em, humies go squish.").also { stage++ }
                5  -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                6  -> npcl(FacialExpression.OLD_NORMAL,"Squish squish squish.").also { stage++ }
                7  -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                8  -> npcl(FacialExpression.OLD_NORMAL,"When you touch 'em, humies go squish.").also { stage++ }
                9  -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL,"When you talk to 'em, humies go squish.").also { stage++ }
                11 -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                12 -> npcl(FacialExpression.OLD_NORMAL,"When you poke 'em, humies go squish.").also { stage++ }
                13 -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                14 -> npcl(FacialExpression.OLD_NORMAL,"Squish squish squish.").also { stage++ }
                15 -> playerl(FacialExpression.NEUTRAL, "You have problems, you know that. Come on, we have got stuff to do.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Stampy stampy stampy stampy stampy stampy, I've got big feet.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Are you quite finished?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Stampy stampy stampy stampy stampy stampy, I've got big hands.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Done yet?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Stampy stampy stampy stampy stampy stampy, I've got big chest.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Done yet?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Stampy stampy stampy stampy stampy stampy, I've got big hair.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Oh, be quiet and come on.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL,"...").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"What are we doing today?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Let's just wait and see.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I want to do some squishing of tiny things!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Preferably not me.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Even if only a little bit, like your foot or something?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Um, no. I really don't fancy being squished today, thanks.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Awww...").also { stage = END_DIALOGUE }
            }
        }
    }
}