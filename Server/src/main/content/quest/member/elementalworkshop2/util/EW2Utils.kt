package content.quest.member.elementalworkshop2.util

import config.Items
import config.NPCs
import config.Scenery
import core.game.node.entity.npc.NPC
import core.game.world.map.Location

/**
 * Utils for the Elemental Workshop II quest.
 */
object EW2Utils {
    const val foundBook = "/save:ew2:book-found"
    const val questProgress = "/save:ew2:progress"

    const val PullSupport = "/save:ew2:pulled"
    const val TurnSupport = "/save:ew2:turn"
    const val FillingWaterTank = "/save:ew2:fill"

    //----------------  B A R S  -----------------

    const val PrimedBar = Items.PRIMED_BAR_9727
    const val ElementalBar = Items.ELEMENTAL_METAL_2893
    const val ElementalMindBar = Items.ELEMENTAL_MIND_BAR_9728

    //----------------   ANVIL  -----------------
    const val elementalClaw = Items.CRANE_CLAW_9720
    //--------------- V A R B I T S -----------------

    const val EW2_QUEST_VARBIT = 2639
    const val EW2_KEY_VARBIT = 2640
    const val EW2_HATCH_VARBIT = 2641
    const val EW2_PIPE_VARBIT = 2650
    const val EW2_COGS_VARBIT = 2657

    //----------------  C O G S  ------------------

    const val Pin = Scenery.PIN_18666

    const val SmallCog = Items.SMALL_COG_9726
    const val MediumCog = Items.MEDIUM_COG_9725
    const val LargeCog = Items.LARGE_COG_9724

    const val SmallCogCrate = Scenery.CRATE_18612
    const val MediumCogCrate = Scenery.CRATE_18613
    const val LargeCogCrate = Scenery.CRATE_18616

    const val TakeSmallCog = Scenery.COG_18670
    const val TakeMediumCog = Scenery.COG_18671
    const val TakeLargeCog = Scenery.COG_18669

    const val EW2_SMALL_COG_VARBIT = 2655
    const val EW2_MEDIUM_COG_VARBIT = 2657
    const val EW2_LARGE_COG_VARBIT = 2656

    val allCogs = intArrayOf(SmallCog, MediumCog, LargeCog)

    //------------------------------------------

    const val CraneSchematic = Items.CRANE_SCHEMATIC_9718
    const val LeverSchematic = Items.LEVER_SCHEMATIC_9719
    const val SchematicCrate = Scenery.SCHEMATIC_CRATE_18711

    //------------------------------------------

    //--------------- P I P E S -----------------

    const val Pipe = Items.PIPE_9723
    const val PipeCrate = Scenery.CRATE_18507
    const val BrokenPipe = Scenery.PIPING_18650

    //---------------- C R A N E -----------------

    const val OldCrane = Scenery.OLD_CRANE_18623
    const val OldCrane2 = Scenery.OLD_CRANE_18638

    const val BrokenCraneUp = 18758
    const val BrokenCraneDown = 18757

    const val BrokenCraneUpOnCorrectSide = 18760
    const val BrokenCraneDownOnCorrectSide = 18759

    const val CraneJigUp = 18762
    const val CraneJigDown = 18761

    const val CraneDownOnCorrectSide = 18763
    const val CraneUpOnCorrectSide = 18764

    const val CraneElementalBarUp = 18749
    const val CraneElementalBarDown = 18749

    const val CraneElementalBarJigCartUp = 18751
    const val CraneElementalBarJigCartDown = 18750


    //---------------- L E V E R S --------------

    const val PulledLever = 18691

    const val CartLever = Scenery.LEVER_18620

    const val LeftLever = Scenery.AN_OLD_LEVER_18621

    const val RightLever = Scenery.AN_OLD_LEVER_18622

    const val CenterLever = Scenery.AN_OLD_LEVER_18640

    const val NorthLever = Scenery.AN_OLD_LEVER_18648

    const val EastLever = Scenery.AN_OLD_LEVER_18663

    const val CorkscrewLever = Scenery.CORKSCREW_LEVER_18649
    //------------------------------------------

