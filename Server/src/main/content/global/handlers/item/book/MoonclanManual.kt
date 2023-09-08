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
 * Moonclan Manual handler.
 */
class MoonclanManual : InteractionListener {

    // Player can buy the book for one coin from Baba Yaga on the Lunar Isle.
    // Book tells the history of how the Astral Altar came to exist and some of
    // the history of the origin of magic.
    companion object {
        private val TITLE = "Queen help book"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("<col=08088A>The Basics of Magic</col>", 55),
                    BookLine("A Primer in the Mystical Arts", 56),
                    BookLine("This primer has been", 57),
                    BookLine("collated from years of", 58),
                    BookLine("our research into the", 59),
                    BookLine("metanatural arts commonly", 60),
                    BookLine("known as ‘magic’. All", 61),
                    BookLine("those wishing to set", 62),
                    BookLine("forth upon the Astral", 63),
                    BookLine("Path should read this", 64),
                    BookLine("primer until the concepts", 65),
                ),
                Page(
                    BookLine("within are fully understood", 66),
                    BookLine("so that their potential", 67),
                    BookLine("may be fully realised.", 68),
                    BookLine("<col=08088A>Chapter 1", 68),
                    BookLine("<col=08088A>   Myths & Misconceptions", 68),
                    BookLine("Those familiar with the", 69),
                    BookLine("history of our people", 70),
                    BookLine("will know that the source",71),
                    BookLine("of ‘magic’ has always", 72),
                    BookLine("been a contentious issue. Our", 73),
                    BookLine("Fremennik forefathers", 74),
                    BookLine("believe ‘magic’ to be", 75),
                    BookLine("a possession of the gods,",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("and that our use of it",55),
                    BookLine("risks far more than any", 56),
                    BookLine("benefits it may bring; We",57),
                    BookLine("of course take the more", 58),
                    BookLine("enlightened view that", 59),
                    BookLine("we should use all tools", 60),
                    BookLine("at our disposal for our", 61),
                    BookLine("own betterment. Beyond",62),
                    BookLine("any ethical or moral issues",63),
                    BookLine("surrounding magic however,", 64),
                    BookLine("lies the key question: What",65),
                ),
                Page(
                    BookLine("exactly is magic? The", 66),
                    BookLine("other obvious question",67),
                    BookLine("that presents itself then",68),
                    BookLine("would be: Where exactly", 69),
                    BookLine("does it come from? I",70),
                    BookLine("intend to explain here",71),
                    BookLine("how both of these questions",72),
                    BookLine("are actually the same", 73),
                    BookLine("question phrased differently.' The", 74),
                    BookLine("first major misconception",75),
                    BookLine("relating to magic is that",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("the stones we use for", 55),
                    BookLine("our arts somehow contain",56),
                    BookLine("magic within them. Although",57),
                    BookLine("roughly correct, to believe",58),
                    BookLine("this is how magic works", 59),
                    BookLine("limits your understanding",60),
                    BookLine("of our own potential,", 61),
                    BookLine("and will prevent you ever",62),
                    BookLine("achieving the feats which",63),
                    BookLine("we are all capable of. Rather,",64),
                    BookLine("the 'rune stones' that",65),
                ),
                Page(
                    BookLine("we shape to our purposes",66),
                    BookLine("serve to focus your own", 67),
                    BookLine("power, rather than containing", 68),
                    BookLine("the power themselves. A", 69),
                    BookLine("short history of how the",70),
                    BookLine("runes were first discovered",71),
                    BookLine("will serve to illuminate",72),
                    BookLine("this point to you more",73),
                    BookLine("fully. As we all know,",74),
                    BookLine("from the tales told to",75),
                    BookLine("us in the Secret Tongue,",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("'magic' was first discovered",55),
                    BookLine("in these lands by V-------", 56),
                    BookLine("when he accidentally stumbled", 57),
                    BookLine("upon the Stone of J--. The", 58),
                    BookLine("stone was clearly not", 59),
                    BookLine("of this world, and the",60),
                    BookLine("mere touch of it unlocked",61),
                    BookLine("something within the mind",62),
                    BookLine("of V-------. As we know,",63),
                    BookLine("the stone was instantly", 64),
                    BookLine("removed by those powers", 65),
                ),
                Page(
                    BookLine("who walk a higher Astral",66),
                    BookLine("Path than ourselves, but",67),
                    BookLine("its very existence showed",68),
                    BookLine("V------- possibilities",69),
                    BookLine("that had never occurred", 70),
                    BookLine("to our kind before. His", 71),
                    BookLine("lifelong search for the", 72),
                    BookLine("stone never did reveal",73),
                    BookLine("its eventual fate, and",74),
                    BookLine("the myths suggest that",75),
                    BookLine("the stone may yet still", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("lie somewhere within this",55),
                    BookLine("world, but he did discover", 56),
                    BookLine("something of great importance", 57),
                    BookLine("in the caves where first",58),
                    BookLine("he found the stone; the", 59),
                    BookLine("very rocks that had surrounded",60),
                    BookLine("it had been subtly changed", 61),
                    BookLine("by its presence. The",62),
                    BookLine("rocks in their contact",63),
                    BookLine("with the Stone of J--", 64),
                    BookLine("had gained a yearning", 65),
                ),
                Page(
                    BookLine("to be something they were",66),
                    BookLine("not, and V------- discovered",67),
                    BookLine("that by focussing thought",68),
                    BookLine("upon them he could transform",69),
                    BookLine("them entirely for short", 70),
                    BookLine("periods of time into the",71),
                    BookLine("very elements of the universe. This",72),
                    BookLine("is a mere parlour trick,",73),
                    BookLine("as many of our young have",74),
                    BookLine("done the same thing with",75),
                    BookLine("essence as children, making",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("the cold-burning fire", 55),
                    BookLine("in their hands on summer",56),
                    BookLine("nights, and V------- knew",57),
                    BookLine("that should be able to",58),
                    BookLine("affect a permanent change",59),
                    BookLine("upon these stones, he", 60),
                    BookLine("could have access to the",61),
                    BookLine("very powers of the gods", 62),
                    BookLine("themselves! And so was",63),
                    BookLine("created the Astral Temple. This",64),
                    BookLine("was the first, and to", 65),
                ),
                Page(
                    BookLine("this day we honour those",66),
                    BookLine("who walked before us on", 67),
                    BookLine("the Astral Path, and prevent",68),
                    BookLine("it falling into the ruin",69),
                    BookLine("that this dimension has", 70),
                    BookLine("caused to its counterparts. By",71),
                    BookLine("regular concentrated effort",72),
                    BookLine("of will upon a large dolmen",73),
                    BookLine("of essence, by a number", 74),
                    BookLine("of people simultaneously,",75),
                    BookLine("the dolmen was finally",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("convinced that it had", 55),
                    BookLine("become something which",56),
                    BookLine("it was not. The Astral",57),
                    BookLine("Rune, symbol of our very",58),
                    BookLine("way of life, it is a testament",59),
                    BookLine("to what is possible if",60),
                    BookLine("we seek understanding.",61),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.MOONCLAN_MANUAL_9078, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}