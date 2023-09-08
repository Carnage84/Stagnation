package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Honey Badger familiar interaction dialogue.
 */
@Initializable
class HoneyBadgerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val randomTopic = RandomFunction.random(1,5)
        when(randomTopic) {
            1 -> npcl(FacialExpression.OLD_NORMAL, "An outpouring of sanity-straining abuse*").also { stage = 6 }
            2 -> npcl(FacialExpression.OLD_NORMAL, "An outpouring of spittal-flecked insults.*").also { stage = 6 }
            3 -> npcl(FacialExpression.OLD_NORMAL, "A lambasting of visibly illustrated obscenities.*").also { stage = 6 }
            4 -> npcl(FacialExpression.OLD_NORMAL, "A tirade of biologically questionable threats*").also { stage = 6 }
            5 -> npcl(FacialExpression.OLD_NORMAL, "A stream of eye-watering crudities*").also { stage = 6 }
            6 -> playerl(FacialExpression.NEUTRAL, "Why do I talk to you again?").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HONEY_BADGER_6845, NPCs.HONEY_BADGER_6846)
    }

}