package content.global.skill.member.farming

import config.Items
import core.game.node.item.Item

enum class Plantable(val itemID: Int, val value: Int, val stages: Int, val plantingXP: Double, val harvestXP: Double, val checkHealthXP: Double, val requiredLevel: Int, val applicablePatch: PatchType, val harvestItem: Int, val protectionItem: Item? = null, val protectionFlower: Plantable? = null) {

    //Flowers
    MARIGOLD_SEED(5096,8,4,8.5,47.0,0.0,2, PatchType.FLOWER, Items.MARIGOLDS_6010),
    ROSEMARY_SEED(5097,13,4,12.0,66.5,0.0,11, PatchType.FLOWER, Items.ROSEMARY_6014),
    NASTURTIUM_SEED(5098,18,4,19.5,111.0,0.0,24, PatchType.FLOWER, Items.NASTURTIUMS_6012),
    WOAD_SEED(5099,23,4,20.5,115.5,0.0,25, PatchType.FLOWER, Items.WOAD_LEAF_1793),
    LIMPWURT_SEED(5100,28,4,21.5,120.0,0.0,26, PatchType.FLOWER, Items.LIMPWURT_ROOT_225),
    WHITE_LILY_SEED(14589,37,4,42.0,250.0,0.0,52, PatchType.FLOWER, Items.WHITE_LILY_14583),

    //Flower(Technically)
    SCARECROW(6059,33,3,0.0,0.0,0.0,23, PatchType.FLOWER, Items.SCARECROW_6059),

    //Allotments
    POTATO_SEED(5318, 6, 4, 8.0, 9.0, 0.0, 1,
        PatchType.ALLOTMENT, Items.POTATO_1942,Item(Items.COMPOST_6032,2),
        MARIGOLD_SEED
    ),
    ONION_SEED(5319, 13, 4, 9.5, 10.5,0.0, 5,
        PatchType.ALLOTMENT, Items.ONION_1957,Item(Items.POTATOES10_5438),
        MARIGOLD_SEED
    ),
    CABBAGE_SEED(5324, 20, 4, 10.0, 11.5, 0.0,7,
        PatchType.ALLOTMENT, Items.CABBAGE_1965,Item(Items.ONIONS10_5458),
        ROSEMARY_SEED
    ),
    TOMATO_SEED(5322,27,4,12.5,14.0,0.0,12,
        PatchType.ALLOTMENT, Items.TOMATO_1982,Item(Items.CABBAGES10_5478,2),
        MARIGOLD_SEED
    ),
    SWEETCORN_SEED(5320,34,6,17.0,19.0,0.0,20,
        PatchType.ALLOTMENT, Items.SWEETCORN_5986,Item(Items.JUTE_FIBRE_5931,10),
        SCARECROW
    ),
    STRAWBERRY_SEED(5323,43,6,26.0,29.0,0.0,31, PatchType.ALLOTMENT, Items.STRAWBERRY_5504,Item(Items.APPLES5_5386)),
    WATERMELON_SEED(5321,52,8,48.5,54.5,0.0,47,
        PatchType.ALLOTMENT, Items.WATERMELON_5982,Item(Items.CURRY_LEAF_5970,10),
        NASTURTIUM_SEED
    ),

    //Hops
    BARLEY_SEED(5305,49,4,8.5,9.5,0.0,3, PatchType.HOPS, Items.BARLEY_6006,Item(Items.COMPOST_6032,3)),
    HAMMERSTONE_SEED(5307,4,4,9.0,10.0,0.0,4, PatchType.HOPS, Items.HAMMERSTONE_HOPS_5994,Item(Items.MARIGOLDS_6010)),
    ASGARNIAN_SEED(5308,11,5,10.9,12.0,0.0,8, PatchType.HOPS, Items.ASGARNIAN_HOPS_5996,Item(Items.ONIONS10_5458)),
    JUTE_SEED(5306,56,5,13.0,14.5,0.0,13, PatchType.HOPS, Items.JUTE_FIBRE_5931,Item(Items.BARLEY_MALT_6008,6)),
    YANILLIAN_SEED(5309,19,6,14.5,16.0,0.0,16, PatchType.HOPS, Items.YANILLIAN_HOPS_5998,Item(Items.TOMATOES5_5968)),
    KRANDORIAN_SEED(5310,28,7,17.5,19.5,0.0,21, PatchType.HOPS, Items.KRANDORIAN_HOPS_6000,Item(Items.CABBAGES10_5478,3)),
    WILDBLOOD_SEED(5311,38,8,23.0,26.0,0.0,28, PatchType.HOPS, Items.WILDBLOOD_HOPS_6002,Item(Items.NASTURTIUMS_6012)),

