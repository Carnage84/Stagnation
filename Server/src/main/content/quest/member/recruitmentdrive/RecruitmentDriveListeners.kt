package content.quest.member.recruitmentdrive

import config.Items
import config.NPCs
import content.quest.member.recruitmentdrive.dialogue.KnightNotesRDDialogue
import content.quest.member.recruitmentdrive.dialogue.LadyTableRDDialogue
import content.quest.member.recruitmentdrive.dialogue.MissCheeversRDDialogue
import content.quest.member.recruitmentdrive.dialogue.SirTiffyCashienRDDialogue
import content.quest.member.recruitmentdrive.npc.SirKuamFerentseDialogue
import content.quest.member.recruitmentdrive.npc.SirRenItchoodDialogue
import content.quest.member.recruitmentdrive.npc.SirSpishyusDialogue
import content.quest.member.recruitmentdrive.npc.SirTinleyDialogue
import content.quest.member.recruitmentdrive.util.RDUtils
import core.api.*
import core.game.container.impl.EquipmentContainer
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.Entity
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders

/**
 * Listener for Recruitment Drive quest.
 */
class RecruitmentDriveListeners : InteractionListener, MapArea {

    companion object {
        val chickenTaken = "recruitmentdrive:chicken-taken"
        val grainTaken = "recruitmentdrive:grain-taken"
        val foxTaken = "recruitmentdrive:fox-taken"

        val chickenPlaced = "recruitmentdrive:chicken-placed"
        val grainPlaced = "recruitmentdrive:grain-placed"
        val foxPlaced = "recruitmentdrive:fox-placed"
    }
    override fun areaLeave(entity: Entity, logout: Boolean) {
        super.areaLeave(entity, logout)
        if (entity is Player && !entity.isArtificial) {
            val player = entity.asPlayer()
            if (!getRegionBorders(9805).insideRegion(player)) {
                player.inventory.clear().also { player.equipment.clear() }
            }
        }
    }

    override fun defineAreaBorders(): Array<ZoneBorders> {
        return arrayOf(getRegionBorders(9805))
    }
    
    fun checkProgress(player: Player) {
        if (!getAttribute(player,"recruitmentdrive:puzzle-complete", false)) {
            val correctPlacedChicken = getAttribute(player,chickenPlaced, 0)
            val correctPlacedGrain = getAttribute(player, grainPlaced, 0)
            val correctPlacedFox = getAttribute(player, foxPlaced, 0)

            val correctTakenChicken = getAttribute(player,chickenTaken, 0)
            val correctTakenGrain = getAttribute(player,grainTaken, 0)
            val correctTakenFox = getAttribute(player, foxTaken, 0)

            if (correctPlacedFox == 2 && correctPlacedGrain == 2 && correctPlacedChicken == 3 && correctTakenChicken == 3 && correctTakenGrain == 1 && correctTakenFox == 1) {
                sendMessage(player, "Congratulations! You have solved this room's puzzle!")
                player.questRepository.getQuest("Recruitment Drive").setStage(player, 40)
                removeAttributes(player, "recruitmentdrive:grain-placed", "recruitmentdrive:fox-placed", "recruitmentdrive:chicken-taken", "recruitmentdrive:grain-taken", "recruitmentdrive:fox-taken", "recruitmentdrive:puzzle-complete"
                )
            }
        }
    }

    fun puzzleRoom(player: Player) {
        if (RDUtils.eastBorder.insideBorder(player) || (RDUtils.westBorder.insideBorder(player))) {
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

    override fun defineListeners() {

        on(RDUtils.ITCHOOD, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, SirRenItchoodDialogue(), npc)
            return@on true
        }

        on(RDUtils.SPISHYUS, IntType.NPC, "talk-to") { player, npc ->
            face(NPC(RDUtils.SPISHYUS), player, 1)
            openDialogue(player, SirSpishyusDialogue(), npc)
            return@on true
        }

        on(RDUtils.SIRTINLEY, IntType.NPC, "talk-to") { player, npc ->
            if (player.questRepository.getStage("Recruitment Drive") == 46) {
                openDialogue(player, SirTinleyDialogue(), npc)
            } else if (player.questRepository.getStage("Recruitment Drive") == 50) {
                runTask(player, 3) {
                    teleport(player, location(2996, 3375, 0))
                    sendNPCDialogue(player, RDUtils.SIRTINLEY, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also { player.faceLocation(npc.location) }
                }
            } else {
                sendNPCDialogue(player, RDUtils.SIRTINLEY, "Patience is a virtue that few possess in this world. Excellent work, Player. Please step through the portal to meet your next challenge.")
            }
            return@on true
        }

        on(RDUtils.CHEEVERS, IntType.NPC, "talk-to") { player, npc ->
            face(NPC(RDUtils.CHEEVERS), player, 1)
            openDialogue(player, MissCheeversRDDialogue(), npc)
            return@on true
        }

        on(RDUtils.FERENTSE, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, SirKuamFerentseDialogue(), npc)
            return@on true
        }

        on(RDUtils.LADYTABLE, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, LadyTableRDDialogue(), npc)
            return@on true
        }

        on(RDUtils.TIFFY, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, SirTiffyCashienRDDialogue(), npc)
            return@on true
        }

