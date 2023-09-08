package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the dialogue plugin used for the Randivor.
 * Keldagrim's Best Bread.
 */
@Initializable
class RandivorDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"Welcome to my stall! Come and buy some","tasty bread, freshly baked just 2 months ago!")
        stage = 1
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> options("Sounds delicious!", "I think I'll pass.").also { stage++ }
            2 -> when (buttonId) {
                1 -> player(FacialExpression.HAPPY, "Sounds delicious!").also { stage++ }
                2 -> player(FacialExpression.NEUTRAL, "I think I'll pass.").also { stage = 4 }
            }
            3 -> {
                end()
                openNpcShop(player, NPCs.RANDIVOR_2156)
            }

            4 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.RANDIVOR_2156)
    }
}