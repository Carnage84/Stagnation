package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.findNPC
import core.api.sendChat
import core.api.sendNPCDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Haera dialogue plugin.
 */
@Initializable
class HaeraDialogue(player: Player? = null) : DialoguePlugin(player) {

    private val randomShouts = arrayOf(
        "That's the last straw!",
        "Do your chores!",
        "Useless... completely useless.",
        "Don't talk to me like that!",
        "You're no good!",
        "I'm warning you, Hegir!"
    )

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"What are you doing in our home?")
        stage = 0
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player("I just came to ask you a question.").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL,"Hegir, why did you let this human into our home?").also { stage++ }
            2 -> sendNPCDialogue(player, NPCs.HEGIR_2188, "I didn't let him in, he let himself in!",FacialExpression.OLD_NORMAL).also { stage++ }
            3 -> npc(FacialExpression.OLD_NORMAL,"Now what do you want, human?", "Speak up, speak up!").also { stage++ }
            4 -> player("I ehm, just wanted some information.").also { stage++ }
            5 -> npc(FacialExpression.OLD_NORMAL,"Well go ask someone else instead of barging", "into people's homes!").also { stage++ }
            6 -> sendNPCDialogue(player, NPCs.HEGIR_2188, "There's no need to be rude to the human, Haera dear.",FacialExpression.OLD_NORMAL).also { stage++ }
            7 -> npc(FacialExpression.OLD_NORMAL,"Now don't you dear me. I'll do whatever I please.").also { stage++ }
            8 ->player("I think this is my cue to leave...").also { stage++ }
            9 ->{
                end()
                sendChat(findNPC(NPCs.HAERA_2189)!!,randomShouts.random())
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HAERA_2189)
    }
}