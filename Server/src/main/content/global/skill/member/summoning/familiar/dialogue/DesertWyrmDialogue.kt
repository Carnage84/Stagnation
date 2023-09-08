package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.anyInEquipment
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
 * Represents the Desert Wyrm familiar interaction dialogue.
 */
@Initializable
class DesertWyrmDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, DesertWyrmDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DESERT_WYRM_6831, NPCs.DESERT_WYRM_6832)
    }
}

class DesertWyrmDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        val pickaxes = intArrayOf(Items.BRONZE_PICKAXE_1265, Items.IRON_PICKAXE_1267, Items.STEEL_PICKAXE_1269, Items.MITHRIL_PICKAXE_1273, Items.ADAMANT_PICKAXE_1271, Items.RUNE_PICKAXE_1275, Items.INFERNO_ADZE_13661)
        npc = NPC(NPCs.DESERT_WYRM_6831)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"This is so unsafe...I should have a hard hat for this work...").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I could get you a rune helm if you like - those are pretty hard.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Keep that up and you'll have the union on your back, ${player!!.username}.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"You can't touch me, I'm part of the union!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Is that some official no touching policy or something?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"You really don't get it, do you ${player!!.username}?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"You know, you might want to register with the union.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What are the benefits?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I stop bugging you to join the union.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Ask that again later; I'll have to consider that generous proposal.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Why are you ignoring that good ore seam, ${player!!.username}?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Which ore seam?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"There's a good ore seam right underneath us at this very moment.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Great! How long will it take for you to get to it?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Five years, give or take.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Five years!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"That's if we go opencast, mind. I could probably reach it in three if I just dug.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Right. I see. I think I'll skip it thanks.").also { stage = END_DIALOGUE }
            }
        }

        if(anyInEquipment(player!!, *pickaxes)){
            when (stage) {
            0 -> npcl(FacialExpression.OLD_NORMAL,"If you have that pick, why make me dig?").also { stage++ }
            1 -> playerl(FacialExpression.NEUTRAL, "Because it's a little quicker and easier on my arms.").also { stage++ }
            2 -> npcl(FacialExpression.OLD_NORMAL,"I should take industrial action over this...").also { stage++ }
            3 -> playerl(FacialExpression.NEUTRAL, "You mean you won't work for me any more?").also { stage++ }
            4 -> npcl(FacialExpression.OLD_NORMAL,"No. It means me and the lads feed you legs-first into some industrial machinery, maybe the Blast Furnace.").also { stage++ }
            5 -> playerl(FacialExpression.NEUTRAL, "I'll just be over here, digging.").also { stage++ }
            6 -> npcl(FacialExpression.OLD_NORMAL,"That's the spirit, lad!").also { stage = END_DIALOGUE }
            }
        }
    }
}