        onUseWith(IntType.NPC, Items.KNIGHTS_NOTES_11734, NPCs.SIR_TIFFY_CASHIEN_2290) { player, _, with ->
            openDialogue(player, KnightNotesRDDialogue(), with)
            return@onUseWith true
        }

        onUseWith(IntType.NPC, Items.KNIGHTS_NOTES_11735, NPCs.SIR_TIFFY_CASHIEN_2290) { player, _, with ->
            openDialogue(player, KnightNotesRDDialogue.BrokenKnightNotes(), with)
            return@onUseWith true
        }

        on(RDUtils.BRIDGE_OUT, IntType.SCENERY, "cross") { player, node ->
            when (player.location.x) {
                2482 -> DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                    .also { sendMessage(player, "You carefully walk across the rickety bridge...") }
                    .also { player.unlock() }

                2484 -> if (allInEquipment(player, RDUtils.GRAIN_ITEM) && allInEquipment(player, RDUtils.FOX_ITEM) ||
                    (allInEquipment(player, RDUtils.FOX_ITEM)
                            && allInEquipment(player, RDUtils.CHICKEN_ITEM)) ||
                    (allInEquipment(player, RDUtils.CHICKEN_ITEM)
                            && allInEquipment(player, RDUtils.GRAIN_ITEM))) {
                    sendDialogue(player, "I really don't think I should be carrying more than 5Kg across that rickety bridge...")
                } else {
                    DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                        .also { sendMessage(player, "You carefully walk across the rickety bridge...") }
                        .also { player.unlock() }
                }
            }
            return@on true
        }

        on(RDUtils.BRIDGE_IN, IntType.SCENERY, "cross") { player, node ->
            when (player.location.x) {
                2478 -> DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                    .also { sendMessage(player, "You carefully walk across the rickety bridge...") }
                    .also { player.unlock() }

                2476 -> if (allInEquipment(player, RDUtils.GRAIN_ITEM) && allInEquipment(player, RDUtils.FOX_ITEM) || (allInEquipment(player, RDUtils.FOX_ITEM) && allInEquipment(player, RDUtils.CHICKEN_ITEM)) || (allInEquipment(player, RDUtils.CHICKEN_ITEM) && allInEquipment(player, RDUtils.GRAIN_ITEM))) {
                    sendDialogue(player, "I really don't think I should be carrying more than 5Kg across that rickety bridge...")
                } else {
                    DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                        .also { sendMessage(player, "You carefully walk across the rickety bridge...") }
                        .also { player.unlock() }
                }
            }
            return@on true
        }

        on(RDUtils.CHICKEN_ON_GROUND, IntType.SCENERY, "pick-up") { player, _ ->
            val chickenTaken = getAttribute(player,chickenTaken, 0)
            val grainTaken = getAttribute(player,grainTaken, 0)
            val foxTaken = getAttribute(player,foxTaken, 0)

            val chickenPlaced = getAttribute(player,chickenPlaced, 0)
            val grainPlaced = getAttribute(player,grainPlaced, 0)
            val foxPlaced = getAttribute(player,foxPlaced, 0)

            if (player.equipment[EquipmentContainer.SLOT_SHIELD] != null) {
                sendMessage(player, "You can't pick them on while wearing armour.")
                return@on false
            }

            if (RDUtils.eastBorder.insideBorder(player)) {
                if (chickenPlaced == 0 && grainPlaced == 0 && foxPlaced == 0 && foxTaken == 0 && grainTaken == 0 && chickenTaken == 0) {
                    setAttribute(player,"recruitmentdrive:chicken-taken", 1)
                } else if (chickenPlaced == 2 && grainPlaced == 2 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 1 && chickenTaken == 2) {
                    setAttribute(player,"recruitmentdrive:chicken-taken", 3)
                }
                player.equipment.add(Item(RDUtils.CHICKEN_ITEM), 5, true, false)
                setVarbit(player, 682, 1, true)
                checkProgress(player)
                return@on true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                if (chickenPlaced == 1 && grainPlaced == 0 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 0 && chickenTaken == 1) {
                    setAttribute(player,"recruitmentdrive:chicken-placed", 2)
                }
                player.equipment.add(Item(RDUtils.CHICKEN_ITEM), 5, true, false)
                setVarbit(player, 683, 2, true)
                checkProgress(player)
                return@on true
            }
            return@on true
        }

