package content.region.misthalin.dorgeshuun.handler

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.tools.RandomFunction

/**
 * Handles brooch interaction with Mistag.
 */
class UseBroochOnMistagListener : InteractionListener {

    private val BROOCH = Items.BROOCH_5008
    override fun defineListeners() {
        onUseWith(IntType.NPC, BROOCH, NPCs.MISTAG_2084) { player, used, _ ->
            if (getQuestStage(player, "Lost Tribe") == 100 && removeItem(player, used.asItem(), Container.INVENTORY)) {
                openDialogue(player, UseBroochOnMistagDialogue())
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }
    }

}

class UseBroochOnMistagDialogue : DialogueFile() {

    private val randomHelm: Int = RandomFunction.getRandomElement(
        arrayOf(Items.MINING_HELMET_5013, Items.MINING_HELMET_5015)
    )
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.MISTAG_2084)
        when (stage) {
            0 -> playerl(FacialExpression.HALF_ASKING, "Is this your brooch?").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL, "Yes! I thought I'd lost it. Thank you.", "Have one of these helmets. It will be useful if you want to", "work in the mine.").also { stage++ }
            2 -> {
                end()
                addItemOrDrop(player!!, randomHelm, 1)
            }
        }
    }

}