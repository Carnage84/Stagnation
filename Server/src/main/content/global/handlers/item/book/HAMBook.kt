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
 * H.A.M book listener.
 */
class HAMBook : InteractionListener {

    // The Book of 'h.a.m' is used during the Zogre Flesh Eaters quest.
    // It is found in the wardrobe in Sithik Ints' room. When used
    // on Sithik Ints, he asks if the player has fought for their life
    // against the odd monster or two. In response to the player
    // accusing him of hating monsters and ogres in particular,
    // he challenges the player to back it up with facts.

    companion object {
        private val TITLE = "Book of 'h.a.m'"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("You read this book for",55),
                    BookLine("a while, it seems to",56),
                    BookLine("be some sort of political",57),
                    BookLine("manifesto about how",58),
                    BookLine("the king doesn't do",59),
                    BookLine("enough to safeguard",60),
                    BookLine("the citizens of the",61),
                    BookLine("realm from the monsters",62),
                    BookLine("that still thrive within",63),
                    BookLine("the borders. It sends",64),
                    BookLine("out a rallying to all",65),
                    ),
                Page(
                    BookLine("people who would want",66),
                    BookLine("to stop monsters, to", 67),
                    BookLine("join the HAM movement.", 68),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.BOOK_OF_HAM_4829, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}