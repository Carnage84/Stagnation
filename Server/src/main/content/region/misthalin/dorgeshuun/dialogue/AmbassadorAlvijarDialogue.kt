package content.region.misthalin.dorgeshuun.dialogue

import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction
import core.tools.START_DIALOGUE

/**
 * Represents the Ambassador Alvijar dialogue plugin.
 */
@Initializable
class AmbassadorAlvijarDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        var randomConversation = RandomFunction.random(1,6)
        when(stage) {
            START_DIALOGUE -> when (randomConversation){
                1 -> npcl(FacialExpression.OLD_NORMAL, "I can't stand these goblins sometimes! I come up with suggestions to make their city more efficient, and they just say it would spoil their precious way of life!").also { stage = END_DIALOGUE }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I miss Keldagrim. This city is all very well, but no one makes an underground city like the dwarves!").also { stage = END_DIALOGUE }
                3 -> npcl(FacialExpression.OLD_NORMAL, "It's funny... whenever we dwarves have ventured onto the surface, we've found ourselves at war with the goblins there. But now we meet these goblins underground, and they're no threat at all.").also { stage = 10 }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Trade with the goblins is going well at the moment. It's a pity they don't seem as interested in our beer as in your food, though!").also { stage = END_DIALOGUE }
                5 -> npcl(FacialExpression.OLD_NORMAL, "Thank you again for helping us complete the train link. Further delays would have cost the Consortium a lot of money.").also { stage = END_DIALOGUE }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Oh, and I hear you saved someone's life as well. Well done on that count too.").also { stage = END_DIALOGUE }
            }
            10 -> npcl(FacialExpression.OLD_NORMAL, "Maybe this will herald a change in our relations with the surface goblins as well.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(5887)
    }

}