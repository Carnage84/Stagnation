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
 * Burnt book listener.
 */
class BurntBook : InteractionListener {

    // Quest item [Royal Trouble quest].
    // Player find the pages of the book in the remains of fires in the,
    // tunnels beneath Miscellania and Etceteria during the quest.
    // Players put them together to make this book.
    // The item's examine text also seems to change to reflect
    // how many of the five pages the player has found so far.

    companion object {
        private val TITLE = "Armod's Charred Diary"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("Property of Armod Brundtson", 55),
                    BookLine("Read at your peril!",56),
                    BookLine("If you steal this book,",57),
                    BookLine("my dad will beat you up!", 58),
                    BookLine("<col=08088A>Year 3 of trials, day 20",59),
                    BookLine("We're doing really badly", 60),
                    BookLine("at the trials. Beigarth",61),
                    BookLine("broke his lyre for the", 62),
                    BookLine("fifth time, Hild got lost",63),
                    BookLine("in the maze again, and", 64),
                    BookLine("Reinn and Signy were both",65),
                ),
                Page(
                    BookLine("sick all over Manni. When",66),
                    BookLine("are we going to finish", 67),
                    BookLine("these trials and become",68),
                    BookLine("adults? My father keeps",69),
                    BookLine("telling me to improve,", 70),
                    BookLine("saying that I should try", 71),
                    BookLine("harder to become a proper",72),
                    BookLine("Fremennik adult.",73),
                    BookLine("<col=08088A>Year 4 of trials, day 144", 74),
                    BookLine("The trials aren't going",75),
                    BookLine("well. I broke my arm while",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("fighting a cow, and Beigarth",55),
                    BookLine("almost fell into the furnace.", 56),
                    BookLine("Luckily, my father saved", 57),
                    BookLine("him and Signy from spilling", 58),
                    BookLine("molten iron all over the", 59),
                    BookLine("adults. Askeladden's already",60),
                    BookLine("become an adult and keeps",61),
                    BookLine("making fun of us. He really", 62),
                    BookLine("annoys me.", 63),
                    BookLine("<col=08088A>Year 5 of trials, day 78", 64),
                    BookLine("It's not fair! Father said",65),
                ),
                Page(
                    BookLine("that me and my friends",66),
                    BookLine("might never pass our trials.",67),
                    BookLine("They dont want us helping",68),
                    BookLine("other people with their",69),
                    BookLine("trials, either. And now",70),
                    BookLine("he says we have to leave", 71),
                    BookLine("Rellekka! How can they",72),
                    BookLine("exile us?",73),
                    BookLine("<col=08088A>Year 5 of trials, day 89", 74),
                    BookLine("Signy said she heard about",75),
                    BookLine("two island kingdoms that", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("had been at war for hundreds",55),
                    BookLine("of years. If we go there", 56),
                    BookLine("and make peace, will my",57),
                    BookLine("father finally agree that",58),
                    BookLine("we're true Fremennik adults?",59),
                    BookLine("<col=08088A>Year 5 of trials, day 93", 60),
                    BookLine("Life's not fair! We got",61),
                    BookLine("to the island kingdoms",62),
                    BookLine("and they said that an", 63),
                    BookLine("adventurer had recently",64),
                    BookLine("made peace, by promising", 65),
                ),
                Page(
                    BookLine("to marry the son of the",66),
                    BookLine("king! Why does this always",67),
                    BookLine("happen to us? If we'd", 68),
                    BookLine("just been a little earlier,", 69),
                    BookLine("I could have married the", 70),
                    BookLine("king's daughter.",71),
                    BookLine("<col=08088A>Year 5 of trials, day 95", 72),
                    BookLine("Beigarth says that just",73),
                    BookLine("because they're at peace", 74),
                    BookLine("now doesn't mean they", 75),
                    BookLine("always will. He says he",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("has a plan.", 55),
                    BookLine("Beigarth's plan is great!", 56),
                    BookLine("Now all we have to do",57),
                    BookLine("is find the right armour,", 58),
                    BookLine("and a place in one of",59),
                    BookLine("the caves. Father's going", 60),
                    BookLine("to be so impressed with", 61),
                    BookLine("me...",62),
                    BookLine("<col=08088A>Year 5 of trials, day 98",63),
                    BookLine("The plan's worked! The",64),
                    BookLine("kingdoms are at war again,",65),
                ),
                Page(
                    BookLine("and no-one knows we were",66),
                    BookLine("those soldiers. All we",67),
                    BookLine("need to do is make peace,", 68),
                    BookLine("and the council at home", 69),
                    BookLine("will be so impressed they'll",70),
                    BookLine("make us adults right away!",71),
                    BookLine("<col=08088A>Year 5 of trials, day 99",72),
                    BookLine("Why did it go so wrong?", 73),
                    BookLine("We were just about to",74),
                    BookLine("get the goods back, but", 75),
                    BookLine("there are MONSTERS in",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("there! I'm not going anywhere",55),
                    BookLine("near that giant snake,",56),
                    BookLine("and I think the others",57),
                    BookLine("agree with me. We're", 58),
                    BookLine("cold and hungry and can't", 59),
                    BookLine("go back to the town, because",60),
                    BookLine("we got drunk in the inn", 61),
                    BookLine("and then couldn't pay.",62),
                    BookLine("It's so bad that we've",63),
                    BookLine("had to burn bits of my",64),
                    BookLine("diary to keep warm. Someone", 65),
                    BookLine("please help us...",66),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.BURNT_DIARY_7961, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}