package content.region.island.mosleharmless.dialogue

import config.Items
import config.NPCs
import core.api.inInventory
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Mike pirate dialogue plugin.
 */
@Initializable
class MikeDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player("Hello!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> if (!inInventory(player, Items.BOOK_O_PIRACY_7144)) {
                npcl(FacialExpression.FRIENDLY, "Arr? Be ye wantin' te go on account with our gang o' fillibusters?").also { stage = 1 }
            } else {
                npcl(FacialExpression.HALF_ASKING, "Wanna buy some clothes? Good quality, none of yer rags.").also { stage = 4 }
            }
            1 -> npcl(FacialExpression.FRIENDLY, "The powder monkey be takin' a caulk after gettin' rowdy on bumboo, so there be plenty of room for ye.").also { stage++ }
            2 -> player(FacialExpression.STRUGGLE, "Riiiiight...").also { stage++ }
            3 -> playerl(FacialExpression.STRUGGLE, "I'll just be over here if you need me.").also { stage = END_DIALOGUE }
            4 -> options("I'll take a look.", "No thanks.").also { stage++ }
            5 -> {
                when (buttonId) {
                    1 -> player("I'll take a look.").also { stage = 7 }
                    2 -> player("No thanks.").also { stage++ }
                }
            }
            6 -> npcl(FacialExpression.ANNOYED, "Then what are you doin' in my shop? Move along.").also { stage = END_DIALOGUE }
            7 -> openNpcShop(player, NPCs.MIKE_3166).also { end(); stage = END_DIALOGUE }
        }
        return true
    }
    override fun newInstance(player: Player?): DialoguePlugin {
        return MikeDialogue(player)
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MIKE_3166)
    }
}