    //Trees
    OAK_SAPLING(5370,8,4,14.0,0.0,467.3,15, PatchType.TREE, Items.OAK_ROOTS_6043,Item(Items.TOMATOES5_5968)),
    WILLOW_SAPLING(5371,15,6,25.0,0.0,1456.5,30, PatchType.TREE, Items.WILLOW_ROOTS_6045,Item(Items.APPLES5_5386)),
    MAPLE_SAPLING(5372,24,8,45.0,0.0,3403.4,45, PatchType.TREE, Items.MAPLE_ROOTS_6047,Item(Items.ORANGES5_5396)),
    YEW_SAPLING(5373,35,10,81.0,0.0,7069.9,60, PatchType.TREE, Items.YEW_ROOTS_6049,Item(Items.CACTUS_SPINE_6016,10)),
    MAGIC_SAPLING(5374,48,12,145.5,0.0,13768.3,75, PatchType.TREE, Items.MAGIC_ROOTS_6051,Item(Items.COCONUT_5974,25)),

    //Fruit Trees
    APPLE_SAPLING(5496,8,6,22.0,8.5,1199.5,27, PatchType.FRUIT_TREE,
        Items.COOKING_APPLE_1955,Item(Items.SWEETCORN_5986,9)),
    BANANA_SAPLING(5497,35,6,28.0,10.5,1750.5,33, PatchType.FRUIT_TREE, Items.BANANA_1963,Item(Items.APPLES5_5386,4)),
    ORANGE_SAPLING(5498,72,6,35.5,13.5,2470.2,39, PatchType.FRUIT_TREE,
        Items.ORANGE_2108,Item(Items.STRAWBERRIES5_5406,3)),
    CURRY_SAPLING(5499,99,6,40.0,15.0,2906.9,42, PatchType.FRUIT_TREE, Items.CURRY_LEAF_5970,Item(Items.BANANAS5_5416,5)),
    PINEAPPLE_SAPLING(5500,136,6,57.0,21.5,4605.7,51,
        PatchType.FRUIT_TREE, Items.PINEAPPLE_2114,Item(Items.WATERMELON_5982,10)),
    PAPAYA_SAPLING(5501,163,6,72.0,27.0,6146.4,57,
        PatchType.FRUIT_TREE, Items.PAPAYA_FRUIT_5972,Item(Items.PINEAPPLE_2114,10)),
    PALM_SAPLING(5502,200,6,110.5,41.5,10150.1,68,
        PatchType.FRUIT_TREE, Items.COCONUT_5974,Item(Items.PAPAYA_FRUIT_5972,15)),

    //Bushes
    REDBERRY_SEED(5101,5,5,11.5,4.5,64.0,10, PatchType.BUSH, Items.REDBERRIES_1951,Item(Items.CABBAGES10_5478,4)),
    CADAVABERRY_SEED(5102,15,6,18.0,7.0,102.5,22, PatchType.BUSH, Items.CADAVA_BERRIES_753,Item(Items.TOMATOES5_5968,3)),
    DWELLBERRY_SEED(5103,26,27,31.5,12.0,177.5,36,
        PatchType.BUSH, Items.DWELLBERRIES_2126,Item(Items.STRAWBERRIES5_5406,3)),
    JANGERBERRY_SEED(5104,38,8,50.5,19.0,284.5,48, PatchType.BUSH, Items.JANGERBERRIES_247,Item(Items.WATERMELON_5982,6)),
    WHITEBERRY_SEED(5105,51,8,78.0,29.0,437.5,59, PatchType.BUSH, Items.WHITE_BERRIES_239,null),
    POISON_IVY_SEED(5106,197,8,120.0,45.0,675.0,70, PatchType.BUSH, Items.POISON_IVY_BERRIES_6018,null),

