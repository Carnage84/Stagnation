package content.quest.member.makinghistory.dialogue

import config.NPCs
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import content.quest.member.makinghistory.MakingHistoryListener.Companion.blanin
import core.api.getAttribute
import core.api.getQuestStage
import core.api.setAttribute
import core.api.setQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Dron dialogue plugin for Making History quest.
 */
@Initializable
class DronMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getAttribute(player, blanin, 0) == 1 || getAttribute(player, blanin, 0) == 2) {
            playerl(friendly, "I need to talk to you.").also { stage = 1 }
        } else if (getAttribute(player, blanin, 0) == 3) {
            npcl(friendly, "You have your answers, now go away!").also { stage = END_DIALOGUE }
        } else {
            playerl(friendly, "Excuse me.").also { stage = 67 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val random = RandomFunction.getRandom(4)
        npc = NPC(NPCs.DRON_2939)
        when (getQuestStage(player!!, MakingHistory.questName)) {
            in 1..2 -> when (stage) {
                1 -> npcl(friendly, "Why should I?").also { stage++ }
                2 -> options("Erm, sorry. I don't mean to cause offence...", "Please don't hurt me.").also { stage++ }
                3 -> when (buttonId) {
                    1 -> playerl(friendly, "Erm, sorry. I don't mean to cause offence...").also { stage = 4 }
                    2 -> if (getAttribute(player, blanin, 0) == 2) {
                        playerl(friendly, "Please don't hurt me.").also { stage = 5 }
                    } else {
                        playerl(friendly, "Please don't hurt me.").also { stage = END_DIALOGUE }
                    }
                }
                4 -> when (random) {
                    1 -> npcl(friendly, "Don't waste my time!").also { stage = END_DIALOGUE }
                    2 -> npcl(friendly, "Leave me alone!").also { stage = END_DIALOGUE }
                    3 -> npcl(friendly, "Wrong, wrong, wrong!").also { stage = END_DIALOGUE }
                    4 -> npcl(friendly, "You know nothing!").also { stage = END_DIALOGUE }
                }
                5 -> playerl(friendly, "I'm after important answers.").also { stage++ }
                6 -> npcl(friendly, "But how do you know me?").also { stage++ }
                7 -> options("I just talked to your brother.", "Why, you're the famous warrior Dron!").also { stage++ }
                8 -> when (buttonId) {
                    1 -> playerl(friendly, "I just talked to your brother.").also { stage = 9 }
                    2 -> playerl(friendly, "Why, you're the famous warrior Dron!").also { stage = 10 }
                }
                9 -> npcl(friendly, "Don't talk of my brother. He's not on my good side at the moment.").also {
                    stage = 5
                }
                10 -> npcl(friendly, "If so, what weapon do I use?").also { stage++ }
                11 -> options("An iron axe.", "An iron mace.", "A steel mace.").also { stage++ }
                12 -> when (buttonId) {
                    1 -> playerl(friendly, "An iron axe.").also { stage = 4 }
                    2 -> playerl(friendly, "An iron mace.").also { stage++ }
                    3 -> playerl(friendly, "An steel mace.").also { stage = 4 }
                }
                13 -> npcl(friendly, "When do I like to eat rats?").also { stage++ }
                14 -> options("Breakfast.", "Tea.", "Lunch.").also { stage++ }
                15 -> when (buttonId) {
                    1 -> playerl(friendly, "Breakfast.").also { stage++ }
                    2 -> playerl(friendly, "Tea.").also { stage = 4 }
                    3 -> playerl(friendly, "Lunch.").also { stage = 4 }
                }
                16 -> npcl(friendly, "When are kittens best devoured?").also { stage++ }
                17 -> options("Tea.", "Lunch.").also { stage++ }
                18 -> when (buttonId) {
                    1 -> playerl(friendly, "Tea.").also { stage = 4 }
                    2 -> playerl(friendly, "Lunch.").also { stage++ }
                }
                19 -> npcl(friendly, "What do I usually eat for tea?").also { stage++ }
                20 -> options("Bunnies.", "Kittens.", "Puppies.").also { stage++ }
                21 -> when (buttonId) {
                    1 -> playerl(friendly, "Bunnies.").also { stage = 24 }
                    2 -> playerl(friendly, "Kittens.").also { stage++ }
                    3 -> playerl(friendly, "Puppies.").also { stage = 4 }
                }
                22 -> npcl(friendly, "Eww. Gross. You have some strange tastes.").also { stage++ }
                23 -> playerl(friendly, "You can't lecture ME on eating habits.").also { stage = END_DIALOGUE }
                24 -> npcl(friendly, "What colour spider blood tastes the best?").also { stage++ }
                25 -> options("Green.", "Red.", "Blue.").also { stage++ }
                26 -> when (buttonId) {
                    1 -> playerl(friendly, "Green.").also { stage = 4 }
                    2 -> playerl(friendly, "Red.").also { stage++ }
                    3 -> playerl(friendly, "Blue.").also { stage = 4 }
                }
                27 -> npcl(friendly, "and months?").also { stage++ }
                28 -> options("8.", "21.", "5.").also { stage++ }
                29 -> when (buttonId) {
                    1 -> playerl(friendly, "8.").also { stage++ }
                    2 -> playerl(friendly, "21.").also { stage = 4 }
                    3 -> playerl(friendly, "5.").also { stage = 4 }
                }
                30 -> npcl(friendly, "What are the most interesting ages for battles?").also { stage++ }
                31 -> options("Fourth", "Third and Fourth", "Fifth and Fourth").also { stage++ }
                32 -> when (buttonId) {
                    1 -> playerl(friendly, "Fourth").also { stage = 4 }
                    2 -> playerl(friendly, "Third and Fourth").also { stage = 4 }
                    3 -> playerl(friendly, "Fifth and Fourth").also { stage++ }
                }
                33 -> npcl(friendly, "And my house is situated where?").also { stage++ }
                34 -> options(
                    "North side of the town",
                    "Northwest side of town",
                    "Northeast side of town"
                ).also { stage++ }
                35 -> when (buttonId) {
                    1 -> playerl(friendly, "North side of the town").also { stage = 4 }
                    2 -> playerl(friendly, "Northwest side of town").also { stage = 4 }
                    3 -> playerl(friendly, "Northeast side of town").also { stage++ }
                }
                36 -> npcl(friendly, "What is my brother's name?").also { stage++ }
                37 -> options("Blanin.", "Dave.", "Blanon.").also { stage++ }
                38 -> when (buttonId) {
                    1 -> playerl(friendly, "Blanin.").also { stage = 42 }
                    2 -> playerl(friendly, "Dave.").also { stage = 39 }
                    3 -> playerl(friendly, "Blanon.").also { stage = 4 }
                }
                39 -> npcl(friendly, "Dev?").also { stage++ }
                40 -> playerl(friendly, "No. D-A-V-E").also { stage++ }
                41 -> npcl(friendly, "What a strange name. Unlike my brother's!").also { stage = END_DIALOGUE }
                42 -> npcl(friendly, "And my pet cat is called?").also { stage++ }
                43 -> options("Fluffy.", "Snowy.", "Blanon.").also { stage++ }
                44 -> when (buttonId) {
                    1 -> playerl(friendly, "Fluffy.").also { stage++ }
                    2 -> playerl(friendly, "Snowy.").also { stage = 4 }
                }
                45 -> npcl(friendly, "What's 5 plus 7?").also { stage++ }
                46 -> options("12.", "14.").also { stage++ }
                47 -> when (buttonId) {
                    1 -> playerl(friendly, "12, but what does that have to do with anything?.").also { stage++ }
                    2 -> playerl(friendly, "14.").also { stage = 4 }
                }
                48 -> npcl(friendly, "Everything! Besides, it's 13!").also { stage++ }
                49 -> playerl(friendly, "Er, I think you'll find it's 12.").also { stage++ }
                50 -> npcl(friendly, "...").also { stage++ }
                51 -> npcl(friendly, "..........").also { stage++ }
                52 -> npcl(friendly, "Very well, you seem to know me quite well, I'll answer your questions best I can.").also { stage++ }
                53 -> playerl(friendly, "Phew!").also { stage++ }
                54 -> npcl(friendly, "Well what are they then?").also { stage++ }
                55 -> playerl(friendly, "I'm trying to find out the history of the outpost near Ardougne. I was hoping you could help me.").also { stage++ }
                56 -> npcl(friendly, "Let me see. Ah yes, I remember reading of a battle that took place at the outpost many years ago. Two ex-friends led forces in a small battle that ended with them pitted against each other at the top of").also { stage++ }
                57 -> npcl(friendly, "the outpost as the sole survivors because of their superior strength.").also { stage++ }
                58 -> playerl(friendly, "Ex-friends?").also { stage++ }
                59 -> npcl(friendly, "They were once friends but a difference in their beliefs meant they fell out. One chose to follow the god Zamorak, whilst the other chose Saradomin.").also { stage++ }
                60 -> npcl(friendly, "But finding themselves in this extreme situation at the top of the outpost caused them to see the errors of their ways, like the waste of life, the lost friendship and the wasted time.").also { stage++ }
                61 -> npcl(friendly, "They both then decided to unite under Guthix.").also { stage++ }
                62 -> playerl(friendly, "Ah, I like happy endings.").also { stage++ }
                63 -> npcl(friendly, "You would.").also { stage++ }
                64 -> playerl(friendly, "Thank you, I can report this back to Jorral now.").also { stage++ }
                65 -> npcl(friendly, "Anything else?").also { stage++ }
                66 -> playerl(friendly, "Well, I was wondering how you get the blood stains out of your clothes?").also { stage++ }
                67 -> npcl(friendly, "BE GONE!").also { stage++ }
                68 -> {
                    end()
                    setAttribute(player, blanin, 3)
                    setAttribute(player, MakingHistoryListener.dron, 3)
                    setQuestStage(player, MakingHistory.questName, 2)
                }
                69 -> npcl(
                    friendly,
                    "Look, I don't have time for weaklings, if you want conversation, talk to my brother Blanin!"
                ).also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DRON_2939)
    }

}
