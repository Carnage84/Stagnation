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
 * Represents the Spirit Wolf familiar interaction dialogue.
 */
@Initializable
class SpiritWolfDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritWolfDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_WOLF_6829, NPCs.SPIRIT_WOLF_6830)
    }
}

class SpiritWolfDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_WOLF_6829)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whurf? (What are you doing?)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Oh, just some... biped things. I'm sure it would bore you.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Bark Bark! (Danger!)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Where?!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Whiiiine... (False alarm...)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whuff whuff. Pantpant awff! (I smell something good! Hunting time!)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "We can go hunting in a moment. I just have to take care of something first.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pant pant whine? (When am I going to get to chase something?)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Oh I'm sure we'll fine something for you in a bit.").also { stage = END_DIALOGUE }
            }
        }


        if (inInventory(player!!, Items.BONES_2530)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Whuff-Whuff! Arf! (Throw the bone!I want to chase it!)").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "I can't just throw bones away-I need them to train my Prayer!").also { stage = END_DIALOGUE }
            }
        }
    }
}