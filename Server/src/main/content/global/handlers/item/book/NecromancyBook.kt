package content.global.handlers.item.book

import config.Items
import content.global.handlers.iface.BookInterface
import content.global.handlers.iface.BookLine
import content.global.handlers.iface.Page
import content.global.handlers.iface.PageSet
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player

/**
 * Necromancy book listener.
 */
class NecromancyBook : InteractionListener {

    // The necromancy book is used during the
    // Zogre Flesh Eaters quest. It is found in
    // the room of Sithik Ints, in the cupboards.
    // Using the Torn page on the book reveals
    // that it comes from this book.

    companion object {
        private val TITLE = "necromancy book"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("This book uses very",55),
                    BookLine("strange language and",56),
                    BookLine("some incomprehensible",57),
                    BookLine("symbols. It has a very",58),
                    BookLine("dark and evil feeling",59),
                    BookLine("to it. As you're looking",60),
                    BookLine("through the book, you",61),
                    BookLine("notice that one of the",62),
                    BookLine("pages has been torn and",63),
                    BookLine("half of it is missing.",64),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.NECROMANCY_BOOK_4837, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}