package content.region.kandarin.ardougne.dialogue

import config.NPCs
import core.api.getAttribute
import core.api.openNpcShop
import core.api.setAttribute
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Monkey Minder dialogue plugin.
 */
@Initializable
class MonkeyMinderDialogue(player: Player? = null) : DialoguePlugin(player){

    private val talkbefore = "/save:monkeyminder:talk"

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if(getAttribute(player, talkbefore, false)){
            options("Why have the monkeys been multiplying?", "What are you going to do about the monkeys?").also { stage = 6 }
        } else {
            playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage = 0 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npcl(FacialExpression.NEUTRAL, "Hello, sir.").also { stage++ }
            1 -> player(FacialExpression.FRIENDLY, "I haven't seen Monkey Minders here before...").also { stage++ }
            2 -> npc(FacialExpression.FRIENDLY, "Yes, you wouldn't have. We have newly been posted here.").also { stage++ }
            3 -> player(FacialExpression.FRIENDLY, "But why do we need Monkey Minders?").also { stage++ }
            4 -> npcl(FacialExpression.FRIENDLY,"It's entirely necessary: just take a look at the monkeys. They've been multiplying out of control!").also { stage++ }
            5 -> npcl(FacialExpression.FRIENDLY,"Every time we turn our backs another one of them tries to break free. It's almost as if they're plotting to escape!").also {
                setAttribute(player, talkbefore, true)
                stage = END_DIALOGUE
            }
            6 -> when(buttonId){
                1 -> playerl(FacialExpression.FRIENDLY, "Why have the monkeys been multiplying?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY,"What are you going to do about the monkeys?").also { stage = 9 }
            }
            7 -> npcl(FacialExpression.FRIENDLY,"We just don't know. Perhaps it's the weather. Perhaps it's something in the food. Perhaps it's just that time of year.").also { stage++ }
            8 -> npcl(FacialExpression.FRIENDLY, "Your guess is as good as ours.").also { stage = END_DIALOGUE }
            9 -> npcl(FacialExpression.FRIENDLY, "We're going to post enough men here to be able to contain the monkeys in case they manage to escape.").also { stage++ }
            10 -> playerl(FacialExpression.FRIENDLY, "Is that very likely?").also { stage++ }
            11 -> npcl(FacialExpression.FRIENDLY, "Stranger things have happened in Ardougne.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return MonkeyMinderDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MONKEY_MINDER_1469)
    }
}
