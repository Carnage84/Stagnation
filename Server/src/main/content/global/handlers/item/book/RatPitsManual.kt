package content.global.handlers.item.book

import content.global.handlers.iface.BookInterface
import content.global.handlers.iface.BookLine
import content.global.handlers.iface.Page
import content.global.handlers.iface.PageSet
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player

/**
 * Rat Pits Manual handler for 'Rat Catchers' quest.
 */
class RatPitsManual : InteractionListener {

    // Obtainable as part of the
    // Rat Catchers quest.
    // It explains the concept of
    // the removed Rat Pits activity.

    companion object {
        private val TITLE = "The ratpits manual"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("<col=08088A>Disclaimer/Warning:",55),
                    BookLine("Rat pit fighting is dangerous",56),
                    BookLine("for the ill prepared.", 57),
                    BookLine("Lots of cats have died",58),
                    BookLine("taking part in the past",59),
                    BookLine("and many more will surely",60),
                    BookLine("die in the future. This",61),
                    BookLine("needless death can be", 62),
                    BookLine("avoided completely by", 63),
                    BookLine("adopting the correct",64),
                    BookLine("precautions.", 65),
                )
            ),
            PageSet(
                Page(
                    BookLine("<col=08088A>Objective:",67),
                    BookLine("The aim of rat pit fighting", 68),
                    BookLine("is to train your cat",69),
                    BookLine("in a fun and profitable",70),
                    BookLine("manner. Rules vary from",71),
                    BookLine("pit to pit.",72),
                    BookLine("Each pit allows a certain",73),
                    BookLine("type of cat fight there",74),
                    BookLine("e.g. the Ardougne pit", 75),
                    BookLine("only permits kittens",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("to fight in it.", 55),
                    BookLine("", 56),
                    BookLine("<col=08088A>How to challenge:",57),
                    BookLine("To compete you must have", 58),
                    BookLine("the correct type of cat",59),
                    BookLine("for the pit and have",60),
                    BookLine("some spare change. If", 61),
                    BookLine("you have both of these,",62),
                    BookLine("you can then challenge",63),
                    BookLine("another cat trainer. The", 64),
                    BookLine("player you challenge",65),
                ),
                Page(
                    BookLine("must also possess the", 66),
                    BookLine("correct type of cat and",67),
                    BookLine("some money. Before you",68),
                    BookLine("proceed to the bidding",69),
                    BookLine("stage tactics should",70),
                    BookLine("be discussed with your",71),
                    BookLine("cat.",72),
                    BookLine("", 73),
                    BookLine("<col=08088A>Tactics:",74),
                    BookLine("A player should tell",75),
                    BookLine("their cat what sort of",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("strategy they should",55),
                    BookLine("adopt. To do this competitors",56),
                    BookLine("should wear an amulet", 57),
                    BookLine("of catspeak when initiating", 58),
                    BookLine("a fight and accepting", 59),
                    BookLine("a challenge. Cautious", 60),
                    BookLine("tactics should be adopted",61),
                    BookLine("by those who are unwilling",62),
                    BookLine("to part with their cat. Using",63),
                    BookLine("these tactics will aid",64),
                    BookLine("the cat in terms of increased",65),
                ),
                Page(
                    BookLine("defence bonuses but penalize",66),
                    BookLine("them as their cats will",67),
                    BookLine("get out of the pits faster",68),
                    BookLine("rather than risking death. Using",69),
                    BookLine("no tactics or aggressive", 70),
                    BookLine("tactics will result in",71),
                    BookLine("your cat fighting to",72),
                    BookLine("the death. Aggressive", 73),
                    BookLine("tactics increase your", 74),
                    BookLine("cats attack levels.",75),
                    BookLine("", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("<col=08088A>Bidding:",55),
                    BookLine("Once a challenge has",56),
                    BookLine("been accepted and both",57),
                    BookLine("cats advised on tactics",58),
                    BookLine("the bidding takes place. Both",59),
                    BookLine("owners can place a small", 60),
                    BookLine("wager on the outcome",61),
                    BookLine("of the match. The wager",62),
                    BookLine("must be the same for",63),
                    BookLine("both sides. Winning",64),
                    BookLine("and losing: Rules differ", 65),
                ),
                Page(
                    BookLine("for each pit, in the",66),
                    BookLine("kitten pits of Ardougne",67),
                    BookLine("the first cat to kill", 68),
                    BookLine("5 rats is declared the",69),
                    BookLine("winner, this number varies",70),
                    BookLine("throughout the pits. If",71),
                    BookLine("your cat dies during",72),
                    BookLine("a fight or runs away",73),
                    BookLine("your opponent is declared",74),
                    BookLine("the winner. Cats tire", 75),
                    BookLine("quickly and after a certain", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("period of time become", 55),
                    BookLine("too exhausted to continue",56),
                    BookLine("fighting, in this case",57),
                    BookLine("the cat with the largest", 58),
                    BookLine("number of kills is declared", 59),
                    BookLine("the winner. If both",60),
                    BookLine("cats have killed the",61),
                    BookLine("same number of rats the",62),
                    BookLine("match is declared a draw.",63),
                    BookLine("", 64),
                    BookLine("<col=08088A>Benefits:", 65),
                ),
                Page(
                    BookLine("Winning a rat pit fight",66),
                    BookLine("increases cats' happiness,",67),
                    BookLine("matures them faster,",68),
                    BookLine("improves their hunting",69),
                    BookLine("skills and keeps them", 70),
                    BookLine("more active. Even losing", 71),
                    BookLine("in a fight will benefit",72),
                    BookLine("your cat (provided it", 73),
                    BookLine("doesn't die). As with", 74),
                    BookLine("everything in life, its",75),
                    BookLine("the taking part which", 76),
                    BookLine("matters.",55),
                ),
                Page(
                    BookLine("Saradomin but rather acted",66),
                    BookLine("in his spirit. For as long",67),
                    BookLine("as we know, those who worship",68),
                    BookLine("Saradomin have fallen prey",69),
                    BookLine("to demons, both those that",70),
                    BookLine("are summoned by dark wizards", 71),
                    BookLine("and those that live wild",72),
                    BookLine("in the desolate north.",73),
                    BookLine("But even priests of Saradomin",74),
                    BookLine("know little about these",75),
                    BookLine("creatures, for all knowledge", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("of them is suppressed by",55),
                    BookLine("the Church. Should we not", 56),
                    BookLine("know our enemies? If we",57),
                    BookLine("were to read the tomes",58),
                    BookLine("of demon-lore, and yet",59),
                    BookLine("keep the light of Saradomin",60),
                    BookLine("in our hearts, might we",61),
                    BookLine("not find new and better",62),
                    BookLine("ways to fight these beasts? It",63),
                    BookLine("was with that thought in",64),
                    BookLine("mind that I performed the", 65),
                ),
                Page(
                    BookLine("actions for which I have",66),
                    BookLine("been convicted. And as",67),
                    BookLine("I read about the various",68),
                    BookLine("types of demon, I did indeed", 69),
                    BookLine("strike upon a way that",70),
                    BookLine("unholy magic could be used",71),
                    BookLine("for a holy purpose. Not",72),
                    BookLine("all demons are most dangerous",73),
                    BookLine("when they are on our plane",74),
                    BookLine("as physical beings. There", 75),
                    BookLine("is also a more subtle demonic",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("influence that pervades",55),
                    BookLine("our world, with demons",56),
                    BookLine("working behind the scenes", 57),
                    BookLine("to cause natural disasters",58),
                    BookLine("and accidents. The chief",59),
                    BookLine("among these demons is Agrith-Naar.",60),
                    BookLine("if Agrith-Naar were to be", 61),
                    BookLine("removed from his own dimension",62),
                    BookLine("he would be unable to work",63),
                    BookLine("his magic on the world,",64),
                    BookLine("and so I came to believe",65),
                ),
                Page(
                    BookLine("that summoning him would",66),
                    BookLine("not be an evil act, but",67),
                    BookLine("a good one. Therefore I",68),
                    BookLine("secretly studied the forbidden",69),
                    BookLine("books and conducted magical",70),
                    BookLine("experiments, until I had",71),
                    BookLine("discovered the means by",72),
                    BookLine("which Agrith-Naar could",73),
                    BookLine("be summoned. The process",74),
                    BookLine("of preparation was complex,",75),
                    BookLine("but the most important",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("part was the construction", 55),
                    BookLine("of a sigil of the demon",56),
                    BookLine("out of silver. The final",57),
                    BookLine("act was for eight persons,",58),
                    BookLine("each holding such a sigil,",59),
                    BookLine("to recite the following",60),
                    BookLine("incantation: 'Tarren Caldar",61),
                    BookLine("Camerinthum Agrith-Naar",62),
                    BookLine("Nahudu' The ritual was",63),
                    BookLine("a success, and the great",64),
                    BookLine("demon Agrith-Naar appeared",65),
                ),
                Page(
                    BookLine("before us. We had prepared",66),
                    BookLine("a magical cage, and with",67),
                    BookLine("difficulty we imprisoned",68),
                    BookLine("the demon. However we were",69),
                    BookLine("unable to destroy him,",70),
                    BookLine("though we tried every physical",71),
                    BookLine("and magical means. Furthermore,",72),
                    BookLine("the magical cage was weakening,",73),
                    BookLine("and we feared it would",74),
                    BookLine("not be able to contain",75),
                    BookLine("him for long. Therefore",76),
                )
            ),
            PageSet(
                Page(
                    BookLine("we decided to... (The last",55),
                    BookLine("pages of the book have",56),
                    BookLine("been torn out)",57),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {}
    // Find correct ID.
}