package content.quest.member.murdermystery.handlers

import config.Items
import content.quest.member.murdermystery.MurderMysteryListener
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.tools.RandomFunction

/**
 * Handles Murder Mystery quest interactions with scenery.
 */
class MurderSceneryInteractionListener : InteractionListener {
    override fun defineListeners() {

        fun initialSuspects(player: Player) {
            if (player.inventory.containItems(Items.CRIMINALS_THREAD_1808)) {
                getAttribute(player, "/save:murdermystery:elizabeth", false)
            } else if (player.inventory.containItems(Items.CRIMINALS_THREAD_1809)) {
                getAttribute(player, "/save:murdermystery:anna", false)
            } else if (player.inventory.containItems(Items.CRIMINALS_THREAD_1810)) {
                getAttribute(player, "/save:murdermystery:david", false)
            }
        }

        on(MurderMysteryListener.SMASHED_WINDOW, IntType.SCENERY, "Investigate") { player, _ ->
            if (player.questRepository.getStage("Murder Mystery") == 1) {
                val caseNumber = if (RandomFunction.getRandom(3) == 1) {
                    sendDialogue(player, "Some thread seems to have been caught on a loose nail on the window.")
                    1
                } else if (RandomFunction.getRandom(3) == 2) {
                    sendDialogue(player, "Some thread seems to have been caught on a loose nail on the window.")
                    2
                } else {
                    sendDialogue(player, "Some thread seems to have been caught on a loose nail on the window.")
                    3
                }
                when {
                    (caseNumber) == 1 -> {
                        if (!inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_0, 1)) {
                            setAttribute(player, "/save:murdermystery:elizabeth", true)
                            setQuestStage(player, "Murder Mystery", 2)
                            addItem(player, MurderMysteryListener.CRIMINAL_THREAD_0)
                            initialSuspects(player)
                        }
                    }

                    (caseNumber) == 2 -> {
                        if (!inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_1, 1)) {
                            setAttribute(player, "/save:murdermystery:anna", true)
                            setQuestStage(player, "Murder Mystery", 2)
                            addItem(player, MurderMysteryListener.CRIMINAL_THREAD_1)
                            initialSuspects(player)
                        }
                    }

                    else -> {
                        if (!inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_2, 1)) {
                            setAttribute(player, "/save:murdermystery:david", true)
                            setQuestStage(player, "Murder Mystery", 2)
                            addItem(player, MurderMysteryListener.CRIMINAL_THREAD_2)
                            initialSuspects(player)
                        }
                    }
                }
            }
            return@on true
        }

        on(MurderMysteryListener.STURDY_GATE, IntType.SCENERY, "Investigate") { player, _ ->
            sendDialogue(
                player,
                "As you approach the gate the Guard Dog starts barking loudly at you. There is no way an intruder could have committed the murder. It must have been someone the dog knew to get past it quietly."
            )
            findNPC(821)!!.sendChat("BARK")
            return@on true
        }

        on(MurderMysteryListener.ANNA_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_NECKLACE_0, 1)) {
                sendDialogue(player, "There's something shiny hidden at the bottom. You take Anna's silver necklace.")
                addItem(player, MurderMysteryListener.SILVER_NECKLACE_0)
            } else {
                sendMessage(player, "You search the Anna's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.BOB_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_CUP_0, 1)) {
                sendDialogue(player, "There's something shiny hidden at the bottom. You take Bob's silver cup.")
                addItem(player, MurderMysteryListener.SILVER_CUP_0)
            } else {
                sendMessage(player, "You search the Bob's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.CAROL_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_BOTTLE_0, 1)) {
                sendDialogue(player, "There's something shiny hidden at the bottom. You take Carol's silver bottle.")
                addItem(player, MurderMysteryListener.SILVER_BOTTLE_0)
            } else {
                sendMessage(player, "You search the Carol's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.DAVID_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_BOOK_0, 1)) {
                sendDialogue(player, "There's something shiny hidden at the bottom. You take David's silver book.")
                addItem(player, MurderMysteryListener.SILVER_BOOK_0)
            } else {
                sendMessage(player, "You search the David's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.FRANK_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_POT_0, 1)) {
                sendDialogue(player, "There's something shiny hidden at the bottom. You take Frank's silver pot.")
                addItem(player, MurderMysteryListener.SILVER_POT_0)
            } else {
                sendMessage(player, "You search the Frank's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.ELIZABETH_BARREL, IntType.SCENERY, "Search") { player, _ ->
            if (!inInventory(player, MurderMysteryListener.SILVER_NEEDLE_0, 1)) {
                sendDialogue(
                    player,
                    "There's something shiny hidden at the bottom. You take Elizabeth's silver needle."
                )
                addItem(player, MurderMysteryListener.SILVER_NEEDLE_0)
            } else {
                sendMessage(player, "You search the Elizabeth's barrel but find nothing.")
            }
            return@on true
        }

        on(MurderMysteryListener.SMASHED_WINDOW, IntType.SCENERY, "Break") { player, _ ->
            sendMessage(player, "You don't want to damage evidence!")
            return@on true
        }

        // Elizabeth
        on(MurderMysteryListener.FAMILY_FOUNTAIN, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:elizabeth", false)) {
                sendDialogue(
                    player,
                    "The fountain is swarming with mosquito's. There's a nest of them underneath the fountain."
                )
            } else if (player.questRepository.getStage("Murder Mystery") == 1) {
                sendDialogue(
                    player,
                    "There are a lot of dead mosquito's around the base of the fountain. A faint smell of poison is in the air, but the water seems clean."
                )
            } else {
                sendMessage(player, "A fountain with large numbers of insects around the base.")
            }
            return@on true
        }

        // Carol
        on(MurderMysteryListener.MANSION_DRAIN, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:elizabeth", false)) {
                sendMessage(player, "It's the drains from the kitchen.")
            } else if (player.questRepository.getStage("Murder Mystery") == 1) {
                sendDialogue(
                    player,
                    "The drain seems to have been recently cleaned. You can still smell the faint aroma of poison."
                )
            }
            return@on true
        }

        // Anna
        on(MurderMysteryListener.COMPOST_HEAP, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:anna", false)) {
                sendDialogue(
                    player,
                    "The compost is teeming with maggots. Somebody should really do something about it. It's certainly clear nobody's used poison here."
                )
            } else if (player.questRepository.getStage("Murder Mystery") == 1) {
                sendDialogue(player, "There is a faint smell of poison behind the smell of the compost.")
            } else {
                sendMessage(player, "It's a heap of compost.")
            }
            return@on true
        }

        // Bob
        on(MurderMysteryListener.BEEHIVE_LIMO, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:anna", false)) {
                sendMessage(player, "It's a very old beehive.")
            } else {
                sendDialogue(player, "The hive is empty. There are a few dead bees and a faint smell of poison.")
            }
            return@on true
        }

        // David
        on(MurderMysteryListener.SPIDER_NEST, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:david", false)) {
                sendDialogue(
                    player,
                    "There is a spider's nest here. There must be at least a few hundred spiders ready to hatch. It's certainly clear nobody's used poison here."
                )
            } else if (player.questRepository.getStage("Murder Mystery") == 1) {
                sendDialogue(player, "There is a faint smell of poison behind the smell of the compost.")
            } else {
                sendMessage(player, "It looks like a spiders' nest of some kind...")
            }
            return@on true
        }

        // Frank
        on(MurderMysteryListener.FAMILY_CREST, IntType.SCENERY, "Investigate") { player, _ ->
            if (getAttribute(player, "/save:murdermystery:david", false)) {
                sendMessage(player, "The Sinclair Family Crest is hung up here.")
            } else if (player.questRepository.getStage("Murder Mystery") == 1) {
                sendDialogue(player, "That it is still dirty, and there is no evidence of cleaning here.")
            } else {
                sendDialogue(
                    player,
                    "The Sinclair family crest. It's shiny and freshly polished and has a slight smell of poison."
                )
            }
            return@on true
        }

        onUseWith(
            IntType.SCENERY,
            MurderMysteryListener.SILVER_POT_0,
            MurderMysteryListener.BARREL_OF_FLOUR
        ) { player, _, _ ->
            sendMessage(player, "You probably shouldn't use evidence from a crime scene to keep flour in...")
            return@onUseWith false
        }

        onUseWith(
            IntType.SCENERY,
            MurderMysteryListener.SILVER_POT_1,
            MurderMysteryListener.BARREL_OF_FLOUR
        ) { player, _, _ ->
            sendMessage(player, "You probably shouldn't use evidence from a crime scene to keep flour in...")
            return@onUseWith false
        }

        onUseWith(
            IntType.SCENERY,
            MurderMysteryListener.PUNGEON_POT,
            MurderMysteryListener.BARREL_OF_FLOUR
        ) { player, _, _ ->
            sendMessage(player, "You probably shouldn't use evidence from a crime scene to keep flour in...")
            return@onUseWith false
        }
    }
}