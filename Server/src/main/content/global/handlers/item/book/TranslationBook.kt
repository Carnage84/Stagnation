package content.global.handlers.item.book

import config.Items
import content.global.handlers.iface.BookInterface
import content.global.handlers.iface.BookLine
import content.global.handlers.iface.Page
import content.global.handlers.iface.PageSet
import core.api.setAttribute
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player

/**
 * Translation Book handler for The Grand Tree quest.
 */
class TranslationBook : InteractionListener {
    companion object {
        private val TITLE = "Translation Book"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("Gnome English translation", 55),
                    BookLine("", 56),
                    BookLine("written by Anita", 57),
                    BookLine("", 58),
                    BookLine("This text contains the ancient", 59),
                    BookLine("Gnome words I have", 60),
                    BookLine("managed to translate thus", 61),
                    BookLine("far.", 62),
                    BookLine("", 63),
                ),
                Page(
                    BookLine("-A-", 66),
                    BookLine("arpos: rocks", 67),
                    BookLine("ando: gate", 68),
                    BookLine("andra: city", 69),
                    BookLine("ataris: cow", 70),
                    BookLine("", 71),
                    BookLine("-C-", 72),
                    BookLine("cef: threat", 73),
                    BookLine("cheray: lazy", 74),
                    BookLine("Cinqo: King", 75),
                    BookLine("cretor: bucket", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("-E-", 55),
                    BookLine("eis: me", 56),
                    BookLine("es: a", 57),
                    BookLine("et: and", 58),
                    BookLine("eto: will", 59),
                    BookLine("", 60),
                    BookLine("-G-", 61),
                    BookLine("gandius: jungle", 62),
                    BookLine("Gal: All", 63),
                    BookLine("gentis: leaf", 64),
                    BookLine("gutus: banana", 65),
                    BookLine("gomondo: branch", 66),
                ),
                Page(
                    BookLine("-H-", 66),
                    BookLine("har: old", 67),
                    BookLine("harij: harpoon", 68),
                    BookLine("hewo: grass", 69),
                    BookLine("", 70),
                    BookLine("-I-", 71),
                    BookLine("ip: you", 72),
                    BookLine("imindus: quest", 73),
                    BookLine("irno: translate", 74),
                )
            ),
            PageSet(
                Page(
                    BookLine("-K-", 55),
                    BookLine("kar: no", 56),
                    BookLine("kai: boat", 57),
                    BookLine("ko: sail", 58),
                    BookLine("", 59),
                    BookLine("-L-", 60),
                    BookLine("lauf: eye", 61),
                    BookLine("laquinay: common sense", 62),
                    BookLine("lemanto: man", 63),
                    BookLine("lemantolly: stupid man", 64),
                    BookLine("lovos: gave", 65),
                ),
                Page(
                    BookLine("-M-", 66),
                    BookLine("meriz: kill", 67),
                    BookLine("mina: time(s)", 68),
                    BookLine("mos: coin", 69),
                    BookLine("mi: I", 70),
                    BookLine("mond: seal", 71),
                    BookLine("", 72),
                    BookLine("-P-", 73),
                    BookLine("por: long", 74),
                    BookLine("prit: with", 75),
                    BookLine("priw: tree", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("pro: to", 55),
                    BookLine("", 56),
                    BookLine("-Q-", 57),
                    BookLine("Qui: guard", 58),
                    BookLine("Quir: guardian", 59),
                    BookLine("", 60),
                    BookLine("-R-", 61),
                    BookLine("rentos: agility", 62),
                    BookLine("", 63),
                    BookLine("-S-", 64),
                    BookLine("sarko: Begone", 65),
                    BookLine("sind: big", 66),
                ),
                Page(
                    BookLine("", 67),
                    BookLine("-T-", 68),
                    BookLine("ta: the", 69),
                    BookLine("tuzo: open", 70),
                    BookLine("", 71),
                    BookLine("-U-", 72),
                    BookLine("Undri: lands", 73),
                    BookLine("Umesco: Soul", 74),
                )
            ),
        )

        private fun display(player:Player, pageNum: Int, buttonID: Int) : Boolean {
            BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
            return true
        }
    }

    override fun defineListeners() {
        on(Items.TRANSLATION_BOOK_784, IntType.ITEM, "read") { player, _ ->
            setAttribute(player, "bookInterfaceCallback", Companion::display)
            setAttribute(player, "bookInterfaceCurrentPage", 0)
            display(player, 0, 0)
            return@on true
        }
    }
}