        onUnequip(RDUtils.CHICKEN_ITEM) { player, _ ->
            val chickenTaken = getAttribute(player,chickenTaken, 0)
            val grainTaken = getAttribute(player,grainTaken, 0)
            val foxTaken = getAttribute(player,foxTaken, 0)

            val chickenPlaced = getAttribute(player,chickenPlaced, 0)
            val grainPlaced = getAttribute(player,grainPlaced, 0)
            val foxPlaced = getAttribute(player,foxPlaced, 0)

            if (RDUtils.eastBorder.insideBorder(player)) {
                if (chickenPlaced == 2 && grainPlaced == 0 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 0 && chickenTaken == 1) {
                    setAttribute(player,"recruitmentdrive:chicken-taken", 2)
                }
                removeItem(player, Item(RDUtils.CHICKEN_ITEM), Container.EQUIPMENT)
                setVarbit(player, 682, 2, true)
                checkProgress(player)
                return@onUnequip true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                if (chickenPlaced == 0 && grainPlaced == 0 && foxPlaced == 0 && foxTaken == 0 && grainTaken == 0 && chickenTaken == 1) {
                    setAttribute(player,"recruitmentdrive:chicken-placed", 1)
                } else if (chickenPlaced == 2 && grainPlaced == 2 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 1 && chickenTaken == 3) {
                    setAttribute(player,"recruitmentdrive:chicken-placed", 3)
                }
                removeItem(player, Item(RDUtils.CHICKEN_ITEM), Container.EQUIPMENT)
                setVarbit(player, 683, 1, true)
                checkProgress(player)
                return@onUnequip true
            }
            return@onUnequip true
        }

        on(RDUtils.GRAIN_ON_GROUND, IntType.SCENERY, "pick-up") { player, _ ->
            if (player.equipment[EquipmentContainer.SLOT_CAPE] != null) {
                sendMessage(player, "You can't pick them on while wearing armour.")
                return@on false
            }

            if (RDUtils.eastBorder.insideBorder(player)) {
                val chickenTaken = getAttribute(player,chickenTaken, 0)
                val grainTaken = getAttribute(player,grainTaken, 0)
                val foxTaken = getAttribute(player,foxTaken, 0)

                val chickenPlaced = getAttribute(player,chickenPlaced, 0)
                val grainPlaced = getAttribute(player,grainPlaced, 0)
                val foxPlaced = getAttribute(player,foxPlaced, 0)

                if (chickenPlaced == 2 && grainPlaced == 0 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 0 && chickenTaken == 2) {
                    setAttribute(player,"recruitmentdrive:grain-taken", 1)
                }
                player.equipment.add(Item(RDUtils.GRAIN_ITEM), 1, true, false)
                setVarbit(player, 684, 1, true)
                
                checkProgress(player)
                return@on true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                player.equipment.add(Item(RDUtils.GRAIN_ITEM), 1, true, false)
                setVarbit(player, 685, 2, true)
                return@on true
            }
            return@on true
        }

        onUnequip(RDUtils.GRAIN_ITEM) { player, _ ->
            val chickenTaken = getAttribute(player,chickenTaken, 0)
            val grainTaken = getAttribute(player,grainTaken, 0)
            val foxTaken = getAttribute(player,foxTaken, 0)

            val chickenPlaced = getAttribute(player,chickenPlaced, 0)
            val grainPlaced = getAttribute(player,grainPlaced, 0)
            val foxPlaced = getAttribute(player,foxPlaced, 0)

            if (RDUtils.eastBorder.insideBorder(player.location)) {
                removeItem(player, Item(RDUtils.GRAIN_ITEM), Container.EQUIPMENT)
                setVarbit(player, 684, 8, true)
                return@onUnequip true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                if (chickenPlaced == 2 && grainPlaced == 0 && foxPlaced == 2 && foxTaken == 1 && grainTaken == 1 && chickenTaken == 2) {
                    setAttribute(player,"recruitmentdrive:grain-placed", 2)
                }
                removeItem(player, Item(RDUtils.GRAIN_ITEM), Container.EQUIPMENT)
                setVarbit(player, 685, 1, true)
                checkProgress(player)
                return@onUnequip true
            }
            return@onUnequip true
        }

