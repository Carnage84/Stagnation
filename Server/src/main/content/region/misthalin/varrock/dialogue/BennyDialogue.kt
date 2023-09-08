package content.region.misthalin.varrock.dialogue

import config.Items
import config.NPCs
import core.api.addItem
import core.api.addItemOrDrop
import core.api.freeSlots
import core.api.inInventory
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Benny dialogue plugin.
 */
@Initializable
class BennyDialogue(player: Player? = null) : DialoguePlugin(player) {

     /*
        Info: Newspaper seller within Varrock.
        Location: 3220,3433
     */
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        options("Can I have a newspaper, please?", "How much does a paper cost?", "Varrock Herald? Never heard of it.", "Anything interesting in there?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> when (buttonId) {
                1 -> player("Can I have a newspaper, please?").also { stage = 10 }
                2 -> player("How much does a paper cost?").also { stage = 20 }
                3 -> player("Varrock Herald? Never heard of it.").also { stage = 30 }
                4 -> player("Anything interesting in there?").also { stage = 40 }
            }

            10 -> npc("Certainly, Guv. That'll be 50 gold pieces, please.").also { stage++ }
            11 -> options("Sure, here you go.", "Uh, no thanks, I've changed my mind").also { stage++ }
            12 -> when (buttonId) {
                1 -> player("Sure, here you go.").also { stage++ }
                2 -> player("No, thanks.").also { stage = 14 }
            }

            13 -> {
                if (!inInventory(player, Items.COINS_995, 50)) {
                    end()
                    player.packetDispatch.sendMessage("You need 50 gold coins to buy a newspaper.")
                } else if (freeSlots(player) == 0) {
                    end()
                    player.packetDispatch.sendMessage("You don't have enough inventory space.")
                } else {
                    addItemOrDrop(player, Items.NEWSPAPER_11169, 1)
                }
            }

            14 -> npc("Ok, suit yourself. Plenty more fish in the sea.").also { stage = END_DIALOGUE }
            20 -> npc("Just 50 gold pieces! An absolute bargain! Want one?").also { stage++ }
            21 -> options("Yes, please.", "No, thanks.").also { stage++ }
            22 -> when (buttonId) {
                1 -> player("Yes, please.").also { stage = 13 }
                2 -> player("No, thanks.").also { stage = 14 }
            }
            30 -> npc("For the illiterate amongst us, I shall elucidate. The", "Varrock Herald is a new newspaper. It is edited, printed", "and published by myself, Benny Gutenberg, and each", "edition promises to enthrall the reader with captivating ").also { stage++ }
            31 -> npc("material! Now, can I interest you in buying one for a mere", "50 gold?").also { stage = 21 }
            40 -> npc("Of course there is, mate. Packed full of thought provoking", "insights, contentious interviews and celebrity", "scandalmongering! An excellent read and all for just 50", "coins! Want one?").also { stage = 21 }
        }
        return false
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return BennyDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BENNY_5925)
    }

}