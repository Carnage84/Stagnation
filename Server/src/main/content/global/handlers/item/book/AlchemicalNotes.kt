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
 * Alchemical Notes listener.
 */
class AlchemicalNotes : InteractionListener {
    companion object {
        private val TITLE = "Alchemical Notes"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("<col=08088A>Acetic acid", 55),
                    BookLine("<col=08088A>and Cupric Sulphate:",56),
                    BookLine("Endothermic.", 57),
                    BookLine("The Cupric is in",58),
                    BookLine("insufficient quantities",59),
                    BookLine("to cause any noticeable",60),
                    BookLine("reaction.", 61),
                    BookLine("<col=08088A>Acetic acid", 63),
                    BookLine("<col=08088A>and Gypsum:",64),
                    BookLine("Made a particularly bad",65),
                ),
                Page(
                    BookLine("smell, but little else", 66),
                    BookLine("that was productive.",67),
                    BookLine("<col=08088A>Acetic acid",69),
                    BookLine("<col=08088A>and Sodium Chloride:",70),
                    BookLine("Very tasty when combined",71),
                    BookLine("with fried potatoes", 72),
                    BookLine("at room temperature.",73),
                    BookLine("Acetic acid",75),
                    BookLine("and Dihydrogen Monoxide:",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("The Dihydrogen Monoxide",55),
                    BookLine("served only to dilute",56),
                    BookLine("the Acetic acid at",57),
                    BookLine("room temperature.",58),
                    BookLine("<col=08088A>Acetic acid",60),
                    BookLine("<col=08088A>and Cupric Ore Powder:", 61),
                    BookLine("The powdered form of",62),
                    BookLine("Cupric Ore allowed a",63),
                    BookLine("lower than usual melting",64),
                    BookLine("temperature, but the",65),
                ),
                Page(
                    BookLine("end product was non-usable.", 66),
                    BookLine("<col=08088A>Acetic acid", 68),
                    BookLine("<col=08088A>and Tin Ore powder:", 69),
                    BookLine("Similar results to", 70),
                    BookLine("those made using Cupric Ore.",71),
                    BookLine("<col=08088A>Cupric Sulphate",73),
                    BookLine("<col=08088A>and Dihyrdogen Monoxide:",74),
                    BookLine("Exothermic.",75),
                    BookLine("A blue compound was", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("produced, along with heat.",55),
                    BookLine("<col=08088A>Cupric Sulphate",57),
                    BookLine("<col=08088A>and Gypsum:",58),
                    BookLine("At room temperature, no",59),
                    BookLine("useful product was created.", 60),
                    BookLine("<col=08088A>Cupric Sulphate",62),
                    BookLine("<col=08088A>and Sodium Chloride:",63),
                    BookLine("A pungent odour was",64),
                    BookLine("released when combined.",65),
                ),
                Page(
                    BookLine("",66),
                    BookLine("<col=08088A>Cupric Sulphate and",67),
                    BookLine("<col=08088A>Cupric Ore powder:",68),
                    BookLine("The Cupric did not", 69),
                    BookLine("react with each other",70),
                    BookLine("at room temperature.",71),
                    BookLine("<col=08088A>Cupric Sulphate", 73),
                    BookLine("<col=08088A>and Tin Ore powder:", 74),
                    BookLine("Similar results to those",75),
                    BookLine("shown with Cupric Ore,", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("despite the increased",55),
                    BookLine("solubility involved", 56),
                    BookLine("with the powdered form.",57),
                    BookLine("<col=08088A>Gypsum",59),
                    BookLine("<col=08088A>and Dihydrogen Monoxide:",60),
                    BookLine("A white liquid compound was", 61),
                    BookLine("formed, that quickly cooled", 62),
                    BookLine("at room temperature to a",63),
                    BookLine("white heat resistant solid",64),
                    BookLine("very similar to plaster.",65),
                ),
                Page(
                    BookLine("Heat was also produced,",66),
                    BookLine("although not in the same",67),
                    BookLine("quantity as Cupric Sulphate", 68),
                    BookLine("with Dihydrogen Monoxide",69),
                    BookLine("<col=08088A>Gypsum", 71),
                    BookLine("<col=08088A>and Sodium Chloride:",72),
                    BookLine("The two did not seem to",73),
                    BookLine("noticably mix together", 74),
                    BookLine("at room temperature.",75),
                )
            ),
            PageSet(
                Page(
                    BookLine("<col=08088A>Gypsum", 55),
                    BookLine("<col=08088A>and Cupric Ore:",56),
                    BookLine("Endothermic",57),
                    BookLine("The gypsum seems quite", 58),
                    BookLine("resistant to most compounds",59),
                    BookLine("at normal room temperature.",60),
                    BookLine("<col=08088A>Gypsum", 62),
                    BookLine("<col=08088A>and Tin Ore:",63),
                    BookLine("Again, very similar results",64),
                    BookLine("as those shown with",65),
                ),
                Page(
                    BookLine("Cupric Ore.",66),
                    BookLine("<col=08088A>Sodium Chloride", 68),
                    BookLine("<col=08088A>and Dihydrogen Monoxide:",69),
                    BookLine("At room temperature,",70),
                    BookLine("the Sodium Chloride",71),
                    BookLine("dissolves quite easily.", 72),
                    BookLine("Dissolution is faster",73),
                    BookLine("at higher temperatures.", 74),
                    BookLine("<col=08088A>Sodium Chloride", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("<col=08088A>and Cupric Ore:",55),
                    BookLine("No visible combination", 56),
                    BookLine("at room temperature.", 57),
                    BookLine("<col=08088A>Sodium Chloride", 59),
                    BookLine("<col=08088A>and Tin Ore:",60),
                    BookLine("Another very similar", 61),
                    BookLine("result as with Cupric Ore.",62),
                    BookLine("<col=08088A>Cupric Ore Powder",64),
                    BookLine("<col=08088A>and Tin Ore Powder:",65),
                ),
                Page(
                    BookLine("When both ores are in",66),
                    BookLine("particulate form, a",67),
                    BookLine("much lower than usual",68),
                    BookLine("bonding temperature can", 69),
                    BookLine("be obtained. When combined",70),
                    BookLine("at a moderate heat",71),
                    BookLine("(my laboratory heating apparatus)",72),
                    BookLine("I was able to form liquid",73),
                    BookLine("Bronze quite easily,", 74),
                    BookLine("which cooled to form a",75),
                    BookLine("standard Bronze Bar at",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("a temperature far lower", 55),
                    BookLine("than that required to",56),
                    BookLine("produce in mass at a",57),
                    BookLine("furnace.",58),
                    BookLine("<col=08088A>Nitrous Monoxide:",60),
                    BookLine("Was not able to perform", 61),
                    BookLine("an experimentation using",62),
                    BookLine("this substance, as the",63),
                    BookLine("gaseous form would always",64),
                    BookLine("escape when the vial", 65),
                    BookLine("was opened.",66),
                )
            ),
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }


    override fun defineListeners() {
        on(Items.ALCHEMICAL_NOTES_5588, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}