    //Herbs
    GUAM_SEED(5291,4,4,11.0,12.5,0.0,9, PatchType.HERB, Items.GRIMY_GUAM_199),
    MARRENTILL_SEED(5292,11,4,13.5,15.0,0.0,14, PatchType.HERB, Items.GRIMY_MARRENTILL_201),
    TARROMIN_SEED(5293,18,4,16.0,18.0,0.0,19, PatchType.HERB, Items.GRIMY_TARROMIN_203),
    HARRALANDER_SEED(5294,25,4,21.5,24.0,0.0,26, PatchType.HERB, Items.GRIMY_HARRALANDER_205),
    RANARR_SEED(5295,32,4,27.0,30.5,0.0,32, PatchType.HERB, Items.GRIMY_RANARR_207),
    AVANTOE_SEED(5298,39,4,54.5,61.5,0.0,50, PatchType.HERB, Items.GRIMY_AVANTOE_211),
    TOADFLAX_SEED(5296,46,4,34.0,38.5,0.0,38, PatchType.HERB, Items.GRIMY_TOADFLAX_3049),
    IRIT_SEED(5297,53,4,43.0,48.5,0.0,44, PatchType.HERB, Items.GRIMY_IRIT_209),
    KWUARM_SEED(5299,68,4,69.0,78.0,0.0,56, PatchType.HERB, Items.GRIMY_KWUARM_213),
    SNAPDRAGON_SEED(5300,75,4,87.5,98.5,0.0,62, PatchType.HERB, Items.GRIMY_SNAPDRAGON_3051),
    CADANTINE_SEED(5301,82,4,106.5,120.0,0.0,67, PatchType.HERB, Items.GRIMY_CADANTINE_215),
    LANTADYME_SEED(5302,89,4,134.5,151.5,0.0,73, PatchType.HERB, Items.GRIMY_LANTADYME_2485),
    DWARF_WEED_SEED(5303,96,4,170.5,192.0,0.0,79, PatchType.HERB, Items.GRIMY_DWARF_WEED_217),
    TORSTOL_SEED(5304,103,4,199.5,224.5,0.0,85, PatchType.HERB, Items.GRIMY_TORSTOL_219),
    GOUT_TUBER(6311,192,4,105.0,45.0,0.0,29, PatchType.HERB, Items.GOUTWEED_3261),
    SPIRIT_WEED_SEED(12176, 204, 4, 32.0, 36.0, 0.0, 36, PatchType.HERB, Items.GRIMY_SPIRIT_WEED_12174),

    //Other
    BELLADONNA_SEED(5281, 4, 4, 91.0, 128.0, 0.0, 63, PatchType.BELLADONNA, Items.CAVE_NIGHTSHADE_2398),
    MUSHROOM_SPORE(Items.MUSHROOM_SPORE_5282, 6, 7, 61.5, 57.7, 0.0, 53, PatchType.MUSHROOM, Items.MUSHROOM_6004),
    CACTUS_SEED(Items.CACTUS_SEED_5280, 8, 7, 66.5, 25.0, 374.0, 55, PatchType.CACTUS, Items.CACTUS_SPINE_6016),
    EVIL_TURNIP_SEED(Items.EVIL_TURNIP_SEED_12148, 4, 1, 41.0, 46.0, 0.0, 42, PatchType.EVIL_TURNIP, Items.EVIL_TURNIP_12134)
    ;

    constructor(itemID: Int, value: Int, stages: Int, plantingXP: Double, harvestXP: Double, checkHealthXP: Double, requiredLevel: Int, applicablePatch: PatchType, harvestItem: Int, protectionFlower: Plantable)
            : this(itemID,value,stages,plantingXP,harvestXP,checkHealthXP,requiredLevel,applicablePatch,harvestItem,null,protectionFlower)
    companion object {
        @JvmField
        val plantables = values().map { it.itemID to it }.toMap()

        @JvmStatic
        fun forItemID(id: Int): Plantable?{
            return plantables[id]
        }

        @JvmStatic
        fun forItem(item: Item): Plantable?{
            return forItemID(item.id)
        }
    }
}