    //-------------------P I P I N G---------------

    const val Piping = Scenery.PIPING_18650
    const val PipingDoorClose = Scenery.DOOR_18651
    const val PipingDoorOpen = Scenery.DOOR_18652
    const val PipingRails = Scenery.DOOR_18653
    const val PipingJigElementalMindBar = Scenery.DOOR_18654
    const val PipingElementalMindBarOpen = Scenery.DOOR_18655
    const val PipingElementalMindBarInside = Scenery.DOOR_18656
    const val PipingJigElementalBar = Scenery.DOOR_18657
    const val PipingJigElementalBarOpen = Scenery.DOOR_18658
    const val PipingJigElementalBarInside = Scenery.DOOR_18659

    const val WaterTankEmpty = Scenery.WATER_TANK_18660 // location(1951, 5164, 2)
    const val WaterTankFull = Scenery.WATER_TANK_18661 // location(1951, 5164, 2)

    //----------------- V A L V E S --------------

    const val WaterValveLeft = Scenery.WATER_VALVE_18646
    const val WaterValveRight = Scenery.WATER_VALVE_18647

    //------------- A N I M A T I O N S --------------

    const val turnValveAnimation = 4861

    const val UsingExtractorHatChairAnimation = 4884
    const val ImproperlyUsingExtractorHatElectricChair = 4885

    const val UsingExtractorHatChairGraphics = 807
    const val ImproperlyUsingExtractorHatChairGraphics = 808

    const val PullCorkscrewLever = 4905
    const val PullLever = 4909
    const val SearchCrate = 6840

    //---------------------------------------------------

    //-------------------  S F X  -------------------

    const val EW2_EXTRACTOR_LOOP2_2325 = 2325
    const val EW2_DIAL_SPIN_2326 = 2326
    const val EW2_EXTRACTOR_OPERATION_3166 = 3166
    const val EW2_CART_LOOP_3167 = 3167
    const val EW2_COOL_BAR_3168 = 3168
    const val EW2_CRANE_LOWER_3169 = 3169
    const val EW2_CRANE_TURN_3170 = 3170
    const val EW2_EXTRACTOR_LOOP_3171 = 3171
    const val EW2_MIND_ELECTROCUTE_3172 = 3172
    const val EW2_EXTRACTOR_ZAP_3173 = 3173
    const val EW2_FANBLADE_3174 = 3174
    const val EW2_MACHINE_RUMBLE_3175 = 3175
    const val EW2_MIND_CONTROL_3176 = 3176
    const val EW2_PLACE_BAR_3177 = 3177
    const val EW2_PLACE_COG_3178 = 3178
    const val EW2_PLACE_PIPE_3179 = 3179
    const val EW2_PRESS_BAR_3180 = 3180
    const val EW2_REMOVE_PIPE_3181 = 3181
    const val EW2_SCREW_TURN_3182 = 3182
    const val EW2_WATER_FLOW_3183 = 3183
    const val EW2_WATER_RACK_3184 = 3184
    const val EW2_WATER_VALVE_3185 = 3185

    //----------------- J I G  C A R T ------------------

    val JigCartNPC = NPC(NPCs.JIG_CART_4913, Location.create(1954, 5147, 2))

    const val EmptyJigCart = NPCs.JIG_CART_4913
    const val JigCartWithElementalBar = NPCs.JIG_CART_4914
    const val JigCartWithElementalMindBar = NPCs.JIG_CART_4915
    const val JigCartWithPrimedElementalMindBar = NPCs.JIG_CART_4916
    const val JigCartWithPrimedElementalBar = NPCs.JIG_CART_4917
    const val JigCartWithPrimedBar = NPCs.JIG_CART_4918

    const val JigCartUnderPressMachine = 18784


    const val EmptyJigCartFrontOfDoors = 18832

    //-------------- I N T E R F A C E S ------------------

    const val JunctionBox = Scenery.JUNCTION_BOX_18641

    const val BlankScroll = 222
    const val SwitchDiagram = 466

    //----------------  U S E L E S S  ------------------

    const val Doors = Scenery.DOOR_18702
}