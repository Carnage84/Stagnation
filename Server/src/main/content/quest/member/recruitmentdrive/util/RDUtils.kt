package content.quest.member.recruitmentdrive.util

import config.Items
import config.NPCs
import config.Scenery
import core.api.*
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics

/**
 * Recruitment Drive quest utils.
 **/
object RDUtils {

    const val SPISHYUS = NPCs.SIR_SPISHYUS_2282
    const val LADYTABLE = NPCs.LADY_TABLE_2283
    const val FERENTSE = NPCs.SIR_KUAM_FERENTSE_2284
    const val SIRTINLEY = NPCs.SIR_TINLEY_2286
    const val ITCHOOD = NPCs.SIR_REN_ITCHOOD_2287
    const val CHEEVERS = NPCs.MISS_CHEEVERS_2288
    const val MSHYNN = NPCs.MS_HYNN_TERPRETT_2289
    const val TIFFY = NPCs.SIR_TIFFY_CASHIEN_2290

    const val CUPRIC_SULPHATE = Items.CUPRIC_SULPHATE_5577
    const val ACETIC_ACID = Items.ACETIC_ACID_5578
    const val GYPSUM = Items.GYPSUM_5579
    const val SODIUM_CHLORIDE = Items.SODIUM_CHLORIDE_5580
    const val NITROUS_OXIDE = Items.NITROUS_OXIDE_5581
    const val VIAL_OF_LIQUID = Items.VIAL_OF_LIQUID_5582
    const val TIN_ORE_POWDER = Items.TIN_ORE_POWDER_5583
    const val CUPRIC_ORE_POWDER = Items.CUPRIC_ORE_POWDER_5584
    const val BRONZE_KEY = Items.BRONZE_KEY_5585
    const val METAL_SPADE = Items.METAL_SPADE_5586
    const val METAL_SPADE_BROKEN = Items.METAL_SPADE_5587
    const val UNKNOWN_MIXTURE_1 = Items.UNKNOWN_MIXTURE_5589
    const val UNKNOWN_MIXTURE_2 = Items.UNKNOWN_MIXTURE_5590
    const val UNKNOWN_MIXTURE_3 = Items.UNKNOWN_MIXTURE_5591
    const val EMPTY_TIN = Items.TIN_5592
    const val CHISEL = Items.CHISEL_5601
    const val BRONZE_WIRE = Items.BRONZE_WIRE_5602
    const val SHEARS = Items.SHEARS_5603
    const val MAGNET = Items.MAGNET_5604
    const val RD_KNIFE = Items.KNIFE_5605
    const val MAKEOVER_VOUCHER = Items.MAKEOVER_VOUCHER_5606
    const val HOURGLASS = Items.HOURGLASS_5610

    const val OLDBOOKSHELF_0 = Scenery.OLD_BOOKSHELF_7327
    const val OLDBOOKSHELF_1 = Scenery.OLD_BOOKSHELF_7328
    const val OLDBOOKSHELF_2 = Scenery.OLD_BOOKSHELF_7329
    const val OLDBOOKSHELF_3 = Scenery.OLD_BOOKSHELF_7330

    const val BOOKSHELF_0 = Scenery.SHELVES_7340
    const val BOOKSHELF_2 = Scenery.SHELVES_7333
    const val BOOKSHELF_3 = Scenery.SHELVES_7334
    const val BOOKSHELF_4 = Scenery.SHELVES_7335
    const val BOOKSHELF_5 = Scenery.SHELVES_7336
    const val BOOKSHELF_6 = Scenery.SHELVES_7337
    const val BOOKSHELF_7 = Scenery.SHELVES_7338
    const val BOOKSHELF_8 = Scenery.SHELVES_7339

    const val STONE_DOORS_0 = Scenery.STONE_DOOR_7343
    const val STONE_DOORS_1 = Scenery.STONE_DOOR_7344
    const val STONE_DOORS_2 = 7345
    val STONE_DOORS_3 = getScenery(2477, 4940, 0)

    const val FINAL_DOOR = Scenery.DOOR_7326
    const val KEY_CHAIN = Scenery.KEY_7346

    const val CRATE = Scenery.CRATE_7349
    const val BIG_CRATE = Scenery.CRATE_7347
    const val BIGGER_CRATE = Scenery.CRATE_7348

    const val CLOSED_CHEST = Scenery.CLOSED_CHEST_7350
    const val OPEN_CHEST = Scenery.OPEN_CHEST_7351

    const val BUNSEN_BURNER = Scenery.BUNSEN_BURNER_7332

    const val DOOR_0 = Scenery.DOOR_7323
    const val DOOR_1 = Scenery.DOOR_7274
    const val DOOR_2 = Scenery.DOOR_7302
    const val DOOR_3 = Scenery.DOOR_7354
    const val DOOR_4 = Scenery.DOOR_7320
    const val CLOSED_DOOR = Scenery.DOOR_7317

    val EXITPORTAL = intArrayOf(
        Scenery.PORTAL_7315,
        Scenery.PORTAL_7318,
        Scenery.PORTAL_7321,
        Scenery.PORTAL_7324,
        Scenery.PORTAL_7352,
        Scenery.PORTAL_7272,
        Scenery.PORTAL_7288
    )

    //    <ObjectAction mode="remove" id="5245" x="2481" y="4956"/>
    //    <ObjectAction mode="add" id="7315" x="2481" y="4956"/>

    val eastBorder = ZoneBorders(2483, 4968, 2489, 4976)
    val westBorder = ZoneBorders(2472, 4968, 2476, 4976)

    val BRIDGE_OUT = Scenery.PRECARIOUS_BRIDGE_7286
    val BRIDGE_IN = Scenery.PRECARIOUS_BRIDGE_7287

    val GRAIN_ON_GROUND = Scenery.GRAIN_7284
    val FOX_ON_GROUND = Scenery.FOX_7277
    val CHICKEN_ON_GROUND = Scenery.CHICKEN_7281

    val GRAIN_ITEM = Items.GRAIN_5607
    val FOX_ITEM = Items.FOX_5608
    val CHICKEN_ITEM = Items.CHICKEN_5609

    const val CORRECT_STATUE = Scenery.STATUE_7308
    val STATUES = intArrayOf(7303, 7304, 7305, 7306, 7307, 7310, 7311, 7312, 7313, 7314)
    val TP_OUT_GFX = Graphics(110, 150)
    val USING_BURNER_GFX = Graphics(157, 96)

    val POUR_VIAL = Animation(2259)
    val ADD_INGREDIENTS = Animation(364)

    fun puzzleRoom(player: Player) {
        if (eastBorder.insideBorder(player) || (westBorder.insideBorder(player))) {
            registerLogoutListener(player, "puzzle-room") {
                teleport(player, Location.create(2996, 3375, 0))
                player.inventory.clear()
                player.equipment.clear()
                runTask(player) {
                    clearLogoutListener(player, "puzzle-room")
                }
            }
        }
    }
}