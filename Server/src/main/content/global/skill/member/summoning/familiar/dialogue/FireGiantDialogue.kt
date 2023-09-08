package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.inInventory
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
 * Represents the Fire Giant familiar interaction dialogue.
 */
@Initializable
class FireGiantDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, FireGiantDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FIRE_GIANT_7003, NPCs.FIRE_GIANT_7004)
    }
}

class FireGiantDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.FIRE_GIANT_7003)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pick flax.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Jump to it.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "If you want to get to Fletching level 99.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "That song...is terrible.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Sorry.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You're fanning my flame with your wind spells.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I'm singeing the curtains with my heat.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Oooh, very mellow.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'm burning up.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I want the world to know.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I got to let it show.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Catchy.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "It's raining flame!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Huzzah!").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "You have a...powerful voice.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "Thanks.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Let's go fireside.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I think I've roasted the sofa.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I think I've burnt down the hall.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Can't you sing quietly?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Sorry.").also { stage = END_DIALOGUE }
            }
        }

        if (inInventory(player!!, Items.TINDERBOX_590)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Relight my fire.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "A tinderbox is my only desire.").also { stage++ }
                2 -> playerl(FacialExpression.HALF_ASKING, "What are you singing?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "Just a song I heard a while ago.").also { stage++ }
                4 -> playerl(FacialExpression.HALF_ASKING, "It's not very good.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "You're just jealous of my singing voice.").also { stage++ }
                6 -> playerl(FacialExpression.HALF_ASKING, "Where did you hear this again?").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "Oh, you know, just with some other fire titans. Out for a night on the pyres.").also { stage++ }
                8 -> playerl(FacialExpression.NEUTRAL, "Hmm. Come on then. We have stuff to do.").also { stage = END_DIALOGUE }
            }
        }
    }
}