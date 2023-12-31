package content.global.skill.member.farming
import content.global.skill.member.farming.timers.CropGrowth
import core.api.getOrStartTimer
import core.cache.def.impl.SceneryDefinition
import core.game.node.entity.player.Player
import core.game.node.scenery.Scenery

enum class FarmingPatch(val varbit: Int, val type: PatchType) {

    //Allotments
    S_FALADOR_ALLOTMENT_NW(708,PatchType.ALLOTMENT),
    S_FALADOR_ALLOTMENT_SE(709,PatchType.ALLOTMENT),
    CATHERBY_ALLOTMENT_N(710,PatchType.ALLOTMENT),
    CATHERBY_ALLOTMENT_S(711,PatchType.ALLOTMENT),
    ARDOUGNE_ALLOTMENT_S(713,PatchType.ALLOTMENT),
    ARDOUGNE_ALLOTMENT_N(712,PatchType.ALLOTMENT),
    PORT_PHAS_ALLOTMENT_NW(714,PatchType.ALLOTMENT),
    PORT_PHAS_ALLOTMENT_SE(715,PatchType.ALLOTMENT),
    HARMONY_ISLAND_ALLOTMENT(3402,PatchType.ALLOTMENT),

    //Herb
    CATHERBY_HERB_CE(781,PatchType.HERB),
    S_FALADOR_HERB_NE(780,PatchType.HERB),
    ARDOUGNE_HERB_CE(782,PatchType.HERB),
    PORT_PHAS_HERB_NE(783,PatchType.HERB),
    TROLL_STRONGHOLD_HERB(2788,PatchType.HERB),

    //Flower
    S_FALADOR_FLOWER_C(728,PatchType.FLOWER),
    CATHERBY_FLOWER_C(729,PatchType.FLOWER),
    ARDOUGNE_FLOWER_C(730,PatchType.FLOWER),
    PORT_PHAS_FLOWER_C(731,PatchType.FLOWER),
    WILDERNESS_FLOWER(5067,PatchType.FLOWER),

    //Tree
    N_FALADOR_TREE(701,PatchType.TREE),
    TAVERLY_TREE(700,PatchType.TREE),
    GNOME_STRONGHOLD_TREE(2953,PatchType.TREE),
    LUMBRIDGE_TREE(703,PatchType.TREE),
    VARROCK_TREE(702,PatchType.TREE),

    //Fruit Tree
    GNOME_STRONGHOLD_FRUIT_TREE(704,PatchType.FRUIT_TREE),
    CATHERBY_FRUIT_TREE(707,PatchType.FRUIT_TREE),
    TREE_GNOME_VILLAGE_FRUIT_TREE(705,PatchType.FRUIT_TREE),
    BRIMHAVEN_FRUIT_TREE(706,PatchType.FRUIT_TREE),
    LLETYA_FRUIT_TREE(4317,PatchType.FRUIT_TREE),

    //Hops
    ENTRANA_HOPS(717,PatchType.HOPS),
    LUMBRIDGE_HOPS(718,PatchType.HOPS),
    MCGRUBOR_HOPS(719,PatchType.HOPS),
    YANILLE_HOPS(716,PatchType.HOPS),

    //Bushes
    CHAMPIONS_GUILD_BUSH(732,PatchType.BUSH),
    RIMMINGTON_BUSH(733,PatchType.BUSH),
    ARDOUGNE_BUSH(735,PatchType.BUSH),
    ETCETERIA_BUSH(734,PatchType.BUSH),

    //Spirit Tree
    ETCETERIA_SPIRIT_TREE(722,PatchType.SPIRIT_TREE),
    PORT_SARIM_SPIRIT_TREE(720,PatchType.SPIRIT_TREE),
    KARAMJA_SPIRIT_TREE(724,PatchType.SPIRIT_TREE),

    //Other
    DRAYNOR_BELLADONNA(748, PatchType.BELLADONNA),
    CANIFIS_MUSHROOM(746, PatchType.MUSHROOM),
    ALKHARID_CACTUS(744, PatchType.CACTUS),
    EVIL_TURNIP(4291, PatchType.EVIL_TURNIP);


    companion object {
        @JvmField
        val patches = FarmingPatch.values().map { it.varbit to it }.toMap()
        val patchNodes = ArrayList<Int>()
        val nodeMap = HashMap<Int, SceneryDefinition>()

        init {
            patchNodes.addAll(8550..8557) //allotment wrappers
            patchNodes.addAll(7847..7853) //flower patch wrappers
            patchNodes.addAll(8150..8156) //herb patch wrappers
            patchNodes.addAll(8388..8391) // Tree patches
            patchNodes.add(19147) //Tree patch
            patchNodes.addAll(7962..7965) //fruit trees
            patchNodes.addAll(8173..8176) //hops
            patchNodes.addAll(7577..7580) //bush
            patchNodes.add(23760) //evil turnip
            patchNodes.add(7572) //belladonna
            patchNodes.add(8337) //mushroom
            patchNodes.add(27197) //jade vine
            patchNodes.add(7771) //cactus
            patchNodes.add(7807) //calquat
            patchNodes.addAll(8382..8383)//spirit trees
            patchNodes.add(8338) //spirit tree
            patchNodes.add(18816) //death plateau wrapper

            for (patch in patchNodes) {
                val def = SceneryDefinition.forId(patch)
                nodeMap[def.varbitID] = def
            }
        }

        @JvmStatic
        fun forObject(obj: Scenery): FarmingPatch?{
            return forObjectID(obj.id)
        }

        @JvmStatic
        fun forObjectID(id: Int): FarmingPatch?{
            val objDef = SceneryDefinition.forId(id)
            return patches[objDef.varbitID]
        }

        fun getSceneryDefByVarbit (id: Int) : SceneryDefinition? {
            return nodeMap[id]
        }
    }

    fun getPatchFor(player: Player): Patch{
        var crops = getOrStartTimer <CropGrowth> (player)!!
        return crops.getPatch(this)
    }
}
