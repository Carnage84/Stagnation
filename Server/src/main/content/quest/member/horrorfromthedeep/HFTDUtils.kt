package content.quest.member.horrorfromthedeep

import config.Items
import config.Scenery

object HFTDUtils {

    const val GET_LIGHTHOUSE_KEY = "/save:hftd:key-claim"

    const val FIX_LIGHTHOUSE_MECHANISM = "/save:hftd:lighthouse-fixed"
    const val FIX_BROKEN_BRIDGE = "/save:hftd:lighthouse-bridge"

    const val PUZZLE_PROGRESS = "hftd:item-placed"

    const val BROKEN_BRIDGE_1 = Scenery.BROKEN_BRIDGE_4615
    const val BROKEN_BRIDGE_2 = Scenery.BROKEN_BRIDGE_4616

    const val LIGHTHOUSE_FRONT_DOORS = Scenery.DOORWAY_4577

    const val QUEST_CASKET = Items.RUSTY_CASKET_3849

    val STRANGE_DOORS = intArrayOf(
        Scenery.STRANGE_WALL_4545,
        Scenery.STRANGE_WALL_4546,
    )

    val PUZZLE_DOORS = intArrayOf(
        Scenery.STRANGE_WALL_4544,
        Scenery.STRANGE_WALL_4543,
    )

    val QUEST_ITEMS = intArrayOf(
        Items.BRONZE_ARROW_882, Items.BRONZE_SWORD_1277,
        Items.AIR_RUNE_556, Items.FIRE_RUNE_554,
        Items.EARTH_RUNE_557, Items.WATER_RUNE_555
    )
}