package content.global.handlers.item.withnpc

import config.Items
import config.NPCs
import content.region.island.apeatoll.dialogue.dungeon.ZooknockDialogueFile
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listener for Zooknock.
 */
open class ZooknockListener() : InteractionListener {
    val goldBar = Items.GOLD_BAR_2357
    val monkeyAmuletMould = Items.MAMULET_MOULD_4020
    val monkeyDentures = Items.MONKEY_DENTURES_4006


    val items = intArrayOf(goldBar, monkeyDentures, monkeyAmuletMould)

    val zooknock = NPCs.ZOOKNOCK_1425

    val lol = arrayOf(Items)

    override fun defineListeners() {
        onUseWith(IntType.NPC, items, zooknock) { player, used, with ->
            openDialogue(player, ZooknockDialogueFile(used.id))
            return@onUseWith false
        }
    }
}