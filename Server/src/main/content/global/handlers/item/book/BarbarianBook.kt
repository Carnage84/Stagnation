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
 * Barbarian Skills Book listener.
 */
class BarbarianBook : InteractionListener {

    // Obtainable from Otto Godblessed
    // when embarking on Barbarian Training.

    companion object {
        private val TITLE = "Barbarian Skills"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("I have noted down what", 55),
                    BookLine("Otto has told me in this", 56),
                    BookLine("journal, so that I may", 57),
                    BookLine("not forget my tasks. His", 58),
                    BookLine("instructions are thus faithfully", 59),
                    BookLine("recorded for posterity. Otto's", 60),
                    BookLine("words on Fishing with rods", 61),
                    BookLine("While you civilised folk use", 62),
                    BookLine("small, weak fishing rods,", 63),
                    BookLine("we barbarians are skilled", 64),
                    BookLine("with heavier tackle. We", 65),
                ),
                Page(
                    BookLine("fish in the lake nearby.", 66),
                    BookLine("Take the rod from under", 67),
                    BookLine("my bed in my dwelling and", 68),
                    BookLine("fish in the lake. When", 69),
                    BookLine("you have caught a few fish", 70),
                    BookLine("I am sure you will be able", 71),
                    BookLine("to talk more with me. You", 72),
                    BookLine("will know when you are", 73),
                    BookLine("ready since my inspiration", 74),
                    BookLine("will fill your mind. We", 75),
                    BookLine("do not use these fish quite", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("as you might expect. When", 55),
                    BookLine("you return from Fishing", 56),
                    BookLine("we can speak of this matter.", 57),
                    BookLine("Your mind is as clear as", 58),
                    BookLine("the waters you have fished.", 59),
                    BookLine("This is good. These are", 60),
                    BookLine("fish that are fat with", 61),
                    BookLine("eggs rather than fat with", 62),
                    BookLine("flesh; this is what we", 63),
                    BookLine("will make use of. Otto's", 64),
                    BookLine("words on Fishing without", 65),
                ),
                Page(
                    BookLine("harpoons First you must", 66),
                    BookLine("know more of harpoons with", 67),
                    BookLine("fish that are usually caught", 68),
                    BookLine("with such a device. You", 69),
                    BookLine("must catch fish which are", 70),
                    BookLine("usually harpooned, without", 71),
                    BookLine("a harpoon. You will be", 72),
                    BookLine("using your skill and strength.", 73),
                    BookLine("Use your arm as bait. Wriggle", 74),
                    BookLine("you fingers as if they", 75),
                    BookLine("are a tasty snack and hungry", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("tuna and swordfish will", 55),
                    BookLine("throng to be caught by", 56),
                    BookLine("you. The method also works", 57),
                    BookLine("with shark - though in this", 58),
                    BookLine("case the action must be", 59),
                    BookLine("more of a frenzied thrashing", 60),
                    BookLine("of the arm than a wriggle. I", 61),
                    BookLine("sense you have more", 62),
                    BookLine("understanding of spears", 63),
                    BookLine("through your studies.", 64),
                    BookLine("Otto's words on Firemaking", 65),
                ),
                Page(
                    BookLine("The first point in your", 66),
                    BookLine("progression is that of lighting", 67),
                    BookLine("fires without a tinderbox. For", 68),
                    BookLine("this process you will require", 69),
                    BookLine("a strung bow. You use the", 70),
                    BookLine("bow to quickly rotate pieces", 71),
                    BookLine("of wood against one another.", 72),
                    BookLine("As you rub the wood becomes", 73),
                    BookLine("hot, eventually springing", 74),
                    BookLine("into flame. The spirits", 75),
                    BookLine("will aid you, the power", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("they supply you will guide", 55),
                    BookLine("your hands. Go benefit", 56),
                    BookLine("from their guidance upon", 57),
                    BookLine("an oaken log. Firemaking", 58),
                    BookLine("with your bow worked -", 59),
                    BookLine("fine news indeed, secrets", 60),
                    BookLine("of our spirit boats now", 61),
                    BookLine("await your attention. Otto's", 62),
                    BookLine("words on potion enhancement", 63),
                    BookLine("If you use a knife upon a", 64),
                    BookLine("fat fish, several new fishy", 65),
                ),
                Page(
                    BookLine("items will be produced.", 66),
                    BookLine("Fish parts can be used", 67),
                    BookLine("as bait; the roe or caviar", 68),
                    BookLine("is more useful for us.", 69),
                    BookLine("Mixing these items with", 70),
                    BookLine("two dose potions is what", 71),
                    BookLine("should be performed. This", 72),
                    BookLine("results in nutritious slop,", 73),
                    BookLine("perfect for healing as", 74),
                    BookLine("well as imparting the effect", 75),
                    BookLine("of the potion. Roe can", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("be used for some more easily", 55),
                    BookLine("combined mixes, while caviar", 56),
                    BookLine("may be used for any mixes", 57),
                    BookLine("of which I am aware. You", 58),
                    BookLine("will discover you will", 59),
                    BookLine("be able to decant a four", 60),
                    BookLine("dose potion into an empty", 61),
                    BookLine("vial, thus giving two potions", 62),
                    BookLine("of two doses. This will", 63),
                    BookLine("aid you in the process.", 64),
                    BookLine("In this case I in fact", 65),
                ),
                Page(
                    BookLine("require a potion for my", 66),
                    BookLine("own stocks. Bring a lesser", 67),
                    BookLine("attack potion combined", 68),
                    BookLine("with fish roe. I see you", 69),
                    BookLine("have my potion. I will", 70),
                    BookLine("say no more than that I", 71),
                    BookLine("am eternally grateful. Otto's", 72),
                    BookLine("words on Smithing spears The", 73),
                    BookLine("next step if to manufacture", 74),
                    BookLine("a spear, suitable for combat.", 75),
                    BookLine("Our distant cousins on", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("Karamja are in need of", 55),
                    BookLine("help, however, you must", 56),
                    BookLine("aid them before I can aid", 57),
                    BookLine("you. You must go now and", 58),
                    BookLine("complete Tai Bwo Wannai", 59),
                    BookLine("Trio quest. Since I have", 60),
                    BookLine("completed this quest, he", 61),
                    BookLine("adds Many warriors complain", 62),
                    BookLine("that spears are difficult", 63),
                    BookLine("to find, we barbarians", 64),
                    BookLine("thus forge our own. If", 65),
                ),
                Page(
                    BookLine("you use our special barbarian", 66),
                    BookLine("anvil here, you will find", 67),
                    BookLine("it ideal. Other forges", 68),
                    BookLine("are not sturdy enough or", 69),
                    BookLine("for the forging work involved.", 70),
                    BookLine("Make any of our spears", 71),
                    BookLine("and return. Note well that", 72),
                    BookLine("you will require wood for", 73),
                    BookLine("the spear shafts, the quality", 74),
                    BookLine("of the wood must be similar", 75),
                    BookLine("to that of the metal involved.", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("The manufacture of spears is", 55),
                    BookLine("now yours as a speciality.", 56),
                    BookLine("Use your skill well. In", 57),
                    BookLine("addition, I am ready to", 58),
                    BookLine("reveal more spear-related", 59),
                    BookLine("crafts. Otto's words on", 60),
                    BookLine("Crafting pyre ships The", 61),
                    BookLine("next step is quite complex", 62),
                    BookLine("so listen well. In order", 63),
                    BookLine("to send our ancestors into", 64),
                    BookLine("the spirit world, their", 65),
                ),
                Page(
                    BookLine("mortal remains must be", 66),
                    BookLine("burned with due ceremony.", 67),
                    BookLine("This can only be performed", 68),
                    BookLine("close to the water on the", 69),
                    BookLine("shore of the lake, just", 70),
                    BookLine("to our north-east. You", 71),
                    BookLine("will recognise the correct", 72),
                    BookLine("places by the ashes to", 73),
                    BookLine("be seen there. You will", 74),
                    BookLine("need to construct a small", 75),
                    BookLine("ship with logs in this", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("area, the add the bones", 55),
                    BookLine("of a long dead barbarian", 56),
                    BookLine("hero. From the caverns", 57),
                    BookLine("beneath this lake. Many", 58),
                    BookLine("of our ancestors travelled", 59),
                    BookLine("to these caverns in order", 60),
                    BookLine("to hunt for glory and only", 61),
                    BookLine("found death. Their bones", 62),
                    BookLine("must still lie inside,", 63),
                    BookLine("their spirits trapped in", 64),
                    BookLine("torment. The spirit will", 65),
                ),
                Page(
                    BookLine("ascend to glory, the pyre", 66),
                    BookLine("will send the earthly remains", 67),
                    BookLine("to the depths. You will", 68),
                    BookLine("also obtain a closer link", 69),
                    BookLine("to the spirit world. During", 70),
                    BookLine("this heightened contact,", 71),
                    BookLine("any bones you bury will", 72),
                    BookLine("have increased importance", 73),
                    BookLine("to the gods. The number", 74),
                    BookLine("of bones buried, before", 75),
                    BookLine("the link fades, is increased", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("with the difficulty of", 55),
                    BookLine("obtaining the wood which", 56),
                    BookLine("you use. I have little", 57),
                    BookLine("knowledge of the caverns,", 58),
                    BookLine("they are blocked from the", 59),
                    BookLine("sight of spirits with whom", 60),
                    BookLine("I commune. I can only suspect", 61),
                    BookLine("that whatever slew barbarians", 62),
                    BookLine("heroes is indeed mighty.", 63),
                    BookLine("I also suggest that these", 64),
                    BookLine("bones may be very uncommon,", 65),
                ),
                Page(
                    BookLine("since heroes are not found", 66),
                    BookLine("in vast numbers. Good luck.", 67),
                    BookLine("Dive into the whirlpool", 68),
                    BookLine("in the lake to the east.", 69),
                    BookLine("The spirits will use their", 70),
                    BookLine("abilities to ensure you", 71),
                    BookLine("arrive in the correct location.", 72),
                    BookLine("Though their influence", 73),
                    BookLine("fades so you must find", 74),
                    BookLine("your own way out. Hail to", 75),
                    BookLine("you, saviour of my ancestors.", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("The spirits herald your", 55),
                    BookLine("presence with a spectral", 56),
                    BookLine("fanfare. On this great", 57),
                    BookLine("day you have my thanks,", 58),
                    BookLine("eternally. May you find", 59),
                    BookLine("riches while rescuing my", 60),
                    BookLine("spiritual ancestors in", 61),
                    BookLine("the caverns for many moons", 62),
                    BookLine("to come. Otto's words", 63),
                    BookLine("on one-handed spears The", 64),
                    BookLine("next step up is to manufacture", 65),
                ),
                Page(
                    BookLine("a one handed version of", 66),
                    BookLine("Such a spear is known as", 67),
                    BookLine("a hasta. As you might suspect,", 68),
                    BookLine("our ways require greater", 69),
                    BookLine("understanding than is gained", 70),
                    BookLine("by looking at a weapon.", 71),
                    BookLine("It is also the case that", 72),
                    BookLine("the process involves a", 73),
                    BookLine("differently balanced spear.", 74),
                    BookLine("Before you use such a weapon", 75),
                    BookLine("in anger you must make an", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("example. Only then will", 55),
                    BookLine("you understand the poise", 56),
                    BookLine("of the techniques involved.", 57),
                    BookLine("You may use our special", 58),
                    BookLine("anvil for this spear type", 59),
                    BookLine("too. As the ways of black", 60),
                    BookLine("and dragon are beyond our", 61),
                    BookLine("knowledge, however, these", 62),
                    BookLine("spears may not be created. I", 63),
                    BookLine("see you have constructed", 64),
                    BookLine("your hasta, and are", 65),
                ),
                Page(
                    BookLine("approaching readiness to live", 66),
                    BookLine("life to its fullest - that you", 67),
                    BookLine("may be a peaceful spirit when", 68),
                    BookLine("your time ends.", 69),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.BARBARIAN_SKILLS_11340, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}