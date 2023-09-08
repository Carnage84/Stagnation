package content.region.island.mosleharmless.handlers

import config.Components
import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.world.map.Location

/**
 * Listeners for interaction in Mos Le'Harmless region.
 */
class MosLeHarmlessListeners : InteractionListener {

    val SeparateItems = intArrayOf(Items.BERET_AND_MASK_11282)

    val mosLeHarmlessShopOwners =
        intArrayOf(
            NPCs.MIKE_3166,
            NPCs.CHARLEY_3161,
            NPCs.MAMA_3164,
            NPCs.JOE_3163,
            NPCs.HONEST_JIMMY_4362
        )

    override fun defineListeners() {
        on(NPCs.PATCHY_4359, IntType.NPC, "sew") { player, _ ->
            if (!anyInInventory(player, 7112, 7124, 7130, 7136, 2635,2651, 1025, 10774, 995, 13355, 13353)) {
                sendMessage(player, "You don't have the items for this.")
            } else {
                openInterface(player, Components.SEW_INTERFACE_419)
            }
            return@on true
        }

        onUseWith(IntType.ITEM, SeparateItems, NPCs.PATCHY_4359) { player, used, with ->
            if (used.id != Items.BERET_AND_MASK_11282) {
                sendNPCDialogue(player, NPCs.PATCHY_4359, "Sorry, I can't do anythin' with that.")
            } else {
                openDialogue(player, SeparateItemsDialogue())
            }
            return@onUseWith true
        }

        on(mosLeHarmlessShopOwners, IntType.NPC, "trade") { player, node ->
            val hasBook = inInventory(player, Items.BOOK_O_PIRACY_7144)
            when (node.id) {
                3161 -> {
                    if (hasBook) {
                        openNpcShop(player, NPCs.CHARLEY_3161)
                    } else {
                        player.dialogueInterpreter.open(NPCs.CHARLEY_3161, node)
                    }
                }

                3163 -> {
                    if (hasBook) {
                        openNpcShop(player, NPCs.JOE_3163)
                    } else {
                        player.dialogueInterpreter.open(NPCs.JOE_3163, node)
                    }
                }

                3164 -> {
                    if (hasBook) {
                        openNpcShop(player, NPCs.MAMA_3164)
                    } else {
                        player.dialogueInterpreter.open(NPCs.MAMA_3164, node)
                    }
                }

                3166 -> {
                    if (hasBook) {
                        openNpcShop(player, NPCs.MIKE_3166)
                    } else {
                        player.dialogueInterpreter.open(NPCs.MIKE_3166, node)
                    }
                }

                4362 -> {
                    if (hasBook) {
                        openNpcShop(player, NPCs.HONEST_JIMMY_4362)
                    } else {
                        player.dialogueInterpreter.open(NPCs.HONEST_JIMMY_4362, node)
                    }
                }
            }
            return@on true
        }
    }

    override fun defineDestinationOverrides() {

        setDest(IntType.NPC, intArrayOf(NPCs.MIKE_3166), "talk-to", "trade") { _, _ ->
            return@setDest Location.create(3693, 2975, 0)
        }

        setDest(IntType.NPC, intArrayOf(NPCs.CHARLEY_3161), "talk-to", "trade") { _, _ ->
            return@setDest Location.create(3674, 2968, 0)
        }

        setDest(IntType.NPC, intArrayOf(NPCs.MAMA_3164), "talk-to", "trade") { player, _ ->
            if (player.location.y < 76) {
                return@setDest Location.create(3665, 2980, 0).transform(0, -2, 0)
            } else {
                return@setDest Location.create(3665, 2980, 0).transform(0, 2, 0)
            }
        }

        setDest(IntType.NPC, intArrayOf(NPCs.JOE_3163), "talk-to", "trade") { player, _ ->
            if (inBorders(player, 3666, 2990, 3670, 2997)) {
                return@setDest Location.create(3667, 2992, 0)
            } else {
                return@setDest Location.create(3665, 2992, 0)
            }
        }
    }

    class SeparateItemsDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            npc = NPC(NPCs.PATCHY_4359)
            when (stage) {
                1 -> if (removeItem(player!!, Items.BERET_AND_MASK_11282)) {
                    addItemOrDrop(player!!, Items.BLACK_BERET_10694)
                    addItemOrDrop(player!!, Items.MIME_MASK_3057)
                } else if (freeSlots(player!!) < 2) {
                    npc("YYe don't seem te have enough free space few the two items.", "Ye might want te visit the bank.")
                } else {
                    npc("Sorry, I can't do anythin' with that.")
                }
            }
        }
    }
}
