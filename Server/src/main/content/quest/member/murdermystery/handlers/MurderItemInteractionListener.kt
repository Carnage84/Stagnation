package content.quest.member.murdermystery.handlers

import content.quest.member.murdermystery.MurderMysteryListener
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Handles Murder Mystery quest interactions with items.
 */
class MurderItemInteractionListener : InteractionListener {
    override fun defineListeners() {

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.PUNGEON_POT
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle a small amount of flour on the strange smelling pot.")
            runTask(player, 1) { sendMessage(player, "The surface isn't shiny enough to take a fingerprint from.") }
            return@onUseWith true
        }

        onUseWith(
            IntType.SCENERY,
            MurderMysteryListener.EMPTY_POT,
            MurderMysteryListener.BARREL_OF_FLOUR
        ) { player, _, _ ->
            sendMessage(player, "A barrel full of finely sifted flour")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.EMPTY_POT)) {
                sendMessage(player, "You take some flour from the barrel.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.POT_WITH_FLOUR)
                    sendMessage(player, "There's still plenty of flour left.")
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.CRIMINAL_DAGGER_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle a small amount of flour on the murder weapon.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.CRIMINAL_DAGGER_0) && removeItem(
                    player,
                    MurderMysteryListener.POT_WITH_FLOUR
                )
            ) {
                sendMessage(player, "The murder weapon is now coated with a thin layer of flour")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.CRIMINAL_DAGGER_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.CRIMINAL_DAGGER_1,
            MurderMysteryListener.FLYPAPER
        ) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the floury dagger.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the murderer's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.UNKNOWN_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_CUP_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on Bob's cup.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_CUP_0) && removeItem(
                    player,
                    MurderMysteryListener.POT_WITH_FLOUR
                )
            ) {
                sendMessage(player, "The cup is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_CUP_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.SILVER_CUP_1, MurderMysteryListener.FLYPAPER) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered cup.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the Bob's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.BOBS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_BOTTLE_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on Carol's bottle.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_BOTTLE_0) && removeItem(
                    player,
                    MurderMysteryListener.POT_WITH_FLOUR
                )
            ) {
                sendMessage(player, "The Bottle is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_BOTTLE_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.SILVER_BOTTLE_1, MurderMysteryListener.FLYPAPER) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered bottle.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the Carol's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.CAROLS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_NECKLACE_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on Anna's necklace.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_NECKLACE_0) && removeItem(
                    player,
                    MurderMysteryListener.POT_WITH_FLOUR
                )
            ) {
                sendMessage(player, "The Necklace is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_NECKLACE_1)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.SILVER_NECKLACE_1,
            MurderMysteryListener.FLYPAPER
        ) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered necklace.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the Anna's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.ANNAS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }


        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_POT_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on Frank's pot.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_POT_0) && removeItem(
                    player,
                    MurderMysteryListener.POT_WITH_FLOUR
                )
            ) {
                sendMessage(player, "The Pot is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_POT_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.SILVER_POT_1, MurderMysteryListener.FLYPAPER) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered pot.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the Frank's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.FRANKS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_BOOK_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on David's book.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_BOOK_0)) {
                sendMessage(player, "The Book is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_BOOK_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.SILVER_BOOK_1, MurderMysteryListener.FLYPAPER) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered book.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                sendMessage(player, "You have a clean impression of the David's finger prints.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.DAVIDS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.POT_WITH_FLOUR,
            MurderMysteryListener.SILVER_NEEDLE_0
        ) { player, _, _ ->
            sendMessage(player, "You sprinkle the flour on Elizabeth's needle.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.SILVER_NEEDLE_0)) {
                sendMessage(player, "The Needle is now coated with a thin layer of flour.")
                runTask(player, 1) {
                    addItem(player, MurderMysteryListener.SILVER_NEEDLE_1)
                    addItem(player, MurderMysteryListener.EMPTY_POT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.SILVER_NEEDLE_1, MurderMysteryListener.FLYPAPER) { player, _, _ ->
            sendMessage(player, "You use the flypaper on the flour covered needle.")
            lock(player, 1)
            if (removeItem(player, MurderMysteryListener.FLYPAPER)) {
                runTask(player, 1) {
                    sendMessage(player, "You have a clean impression of the Elizabeth's finger prints.")
                    addItem(player, MurderMysteryListener.ELIZABETHS_PRINT)
                    setQuestStage(player, "Murder Mystery", 3)
                }
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, MurderMysteryListener.BOBS_PRINT, MurderMysteryListener.UNKNOWN_PRINT) { player, _, _ ->
            if (removeItem(player, MurderMysteryListener.BOBS_PRINT)) {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Bob of the crime. You destroy useless fingerprint."
                )
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.CAROLS_PRINT,
            MurderMysteryListener.UNKNOWN_PRINT
        ) { player, _, _ ->
            if (removeItem(player, MurderMysteryListener.CAROLS_PRINT)) {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Carol of the crime. You destroy useless fingerprint."
                )
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.FRANKS_PRINT,
            MurderMysteryListener.UNKNOWN_PRINT
        ) { player, _, _ ->
            if (removeItem(player, MurderMysteryListener.FRANKS_PRINT)) {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Frank of the crime. You destroy useless fingerprint."
                )
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.ELIZABETHS_PRINT,
            MurderMysteryListener.UNKNOWN_PRINT
        ) { player, _, _ ->
            if (inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_0) && removeItem(
                    player,
                    MurderMysteryListener.ELIZABETHS_PRINT
                ) && removeItem(player, MurderMysteryListener.UNKNOWN_PRINT)
            ) {
                sendMessage(player, "The finger print's are an exact match to Elizabeth.")
                addItem(player, MurderMysteryListener.KILLERS_PRINT)
            } else {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Carol of the crime. You destroy useless fingerprint."
                )
                removeItem(player, MurderMysteryListener.ELIZABETHS_PRINT)
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.ANNAS_PRINT,
            MurderMysteryListener.UNKNOWN_PRINT
        ) { player, _, _ ->
            if (inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_1) && removeItem(
                    player,
                    MurderMysteryListener.ANNAS_PRINT
                ) && removeItem(player, MurderMysteryListener.UNKNOWN_PRINT)
            ) {
                sendMessage(player, "The finger print's are an exact match to Anna's.")
                addItem(player, MurderMysteryListener.KILLERS_PRINT)
            } else {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Bob of the crime. You destroy useless fingerprint."
                )
                removeItem(player, MurderMysteryListener.ANNAS_PRINT)
            }
            return@onUseWith true
        }

        onUseWith(
            IntType.ITEM,
            MurderMysteryListener.DAVIDS_PRINT,
            MurderMysteryListener.UNKNOWN_PRINT
        ) { player, _, _ ->
            if (inInventory(player, MurderMysteryListener.CRIMINAL_THREAD_2) && removeItem(
                    player,
                    MurderMysteryListener.DAVIDS_PRINT
                ) && removeItem(player, MurderMysteryListener.UNKNOWN_PRINT)
            ) {
                sendMessage(player, "The finger print's are an exact match to David.")
                addItem(player, MurderMysteryListener.KILLERS_PRINT)
            } else {
                sendDialogue(
                    player,
                    "They don't seem to be the same. I guess that clears Frank of the crime. You destroy useless fingerprint."
                )
                removeItem(player, MurderMysteryListener.DAVIDS_PRINT)
            }
            return@onUseWith true
        }
    }
}