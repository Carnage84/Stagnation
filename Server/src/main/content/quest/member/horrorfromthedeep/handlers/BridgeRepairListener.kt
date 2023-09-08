package content.quest.member.horrorfromthedeep.handlers

import config.Items
import config.NPCs
import content.quest.member.horrorfromthedeep.HFTDUtils
import content.quest.member.horrorfromthedeep.dialogue.JossikHFTDUndergroundDialogue
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.impl.ForceMovement
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.map.Direction
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation

/**
 * Represents the side interactions related to quest.
 */
class BridgeRepairListener : InteractionListener {
    override fun defineListeners() {

        on(NPCs.JOSSIK_1335, IntType.NPC, "talk-to") { player, _ ->
            openDialogue(player, JossikHFTDUndergroundDialogue())
            return@on true
        }

        // Bridge side between Rellekka & Lighthouse.
        onUseWith(IntType.SCENERY, Items.PLANK_960, HFTDUtils.BROKEN_BRIDGE_1) { player, _, _ ->
            if (getQuestStage(player, "Horror from the Deep") == 4 && inInventory(player, Items.HAMMER_2347) &&
                removeItem(player, Item(Items.STEEL_NAILS_1539, 30)) &&
                removeItem(player, Item(Items.PLANK_960, 1))) {
                lock(player, 2)
                animate(player, 898)
                setAttribute(player, HFTDUtils.FIX_BROKEN_BRIDGE, 1)
                setQuestStage(player, "Horror from the Deep", 10)
                sendDialogue(player, "You create half a makeshift walkway out of the plank.")
            } else if (!inInventory(player, Items.HAMMER_2347)) {
                sendDialogue(player, "You need a hammer to force the nails in with.")
            } else {
                sendDialogue(player, "You need 30 steel nails to attach the plank with.")
            }
            return@onUseWith true
        }

        // Bridge side between Lighthouse & Rellekka.

        onUseWith(IntType.SCENERY, Items.PLANK_960, HFTDUtils.BROKEN_BRIDGE_2) { player, _, _ ->
            if (getQuestStage(player, "Horror from the Deep") == 10 &&
                getAttribute(player, HFTDUtils.FIX_BROKEN_BRIDGE, -1) == 1 &&
                inInventory(player, Items.HAMMER_2347) &&
                removeItem(player, Item(Items.STEEL_NAILS_1539, 30)) &&
                removeItem(player, Item(Items.PLANK_960, 1))) {
                lock(player, 2)
                animate(player, 898)
                setAttribute(player, HFTDUtils.FIX_BROKEN_BRIDGE, 2)
                setQuestStage(player, "Horror from the Deep", 20)
                sendDialogue(player, "You have now made a makeshift walkway over the bridge.")
            } else if (!inInventory(player, Items.HAMMER_2347)) {
                sendDialogue(player, "You need a hammer to force the nails in with.")
            } else {
                sendDialogue(player, "You need 30 steel nails to attach the plank with.")
            }
            return@onUseWith true
        }

        // Cross bridge to Rellekka.

        on(HFTDUtils.BROKEN_BRIDGE_1, IntType.SCENERY, "Cross") { player, node ->
            if (getAttribute(player, HFTDUtils.FIX_BROKEN_BRIDGE, 0) >= 1 ||
                isQuestComplete(player, "Horror from the Deep")) {
                if (!player.location.withinDistance(node.asScenery().location, 2)) return@on true
                lock(player, 6)
                submitIndividualPulse(player, object : Pulse(1) {
                    var count = 0
                    override fun pulse(): Boolean {
                        when (count++) {
                            0 -> {
                                ForceMovement.run(
                                    player,
                                    Location(2596, 3608, 0),
                                    Location.create(2597, 3608, 0),
                                    Animation(819),
                                    Animation(757),
                                    Direction.EAST
                                )
                            }
                            2 -> {
                                ForceMovement.run(
                                    player,
                                    Location(2597, 3608, 0),
                                    Location.create(2598, 3608, 0),
                                    Animation(756),
                                    Animation(759),
                                    Direction.EAST
                                )
                                return true
                            }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "I might be able to make to the other side.")
            }
            return@on true
        }

        // Cross bridge to Lighthouse.

        on(HFTDUtils.BROKEN_BRIDGE_2, IntType.SCENERY, "Cross") { player, node ->
            if (getAttribute(player, HFTDUtils.FIX_BROKEN_BRIDGE, 0) >= 2 ||
                isQuestComplete(player, "Horror from the Deep")) {
                if (!player.location.withinDistance(node.asScenery().location, 2)) return@on true
                lock(player, 6)
                submitIndividualPulse(player, object : Pulse(1) {
                    var count = 0
                    override fun pulse(): Boolean {
                        when (count++) {
                            0 -> {
                                ForceMovement.run(player, Location(2598, 3608, 0), Location.create(2597, 3608, 0), Animation(819), Animation(755), Direction.WEST)
                            }
                            2 -> {
                                ForceMovement.run(player, Location(2597, 3608, 0), Location.create(2596, 3608, 0), Animation(754), Animation(758), Direction.WEST)
                            }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "I might be able to make to the other side.")
            }
            return@on true
        }
    }
}
