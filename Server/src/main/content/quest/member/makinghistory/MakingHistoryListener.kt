package content.quest.member.makinghistory

import config.Items
import config.Scenery
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.game.world.map.Location

/**
 * Making History listeners.
 */
class MakingHistoryListener : InteractionListener {
    companion object {
        const val ghostSpeakAmulet = Items.GHOSTSPEAK_AMULET_552
        const val sapphireAmulet = Items.SAPPHIRE_AMULET_1694

        const val droalakScroll = Items.SCROLL_6758
        const val merchantChest = Items.CHEST_6759
        const val enchantedKey = Items.ENCHANTED_KEY_6754

        const val journalBook = Items.JOURNAL_6755
        const val lathasTimeBook = Items.BOOK_6767

        const val jorralLetter = Items.LETTER_6756
        const val kinglathasLetter = Items.LETTER_6757

        const val coins = Items.COINS_995

        const val bookCase = Scenery.BOOKCASE_10273
        const val shieldDisplay = Scenery.SHIELD_DISPLAY_10267
        const val largeDisplay = Scenery.LARGE_DISPLAY_10269
        const val largeDisplay2 = Scenery.LARGE_DISPLAY_10271

        const val droalak = "/save:makinghistory:droalak"
        const val droalakProgress = "/save:makinghistory:droalak-progress"
        const val merchant = "/save:makinghistory:merchant"
        const val merchantProgress = "/save:makinghistory:merchant-progress"
        const val blanin = "/save:makinghistory:blanin"
        const val blaninProgress = "/save:makinghistory:blanin-progress"
        const val jorralLetterAttr = "/save:makinghistory:jorral-letter"
        const val dron = "/save:makinghistory:dron"
    }

    private fun OutpostScroll(player: Player) {
        val outpostScroll =
            arrayOf(
                "<col=8A0808>Timeline of the Ardougne Outpost</col>",
                "",
                "Start of Fifth age: Outpost built",
                "",
                "+ 65 Years: The dreaded Years of Tragedy'",
                "",
                "+ 68 Years: The Great Battle'",
                "",
                "+ 71 Years: Survivors of battle start a new line of",
                "",
                "kings of Ardougne and the Equal Trade Market.",
                "",
            )
        setInterfaceText(player, outpostScroll.joinToString("<br>"), 222, 2)
    }

    override fun defineListeners() {
        on(droalakScroll, IntType.ITEM, "read") { player, _ ->
            openInterface(player, 222).also { OutpostScroll(player) }
            return@on true
        }

        onDig(Location(2440, 3145, 0)) { player ->
            if (inInventory(player, enchantedKey)) {
                sendDialogue(player, "You use the spade and find a chest. Wonder what's inside?")
                setAttribute(player, merchant, 2)
                addItemOrDrop(player, merchantChest)
            }
            return@onDig
        }

        onUseWith(IntType.ITEM, enchantedKey, merchantChest) { player, _, _ ->
            if (removeItem(player, merchantChest)) {
                sendDialogue(player, "You look in the chest and find a journal, and then you throw away the chest.")
                setAttribute(player, merchant, 3)
                addItemOrDrop(player, journalBook)
            }
            return@onUseWith true
        }

        on(bookCase, IntType.SCENERY, "study") { player, _ ->
            if (!inInventory(player, lathasTimeBook)) {
                sendMessage(player, "You search the bookshelves and find a book named 'The Times of Lathas'")
                addItem(player, lathasTimeBook)
            } else {
                sendMessage(player, "You search the bookshelves...")
                sendMessage(player, "You don't find anything interesting.")
            }
            return@on true
        }

        // need source.
        on(shieldDisplay, IntType.SCENERY, "study") { player, _ ->
            sendMessage(player, "A shield worn by Fremennik warriors.")
            return@on true
        }
    }
}

