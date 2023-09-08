package content.quest.member.makinghistory.book

import content.global.handlers.iface.BookInterface
import content.global.handlers.iface.BookLine
import content.global.handlers.iface.Page
import content.global.handlers.iface.PageSet
import content.quest.member.makinghistory.MakingHistoryListener
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents "The Mysteroius Adventurer" book for Making History quest.
 */
@Initializable
class MysteroiusAdventurer : InteractionListener {
    companion object {
        private val TITLE = "The Mysteroius Adventurer"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("Word is spreading of a", 55),
                    BookLine("brave and intelligent", 56),
                    BookLine("adventurer who goes by", 57),
                    BookLine("the name 'player', has", 58),
                    BookLine("talked to a great many people,", 59),
                    BookLine("helping solve problems and", 60),
                    BookLine("improving their lives.", 61),
                    BookLine("The list of achievements include:", 62),
                    BookLine("- Confronting the demon", 63),
                    BookLine("Delrith with the sword,", 64),
                    BookLine("Silverlight.", 65),
                ),
                Page(
                    BookLine("- Returning the skull of", 66),
                    BookLine("a ghost so that he may rest in peace.", 67),
                    BookLine("- Collecting ingredients", 68),
                    BookLine("for the chef in Lumbridge", 69),
                    BookLine("to make a cake.", 70),
                    BookLine("- Helping Dr Fenkenstrain", 71),
                    BookLine("create a monster.", 72),
                    BookLine("- Being launched from a", 73),
                    BookLine("cannon wearing a gold helm", 74),
                )
            ),
            PageSet(
                Page(
                    BookLine("just to help an old dwarf.", 55),
                    BookLine("- Meddling with Mourner's", 56),
                    BookLine("soup in West Ardougne.", 57),
                    BookLine("- Shearing many sheep to", 58),
                    BookLine("help Fred the Farmer.", 59),
                    BookLine("- Sabotaging the plans of", 60),
                    BookLine("the black knights with", 61),
                    BookLine("just a cabbage!", 62),
                    BookLine("- Turning a chicken called", 63),
                    BookLine("Ernest into a man.", 64),
                    BookLine("- Participating in the", 65),
                ),
                Page(
                    BookLine("fishing contest at ", 66),
                    BookLine("Hemenster and winning first prize.", 67),
                    BookLine("- Searching for a lost cat", 68),
                    BookLine("and returning its kitten.", 69),
                    BookLine("- Defeating the incredible", 70),
                    BookLine("threat of Nomad.", 71),
                    BookLine("- Helping the Void Knights", 72),
                    BookLine("and deciding the fate of", 73),
                    BookLine("the Wizard Grayzag.", 74),
                    BookLine("- Tracking down and", 75),
                    BookLine("actually touching Guthix's", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("prized stone of power.", 55),
                    BookLine("- Getting initiated into", 56),
                    BookLine("the elitist Legend's Guild.", 57),
                    BookLine("- Fighting incredibly", 58),
                    BookLine("dangerous sea creatures at", 59),
                    BookLine("the Piscatoris Fishing Colony.", 60),
                    BookLine("- Uncovering an ancient", 61),
                    BookLine("magic spellbook in the", 62),
                    BookLine("heart of the desert.", 63),
                    BookLine("- Vanquishing a zombie", 64),
                    BookLine("disease affecting deadly ogres.", 65),
                ),
                Page(
                    BookLine("- Making a beautiful,", 66),
                    BookLine("regal quality garden for", 67),
                    BookLine("the Queen of Varrock.", 68),
                    BookLine("- Charming all of the", 69),
                    BookLine("rats in Port Sarim!", 70),
                    BookLine("- Battling against the", 71),
                    BookLine("fearsome Dagannoth ", 72),
                    BookLine("alongside the Fremennik.", 73),
                    BookLine("The list goes on, so we can", 74),
                    BookLine("see that this player", 75),
                    BookLine("is destined for great things", 76),
                )
            ),
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {

        on(MakingHistoryListener.largeDisplay2, IntType.SCENERY, "study") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}