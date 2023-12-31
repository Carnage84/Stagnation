package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Myndill dialogue plugin.
 */
@Initializable
class MyndillDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"Hello there, human.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> {
                options("Do you have any news?", "Do you have any quests?", "See you later!")
                stage++
            }

            1 -> when (buttonId) {
                1 -> {
                    player(FacialExpression.HALF_ASKING,"Do you have any news?")
                    stage = 4
                }

                2 -> {
                    player(FacialExpression.HALF_ASKING,"Do you have any quests?")
                    stage = 10
                }

                3 -> {
                    player(FacialExpression.FRIENDLY,"See you later!")
                    stage = 12
                }
            }

            4 -> {
                npc(FacialExpression.OLD_NORMAL,"The Red Axe has departed the city recently.","Ill omens.")
                stage++
            }

            5 -> {
                player(FacialExpression.HALF_ASKING,"How is that? Do you know anything","about this?")
                stage++
            }

            6 -> {
                npc(FacialExpression.OLD_NORMAL,"No, it's just that no company has ever willingly","left the Consortium before.")
                stage++
            }

            7 -> {
                player(FacialExpression.HALF_ASKING,"Companies have been forced out of the","Consortium then?")
                stage++
            }

            8 -> {
                npc(FacialExpression.OLD_NORMAL,"Oh yes. It hasn't happened for a long while now,","but sometimes a company goes bankrupt. Or the value"," of the company becomes so low that it's relegated","to being a minor company.")
                stage++
            }

            9 -> {
                player(FacialExpression.FRIENDLY,"I see, thank you.")
                stage = 100
            }

            10 -> {
                npc(FacialExpression.OLD_NORMAL,"Let me think for a moment...")
                stage++
            }

            11 -> {
                npc(FacialExpression.OLD_NORMAL,"No, I have nothing that I could possible"," want doing. Try the shops or the market instead.")
                stage = 0
            }

            12 -> {
                npc(FacialExpression.OLD_NORMAL," Perhaps!")
                stage = 100
            }

            100 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MYNDILL_2197)
    }
}