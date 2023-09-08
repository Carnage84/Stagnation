package content.quest.member.recruitmentdrive.npc

import config.Items
import config.NPCs
import config.Sounds
import content.quest.member.recruitmentdrive.util.RDUtils
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.impl.ForceMovement
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.tools.END_DIALOGUE

/**
 * Represents the interaction listener for Miss Cheevers stage in Recruitment Drive quest.
 */
class MissCheeversNPC : InteractionListener {
    override fun defineListeners() {
        class ShelfDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 3)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 2)) {
                        sendDialogue(player!!, "There is one vial on this shelf.").also { stage = 3 }
                    } else if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 1)) {
                        sendDialogue(player!!, "There are two vials on this shelf.").also { stage = 2 }
                    } else {
                        sendDialogue(player!!, "There are two vials on this shelf.")
                    }.also { stage = 1 }
                    1 -> options(
                        "Take one vial",
                        "Take two vials.",
                        "Take all three vials.",
                        "Don't take a vial."
                    ).also { stage = 5 }
                    2 -> sendDialogueOptions(player!!, "Take the vials?", "YES", "NO").also { stage = 6 }
                    6 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 2)
                            end()
                        }
                        2 -> end()
                    }
                    3 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 4 }
                    4 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.CUPRIC_SULPHATE)
                            end()
                        }
                        2 -> end()
                    }
                    5 -> when (buttonID) {
                        1 -> if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 1)) {
                            sendMessage(player!!, "You don't find anything interesting.")
                            end()
                        } else {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID)
                            end()
                        }
                        2 -> if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 2)) {
                            sendMessage(player!!, "You don't find anything interesting.")
                            end()
                        } else if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 2)) {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 1)
                            end()
                        } else {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 2)
                            end()
                        }
                        3 -> if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 3)) {
                            sendMessage(player!!, "You don't find anything interesting.")
                            end()
                        } else if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 3)) {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 2)
                            end()
                        } else if (player!!.inventory.contains(RDUtils.VIAL_OF_LIQUID, 1)) {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 2)
                            end()
                        } else {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 3)
                            end()
                        }
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_0, IntType.SCENERY, "search") { player, _ ->
            openDialogue(player, ShelfDialogue())
            return@on true
        }

        on(RDUtils.OLDBOOKSHELF_0, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            if (inInventory(player, RDUtils.MAGNET, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else {
                addItem(player, RDUtils.MAGNET)
                sendMessage(player, "Hidden amongst the books you find a magnet.")
            }
            return@on true
        }

        on(RDUtils.OLDBOOKSHELF_1, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            if (inInventory(player, Items.ALCHEMICAL_NOTES_5588, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else if (player.questRepository.getStage("Recruitment Drive") == 70) {
                sendMessage(player, "You find a book that looks like it might be helpful.")
                addItem(player, Items.ALCHEMICAL_NOTES_5588)
            } else {
                sendMessage(player, "You don't find anything interesting.")
            }
            return@on true
        }

        class Shelf2Dialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.ACETIC_ACID, 1) && (player!!.inventory.contains(
                            RDUtils.VIAL_OF_LIQUID,
                            1
                        ))
                    ) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else if (player!!.inventory.contains(RDUtils.ACETIC_ACID, 1) || (player!!.inventory.contains(
                            RDUtils.VIAL_OF_LIQUID, 1
                        ))
                    ) {
                        sendDialogue(player!!, "There is one vial on this shelf.").also { stage = 2 }
                    } else {
                        sendDialogue(player!!, "There are two vials on this shelf.").also { stage = 1 }
                    }
                    1 -> options("Take the first vial", "Take the second vial.", "Take both vials.").also { stage = 3 }
                    2 -> if (player!!.inventory.contains(RDUtils.ACETIC_ACID, 1)) {
                        addItem(player!!, RDUtils.VIAL_OF_LIQUID)
                        end()
                    } else {
                        addItem(player!!, RDUtils.ACETIC_ACID)
                        end()
                    }
                    3 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.ACETIC_ACID)
                            end()
                        }
                        2 -> {
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 1)
                            end()
                        }
                        3 -> {
                            addItem(player!!, RDUtils.ACETIC_ACID, 1)
                            addItem(player!!, RDUtils.VIAL_OF_LIQUID, 1)
                            end()
                        }
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_2, IntType.SCENERY, "search") { player, _ ->
            openDialogue(player, Shelf2Dialogue())
            return@on true
        }

        class CurpicSulphateDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.CUPRIC_SULPHATE, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.CUPRIC_SULPHATE)
                            end()
                        }

                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_3, IntType.SCENERY, "search") { player, _ ->
            openDialogue(player, CurpicSulphateDialogue())
            return@on true
        }

        class GypsumDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.GYPSUM, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.GYPSUM)
                            end()
                        }
                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_4, IntType.SCENERY, "search") { player, _ ->
            openDialogue(player, GypsumDialogue())
            return@on true
        }

        class SodiumChlorideDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.SODIUM_CHLORIDE, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.SODIUM_CHLORIDE)
                            end()
                        }

                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_5, IntType.SCENERY, "search") { player, _ ->
            openDialogue(player, SodiumChlorideDialogue())
            return@on true
        }

        on(RDUtils.CLOSED_CHEST, IntType.SCENERY, "open") { _, node ->
            replaceScenery(node.asScenery(), RDUtils.OPEN_CHEST, -1, node.location)
            return@on true
        }

        on(RDUtils.OPEN_CHEST, IntType.SCENERY, "close") { _, node ->
            replaceScenery(node.asScenery(), RDUtils.CLOSED_CHEST, -1, node.location)
            return@on true
        }

        on(RDUtils.OPEN_CHEST, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "you search the chest...")
            if (inInventory(player, RDUtils.SHEARS, 1)) {
                sendMessage(player, "you search the chest but find nothing.")
            } else {
                sendMessage(player, "Inside the chest you find some shears.")
                addItem(player, RDUtils.SHEARS)
            }
            return@on true
        }

        on(RDUtils.CRATE, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the create...")
            if (inInventory(player, RDUtils.BRONZE_WIRE, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else {
                sendMessage(player, "Inside the crate you find some wire.")
                addItem(player, RDUtils.BRONZE_WIRE)
            }
            return@on true
        }

        on(RDUtils.BIG_CRATE, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the create...")
            if (inInventory(player, RDUtils.EMPTY_TIN, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else {
                sendMessage(player, "Inside the crate you find a tin.")
                addItem(player, RDUtils.EMPTY_TIN)
            }
            return@on true
        }

        on(RDUtils.BIGGER_CRATE, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the create...")
            if (inInventory(player, RDUtils.CHISEL, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else {
                sendMessage(player, "Inside the crate you find a chisel.")
                addItem(player, RDUtils.CHISEL)
            }
            return@on true
        }

        class NitrousOxideDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.NITROUS_OXIDE, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.NITROUS_OXIDE)
                            end()
                        }
                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_6, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            openDialogue(player, NitrousOxideDialogue())
            return@on true
        }

        class TinOrePowderDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.TIN_ORE_POWDER, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.TIN_ORE_POWDER)
                            end()
                        }
                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_7, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            openDialogue(player, TinOrePowderDialogue())
            return@on true
        }

        class CupricOrePowderDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if (player!!.inventory.contains(RDUtils.CUPRIC_ORE_POWDER, 1)) {
                        sendMessage(player!!, "You don't find anything interesting.").also { END_DIALOGUE }
                    } else {
                        sendDialogue(player!!, "There is a vial on this shelf.").also { stage = 1 }
                    }
                    1 -> sendDialogueOptions(player!!, "Take the vial?", "YES", "NO").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> {
                            addItem(player!!, RDUtils.CUPRIC_ORE_POWDER)
                            end()
                        }
                        2 -> end()
                    }
                }
            }
        }

        on(RDUtils.BOOKSHELF_8, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            openDialogue(player, CupricOrePowderDialogue())
            return@on true
        }

        on(RDUtils.OLDBOOKSHELF_2, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            if (inInventory(player, RDUtils.RD_KNIFE, 1)) {
                sendMessage(player, "You don't find anything interesting.")
            } else {
                addItem(player, RDUtils.RD_KNIFE)
                sendMessage(player, "Hidden amongst this books you find a knife.")
            }
            return@on true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.METAL_SPADE,
            RDUtils.BUNSEN_BURNER
        ) { player, _, _ ->
            sendMessage(player, "You burn the wooden handle away from the spade...")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.graphics(RDUtils.USING_BURNER_GFX)
                        1 -> removeItem(player, RDUtils.METAL_SPADE)
                        2 -> addItem(player, RDUtils.METAL_SPADE_BROKEN).also { addItem(player, Items.ASHES_592) }
                        3 -> sendMessage(player, "...and are left with a metal spade with no handle.")
                        4 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            playAudio(player,Sounds.FIREWAVE_HIT_163)
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.METAL_SPADE_BROKEN,
            RDUtils.STONE_DOORS_0
        ) { player, used, with ->
            sendMessage(player, "You slide the spade into the hole in the stone...")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> removeItem(player, RDUtils.METAL_SPADE_BROKEN)
                        1 -> replaceScenery(RDUtils.STONE_DOORS_3!!, RDUtils.STONE_DOORS_1, 900)
                        2 -> sendMessage(player, "It's nearly a perfect fit!")
                        3 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.CUPRIC_SULPHATE,
            RDUtils.STONE_DOORS_1
        ) { player, _, _ ->
            if (player.questRepository.getStage("Recruitment Drive") == 70) {
                sendMessage(player, "You pour the vial onto the flat part of the spade.")
                setQuestStage(player, "Recruitment Drive", 71)
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.animate(RDUtils.POUR_VIAL)
                            1 -> removeItem(player, RDUtils.CUPRIC_SULPHATE)
                            2 -> addItem(player, Items.VIAL_229)
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.SODIUM_CHLORIDE,
            RDUtils.STONE_DOORS_1
        ) { player, _, _ ->
            if (player.questRepository.getStage("Recruitment Drive") == 71) {
                sendMessage(player, "You pour the vial onto the flat part of the spade.")
                setQuestStage(player, "Recruitment Drive", 72)
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.animate(RDUtils.POUR_VIAL)
                            1 -> removeItem(player, RDUtils.SODIUM_CHLORIDE)
                            2 -> addItem(player, Items.VIAL_229)
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.ACETIC_ACID,
            RDUtils.STONE_DOORS_1
        ) { player, _, _ ->
            if (player.questRepository.getStage("Recruitment Drive") == 72) {
                sendMessage(player, "You pour the vial onto the flat part of the spade.")
                setQuestStage(player, "Recruitment Drive", 73)
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.animate(RDUtils.POUR_VIAL)
                            1 -> removeItem(player, RDUtils.ACETIC_ACID)
                            2 -> addItem(player, Items.VIAL_229)
                            3 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.VIAL_OF_LIQUID,
            RDUtils.STONE_DOORS_1
        ) { player, _, _ ->
            if (player.questRepository.getStage("Recruitment Drive") == 73) {
                sendMessage(player, "You pour the vial onto the flat part of the spade.")
                setQuestStage(player, "Recruitment Drive", 74)
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> player.animate(RDUtils.POUR_VIAL)
                            1 -> removeItem(player, RDUtils.VIAL_OF_LIQUID).also { addItem(player, Items.VIAL_229) }
                            2 -> sendMessage(player, "You pour the vial onto the flat part of the spade.")
                            3 -> sendMessage(player, "Something caused a reaction when mixed!")
                            4 -> sendMessage(player, "The spade gets hotter, and expands slightly.")
                            5 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }

        on(RDUtils.STONE_DOORS_0, IntType.SCENERY, "Study") { player, node ->
            sendMessage(
                player,
                "There is a stone slab here obstructing the door. There is a small hole in the slab that looks like it might be for a handle."
            )
            return@on false
        }

        on(RDUtils.STONE_DOORS_1, IntType.SCENERY, "Pull-spade") { player, node ->
            if (player.questRepository.getStage("Recruitment Drive") >= 74) {
                sendMessage(player, "You pull on the spade...")
                sendMessage(player, "It works as a handle, and you swing the stone door open.")
                replaceScenery(node.asScenery(), 7345, 900)
                DoorActionHandler.handleDoor(player, node.asScenery())
            } else {
                sendMessage(player, "You pull on the spade...")
                sendMessage(player, "Nothing interesting happens.")

            }
            return@on true
        }

        on(RDUtils.STONE_DOORS_2, IntType.SCENERY, "Walk-through") { player, _ ->
            if (inBorders(player, 2476, 4941, 2477, 4939)) {
                ForceMovement.run(player, player.location, Location.create(2478, 4940, 0))
            } else if (inBorders(player, 2477, 4941, 2478, 4939)) {
                ForceMovement.run(player, player.location, Location.create(2476, 4940, 0))
            }
            return@on true
        }

        onUseWith(IntType.ITEM, RDUtils.GYPSUM, RDUtils.EMPTY_TIN) { player, _, _ ->
            sendMessage(player, "You empty the vial into the tin.")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.animate(RDUtils.ADD_INGREDIENTS)
                        1 -> removeItem(player, RDUtils.GYPSUM).also { addItem(player, Items.VIAL_229) }
                        2 -> removeItem(player, RDUtils.EMPTY_TIN).also { addItem(player, Items.TIN_5593) }
                        3 -> sendMessage(player, "You notice the tin gets quite warm as you do this.")
                        4 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, RDUtils.VIAL_OF_LIQUID, Items.TIN_5593) { player, _, _ ->
            sendMessage(player, "You empty the vial into the tin.")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.animate(RDUtils.ADD_INGREDIENTS)
                        1 -> removeItem(player, RDUtils.VIAL_OF_LIQUID).also { addItem(player, Items.VIAL_229) }
                        2 -> removeItem(player, Items.TIN_5593).also { addItem(player, Items.TIN_5594) }
                        3 -> sendMessage(player, "A lumpy white mixture is made, that seems to be hardening.")
                        4 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.SCENERY, Items.TIN_5594, RDUtils.KEY_CHAIN) { player, _, _ ->
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> removeItem(player, Items.TIN_5594).also { addItem(player, Items.TIN_5595) }
                        1 -> sendMessage(player, "You make an impression of the key as the white mixture hardens.")
                        2 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, RDUtils.TIN_ORE_POWDER, Items.TIN_5595) { player, _, _ ->
            sendMessage(player, "You pour the vial into the impression of the key.")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.animate(RDUtils.ADD_INGREDIENTS)
                        1 -> removeItem(player, RDUtils.TIN_ORE_POWDER).also { addItem(player, Items.VIAL_229) }
                        2 -> removeItem(player, Items.TIN_5595).also { addItem(player, Items.TIN_5596) }
                        3 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, RDUtils.CUPRIC_ORE_POWDER, Items.TIN_5596) { player, _, _ ->
            sendMessage(player, "You pour the vial into the impression of the key.")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.animate(RDUtils.ADD_INGREDIENTS)
                        1 -> removeItem(player, RDUtils.CUPRIC_ORE_POWDER).also { addItem(player, Items.VIAL_229) }
                        2 -> removeItem(player, Items.TIN_5596).also { addItem(player, Items.TIN_5597) }
                        3 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.SCENERY, Items.TIN_5597, RDUtils.BUNSEN_BURNER) { player, _, _ ->
            sendMessage(player, "You heat two powered ores together in the tin.")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> removeItem(player, Items.TIN_5597)
                        1 -> addItem(player, Items.TIN_5598)
                        2 -> sendMessage(player, "You make a duplicate of the key in bronze.")
                        3 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, RDUtils.BRONZE_WIRE, Items.TIN_5598) { player, _, _ ->
            if (player.inventory.containsAtLeastOneItem(Items.TIN_5598)) {
                sendMessage(player, "You heat two powered ores together in the tin.")
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> removeItem(player, Items.TIN_5598)
                            1 -> addItem(player, RDUtils.BRONZE_KEY).also { addItem(player, Items.TIN_5600) }
                            2 -> sendMessage(player, "You prise the duplicate key out of the tin.")
                            3 -> setQuestStage(player, "Recruitment Drive", 75)
                            4 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                teleport(player, Location.create(2996, 3375, 0))
                sendNPCDialogue(
                    player,
                    RDUtils.CHEEVERS,
                    "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future."
                )
            }
            return@onUseWith true
        }

        on(RDUtils.NITROUS_OXIDE, IntType.ITEM, "open") { player, _ ->
            sendMessage(player, "You uncork the vial...")
            player.lock()
            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> removeItem(player, RDUtils.NITROUS_OXIDE)
                        1 -> addItem(player, Items.VIAL_229)
                        2 -> sendMessage(player, "You smell a strange gas as it escapes from inside the vial.")
                        3 -> sendChat(player, "Hahahahahahaha!")
                        4 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
            return@on true
        }

        on(RDUtils.FINAL_DOOR, IntType.SCENERY, "open") { player, _ ->
            return@on false
        }

        onUseWith(
            IntType.SCENERY,
            RDUtils.BRONZE_KEY,
            RDUtils.FINAL_DOOR
        ) { player, _, _ ->
            if (player.questRepository.getStage("Recruitment Drive") >= 75) {
                player.lock()
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> removeItem(player, RDUtils.BRONZE_KEY)
                            1 -> DoorActionHandler.handleAutowalkDoor(player, getScenery(2478, 4940, 0))
                            3 -> sendMessage(player, "You use the duplicate key you made to unlock the door.")
                            4 -> player.inventory.clear()
                            5 -> teleport(player, Location.create(2451, 4935, 0))
                            6 -> ForceMovement.run(player, player.location, Location.create(2451, 4936, 0))
                            7 -> sendNPCDialogue(
                                player,
                                NPCs.MS_HYNN_TERPRETT_2289,
                                "Greetings, ${player.username}. I am here to test your wits with a simple riddle."
                            )
                            9 -> player.unlock().also { return true }
                        }
                        return false
                    }
                })
            } else {
                sendNPCDialogue(
                    player,
                    RDUtils.CHEEVERS,
                    "You fail a test, I have no choice but to return you to Falador and start over"
                )
                runTask(player, 3) {
                    teleport(player, Location.create(2996, 3375, 0))
                    sendMessage(player, "You have failed to solve the riddle.")
                }
            }
            return@onUseWith true
        }

        on(RDUtils.OLDBOOKSHELF_3, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player, "You search the bookshelves...")
            sendMessage(player, "You don't find anything interesting.")
            return@on true
        }
    }
}