        on(RDUtils.FOX_ON_GROUND, IntType.SCENERY, "pick-up") { player, _ ->
            val chickenTaken = getAttribute(player,chickenTaken, 0)
            val grainTaken = getAttribute(player,grainTaken, 0)
            val foxTaken = getAttribute(player,foxTaken, 0)

            val chickenPlaced = getAttribute(player,chickenPlaced, 0)
            val grainPlaced = getAttribute(player,grainPlaced, 0)
            val foxPlaced = getAttribute(player,foxPlaced, 0)

            if (player.equipment[EquipmentContainer.SLOT_WEAPON] != null) {
                sendMessage(player, "You can't pick them on while wearing armour.")
                return@on false
            }

            if (RDUtils.eastBorder.insideBorder(player)) {
                if (chickenPlaced == 1 && grainPlaced == 0 && foxPlaced == 0 && foxTaken == 0 && grainTaken == 0 && chickenTaken == 1) {
                    setAttribute(player,"recruitmentdrive:fox-taken", 1)
                }
                player.equipment.add(Item(RDUtils.FOX_ITEM), 3, true, false)
                setVarbit(player, 680, 1, true)
                checkProgress(player)
                return@on true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                player.equipment.add(Item(RDUtils.FOX_ITEM), 3, true, false)
                setVarbit(player, 681, 2, true)
                return@on true
            }
            return@on true
        }

        onUnequip(RDUtils.FOX_ITEM) { player, _ ->
            val chickenTaken = getAttribute(player,chickenTaken, 0)
            val grainTaken = getAttribute(player,grainTaken, 0)
            val foxTaken = getAttribute(player,foxTaken, 0)

            val chickenPlaced = getAttribute(player,chickenPlaced, 0)
            val grainPlaced = getAttribute(player,grainPlaced, 0)
            val foxPlaced = getAttribute(player,foxPlaced, 0)

            if (RDUtils.eastBorder.insideBorder(player.location)) {
                removeItem(player, Item(RDUtils.FOX_ITEM), Container.EQUIPMENT)
                setVarbit(player, 680, 2, true)
                return@onUnequip true
            }

            if (RDUtils.westBorder.insideBorder(player)) {
                if (chickenPlaced == 1 && grainPlaced == 0 && foxPlaced == 0 && foxTaken == 1 && grainTaken == 0 && chickenTaken == 1) {
                    setAttribute(player,"recruitmentdrive:fox-placed", 2)
                }
                removeItem(player, Item(RDUtils.FOX_ITEM), Container.EQUIPMENT)
                setVarbit(player, 681, 1, true)
                checkProgress(player)
                return@onUnequip true
            }
            return@onUnequip true
        }


    on(RDUtils.CORRECT_STATUE, IntType.SCENERY, "touch") { player, _ ->
            setQuestStage(player, "Recruitment Drive", 45)
            player.faceLocation(location(2458, 4980, 0))
            sendNPCDialogue(player, 2283, "Excellent work, ${player.username}. Please step through the portal to meet your next challenge.")
            sendMessage(player, "Congratulations! You have solved this room's puzzle!")
            return@on true
        }

        on(RDUtils.STATUES, IntType.SCENERY, "touch") { player, _ ->
            if (getQuestStage(player,"Recruitment Drive") in 45..100) {
                sendNPCDialogue(player, 2283, "You have solved this room's puzzle! Please step through the portal to meet your next challenge.")
            } else {
                teleport(player, location(2996, 3375, 0))
                sendMessage(player, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.")
                return@on true
            }
            return@on false
        }
    }

    override fun defineDestinationOverrides() {

        setDest(IntType.NPC, intArrayOf(RDUtils.TIFFY), "talk-to") { _, _ ->
            return@setDest Location.create(2997, 3374, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.DOOR_0), "open") { _, _ ->
            return@setDest Location.create(2446, 4956, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.DOOR_1), "open") { _, _ ->
            return@setDest Location.create(2472, 4972, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.DOOR_2), "open") { _, _ ->
            return@setDest Location.create(2447, 4979, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.DOOR_3), "open") { _, _ ->
            return@setDest Location.create(2452, 4943, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.DOOR_4), "open") { _, _ ->
            return@setDest Location.create(2480, 4956, 0)
        }

        setDest(IntType.SCENERY, intArrayOf(RDUtils.STONE_DOORS_2), "Walk-through") { player, _ ->
            if (inBorders(player, 2476, 4941, 2477, 4939)) {
                return@setDest Location.create(2476, 4940, 0)
            } else if (inBorders(player, 2477, 4941, 2478, 4939)) {
                return@setDest Location.create(2478, 4940, 0)
            } else {
                Location.create(2478, 4940, 0)
            }
        }
    }
}