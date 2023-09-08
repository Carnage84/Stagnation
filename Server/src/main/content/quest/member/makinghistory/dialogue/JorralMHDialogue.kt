package content.quest.member.makinghistory.dialogue

import config.NPCs
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import content.quest.member.makinghistory.MakingHistoryListener.Companion.blanin
import content.quest.member.makinghistory.MakingHistoryListener.Companion.blaninProgress
import content.quest.member.makinghistory.MakingHistoryListener.Companion.droalak
import content.quest.member.makinghistory.MakingHistoryListener.Companion.droalakProgress
import content.quest.member.makinghistory.MakingHistoryListener.Companion.merchant
import content.quest.member.makinghistory.MakingHistoryListener.Companion.merchantProgress
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Jorral dialogue plugin for Making History quest.
 */
@Initializable
class JorralMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getAttribute(player, merchantProgress, 0) == 1 && getAttribute(player, droalakProgress, 0) == 1 && getAttribute(player, blaninProgress, 0) == 1) {
            playerl(friendly, "Hi there.").also { stage = 42 }
        } else if (!inInventory(player, MakingHistoryListener.kinglathasLetter) && getAttribute(player, MakingHistoryListener.jorralLetterAttr, 0) == 1) {
            playerl(friendly, "Don't suppose you could give me the letter again?").also { stage = 83 }
        } else if (getQuestStage(player,MakingHistory.questName) == 4 && inInventory(player, MakingHistoryListener.jorralLetter)) {
            npcl(friendly, "Have you taken that letter to King Lathas in Ardougne yet?").also { stage = 81 }
        } else if (inInventory(player, MakingHistoryListener.kinglathasLetter) && getQuestStage(player,MakingHistory.questName) == 10)  {
            playerl(friendly, "I've been to see the king and he gave me this letter.").also { stage = 85 }
        } else if (getAttribute(player, merchant, 0) == 2 && inInventory(player, MakingHistoryListener.merchantChest)) {
            playerl(friendly, "Hi there.").also { stage = 55 }
        } else if (getAttribute(player, merchant, 0) == 3 && removeItem(player, MakingHistoryListener.journalBook)) {
            playerl(friendly, "Hi there.").also { stage = 58 }
        } else if (getAttribute(player, droalak, 0) == 3 && removeItem(player, MakingHistoryListener.droalakScroll)) {
            playerl(friendly, "Hi there.").also { stage = 67 }
        } else if (getAttribute(player, MakingHistoryListener.dron, 0) == 3) {
            npcl(friendly, "How's it going?").also { stage = 72 }
        } else if (getQuestStage(player,MakingHistory.questName) == 100) {
            playerl(friendly, "How's the outpost?").also { stage = 91 }
        } else if (player.questRepository.hasStarted(MakingHistory.questName)) {
            playerl(friendly, "Hi there.").also { stage = 92 }
        } else {
            playerl(friendly, "Hi there.").also { stage = 1 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> npcl(friendly, "All is lost!").also { stage++ }
            2 -> playerl(friendly, "Sorry?").also { stage++ }
            3 -> npcl(friendly, "Just look around you! This great building will soon be in ruin.").also { stage++ }
            4 -> playerl(friendly, "Great building?").also { stage++ }
            5 -> npcl(friendly, "Of course! This building has a history spanning generations!").also { stage++ }
            6 -> playerl(friendly, "Ah, I see, a great history. But why will it be in ruin?").also { stage++ }
            7 -> npcl(friendly, "It's to be ripped apart to make way for King Lathas' new alchemists' lab.").also { stage++ }
            8 -> playerl(friendly, "Does the king not respect the outpost's G R E A T history?").also { stage++ }
            9 -> npcl(friendly, "Well, actually nobody knows what the history is. But there must be something. Wait a minute. You could help me!").also { stage++ }
            10 -> playerl(friendly, "Me?").also { stage++ }
            11 -> npcl(friendly, "Yes! Uncover the history of this building to convince King Lathas to leave it alone. Do you want to know more?").also { stage++ }
            12 -> options("Tell me more.", "I don't care about some dusty building.").also { stage++ }
            13 -> when (buttonId) {
                1 -> if (hasLevelStat(player, Skills.CRAFTING, 24) &&
                    hasLevelStat(player, Skills.SMITHING, 40) &&
                    hasLevelStat(player, Skills.MINING, 40) &&
                    isQuestComplete(player, "Priest in Peril")) {
                    player(friendly, "Tell me more.").also { stage++ }
                } else {
                    end()
                    sendMessage(player, "You don't have enough skills to start this quest.")
                }

                2 -> playerl(friendly, "I don't care about some dusty building.").also { stage = END_DIALOGUE }
            }
            14 -> npcl(friendly, "That's what I like to hear.").also { stage++ }
            15 -> npcl(friendly, "With many occupants over the years, the building has seen much action. It started life as an outpost. Its sole purpose: to see incoming armies before they saw the city of Ardougne.").also { stage++ }
            16 -> npcl(friendly, "If all goes well, I hope to be able to turn it into a museum as a monument to the area's history. What do you think?").also { stage++ }

            17 -> options("OK - I will make a stand for history!", "I don't care about some dusty building.").also { stage++ }
            18 -> when (buttonId) {
                1 -> playerl(friendly, "OK - I will make a stand for history!").also { stage++ }
                2 -> playerl(friendly, "I don't care about some dusty building.").also { stage = END_DIALOGUE }
            }
            19 -> npcl(friendly, "Oh, thank you so much, you really are my saviour!").also { stage++ }
            20 -> playerl(friendly, "But where should I start? What do I need to do now?").also { stage++ }
            21 -> npcl(friendly, "There are three people that may be able to help:").also { stage++ }
            22 -> options(
                "A trader in Ardougne.",
                "A ghost in Port Phasmatys.",
                "A warrior in Rellekka."
            ).also { stage++ }
            23 -> when (buttonId) {
                1 -> npcl(friendly, "There is a silver trader in East Ardougne called Erin, who I believe can help.").also { stage++ }
                2 -> npcl(friendly, "I've been told that there's a ghost far off in Port Phasmatys that moans of losing his life to this place.").also { stage = 30 }
                3 -> npcl(friendly, "Up near the mountains, in Rellekka there is a warrior called Dron whom I have spoken to in the past. He is always on the lookout for information that can improve his fighting and commanding skills.").also { stage = 37 }
            }
            24 -> playerl(friendly, "In what way can he help?").also { stage++ }
            25 -> npcl(friendly, "His Great Grandfather lived in this outpost according to the records. He must know something!").also { stage++ }
            26 -> playerl(friendly, "OK, I'll see what he has to say.").also { stage = 22 }.also { setVarbit(player, 1383, 1, true)
                setQuestStage(player, MakingHistory.questName, 1)
                setAttribute(player, merchant, 1)}
            30 -> playerl(friendly, "Sounds ominous. Does he have a name?").also { stage++ }
            31 -> npcl(friendly, "He does indeed. It's Droalak.").also { stage++ }
            32 -> playerl(friendly, "I'll track him down.").also { stage++ }
            33 -> npcl(friendly, "It might not be so simple! You'll need an amulet of ghostspeak to talk to him!").also { stage++ }
            34 -> playerl(friendly, "I've conversed with the undead before, that shouldn't be too much of a problem.").also { stage = 22 }.also {setVarbit(player, 1383, 1, true)
                setQuestStage(player, MakingHistory.questName, 1)
                setAttribute(player, droalak, 0)}
            37 -> playerl(friendly, "And how's he related to this outpost?").also { stage++ }
            38 -> npcl(friendly, "He isn't directly, but he's studied many wars, and as this used to be an outpost it should have been involved in some war.").also { stage++ }
            39 -> playerl(friendly, "That sounds simple enough.").also { stage++ }
            40 -> npcl(friendly, "He isn't the easiest person to talk tom so you may need to speak to his brother, Blanin, first.").also { stage = END_DIALOGUE }.also {setVarbit(player, 1383, 1, true)
                setQuestStage(player, MakingHistory.questName, 1)
                setAttribute(player, blanin, 1)}
            42 -> npcl(friendly, "It all makes sense now, I never realised there was quite so much history to this place, it was more than I could have hoped.").also { stage++ }
            43 -> playerl(friendly, "I'm glad to hear. What's the whole story?").also { stage++ }
            44 -> npcl(friendly, "Many years ago, there were two friends who fell out over their difference of opinion in religion. One decided to dedicate his life to Saradomin, the other to Zamorak.").also { stage++ }
            45 -> npcl(friendly, "Years passed and the follower of Zamorak moved into this outpost with a group of others to cause havoc for the city of Ardougne.").also { stage++ }
            46 -> npcl(friendly, "The people of the city called upon some Saradomin followers, who happened to be led by the other friend.").also { stage++ }
            47 -> npcl(friendly, "A battle ensued that ended with the two ex-friends at the top of the outpost as the sole survivors. Realising their mistakes they made friends again.").also { stage++ }
            48 -> npcl(friendly, "One decided to become King and spread the word of equality, and the other chose to start up a market where all could trade their wares equally and fairly.").also { stage++ }
            49 -> npcl(friendly, "The one that became king is the great grandfather of the King Lathas that we know!").also { stage++ }
            50 -> playerl(friendly, "So what should I do now?").also { stage++ }
            51 -> npcl(friendly, "Well, what I'll do is write down the details you have provided in a more easy to digest manner, along with my plans for using this building as a museum.").also { stage++ }
            52 -> playerl(friendly, "Then I suppose you want ME to deliver it to the king?").also { stage++ }
            53 -> npcl(friendly, "You've got that right!").also { stage++ }
            54 -> {
                end()
                removeAttributes(player, merchantProgress, droalakProgress, merchantProgress, blaninProgress)
                sendItemDialogue(player, MakingHistoryListener.jorralLetter, "Player receives Letter.")
                setQuestStage(player, MakingHistory.questName, 4)
                addItemOrDrop(player, MakingHistoryListener.jorralLetter)
                stage = END_DIALOGUE
            }
            55 -> npcl(friendly, "How's it going?").also { stage++ }
            56 -> playerl(friendly, "Hi. I found a chest.").also { stage++ }
            57 -> npcl(friendly, "Best you look inside then.").also { stage = END_DIALOGUE }
            58 -> npcl(friendly, "How's it going?").also { stage++ }
            59 -> playerl(friendly, "I found a journal!").also { stage++ }
            60 -> npcl(friendly, "Good work. Let's see what it says.").also { stage++ }
            61 -> sendItemDialogue(player, MakingHistoryListener.journalBook, "Jorral reads through the journal.").also { stage++ }
            62 -> npcl(friendly, "The person who wrote the journal spent time living in the outpost, following the order of Zamorak.").also { stage++ }
            63 -> playerl(friendly, "I see.").also { stage++ }
            64 -> npcl(friendly, "He talks of all the nasty things they did to the people of Ardougne, which I don't care to mention! It looks like they were stopped by someone. But it doesn't say who.").also { stage++ }
            65 -> playerl(friendly, "Interesting.").also { stage++ }
            66 -> {
                end()
                setAttribute(player, merchantProgress, 1)
                setQuestStage(player, MakingHistory.questName, 2)

            }
            67 -> npcl(friendly, "How's it going?").also { stage++ }
            68 -> playerl(friendly, "I have been in contact with the ghost you suggested, and I've recovered this:").also { stage++ }
            69 -> sendItemDialogue(player, MakingHistoryListener.droalakScroll, "Jorral skims over the contents of the scroll.").also { stage++ }
            70 -> npcl(friendly, "Very interesting. So there was a great battle at the outpost. Then one of the survivors became king, and the other started the market place. Good work.").also { stage++ }
            71 -> {
                end()
                setAttribute(player, droalakProgress, 1)
                setQuestStage(player, MakingHistory.questName, 2)
            }
            72 -> playerl(friendly, "I've talked to the warrior.").also { stage++ }
            73 -> npcl(friendly, "Excellent! What did he say?").also { stage++ }
            74 -> {
                playerl(friendly, "Well...").also { stage++ }
            }
            75 -> playerl(friendly, "And then I asked him about getting the blood stains out of his clothes, but he didn't like that!").also { stage++ }
            76 -> npcl(friendly, "It seems you're lucky he didn't beat you up! Well done, that's another piece of the puzzle.").also { stage++ }
            77 -> npcl(friendly, "From the two pieces of evidence, we know that the outpost was occupied by followers of Zamorak who caused havoc to the nearby city of Ardougne.").also { stage++ }
            78 -> npcl(friendly, "Ardougne called in some Saradomin followers to deal with the problem. The two sides were led by ex-friends who settled their differences and decided to worship Guthix.").also { stage++ }
            79 -> npcl(friendly, "But who survived and what happened to them?").also { stage++ }
            80 -> {
                end()
                setAttribute(player, blaninProgress, 1)
                setQuestStage(player, MakingHistory.questName, 2)
            }
            81 -> playerl(friendly, "No, sorry.").also { stage++ }
            82 -> npcl(friendly, "Any time soon would be great.").also { stage = END_DIALOGUE }
            83 -> npcl(friendly, "Ok. Just don't lose it again!").also { stage++ }
            84 -> {
                end()
                addItemOrDrop(player, MakingHistoryListener.jorralLetter)
                setAttribute(player, MakingHistoryListener.jorralLetterAttr, 1)
            }
            85 -> npcl(friendly, "Quick, let me see.").also { stage++ }
            86 -> sendItemDialogue(player, MakingHistoryListener.kinglathasLetter, "Jorral reads the letter.").also { stage++ }
            87 -> npcl(friendly, "Hurrah! Good work, you've done it. You've saved the outpost!").also { stage++ }
            88 -> playerl(friendly, "My pleasure.").also { stage++ }
            89 -> npcl(friendly, "I can now continue with my plans for this place. Thank you.").also { stage++ }
            90 -> {
                end()
                finishQuest(player, MakingHistory.questName)
                setQuestStage(player, MakingHistory.questName, 100)
            }
            91 -> npcl(friendly, "It's perfectly good. Thanks for the help again. Feel free to browse the room next door!").also { stage = END_DIALOGUE }
            92 -> npcl(friendly, "How's it going?").also { stage++ }
            93 -> options("What's the story so far?", "What do I need to do now?", "Got to go, bye!").also { stage++ }
            94 -> when (buttonId) {
                1 -> if (getAttribute(player, merchantProgress, 0) == 1) {
                    playerl(friendly, "What's the story so far?").also { stage = 95 }
                } else if (getAttribute(player, droalakProgress, 0) == 1) {
                    playerl(friendly, "What's the story so far?").also { stage = 96 }
                } else if (getAttribute(player, blaninProgress, 0) == 1) {
                    playerl(friendly, "What's the story so far?").also { stage = 97 }
                } else {
                    npcl(FacialExpression.ANNOYED, "What do you mean? You've discovered nothing!").also { stage = END_DIALOGUE }
                }
                2 -> playerl(friendly, "What do I need to do now?").also { stage = 22 }
                3 -> playerl(friendly, "Got to go, bye!").also { stage = END_DIALOGUE }
            }
            95 -> npcl(friendly, "From the two pieces of evidence, we know that the outpost was lived in by followers of Zamorak, whom caused havoc to the nearby city of Ardougne.").also { stage++ }
            96 ->npcl(friendly, "This must be the time of 'The Dreadful Years of Tragedy', which was stopped in 'The Great battle'.").also { stage = END_DIALOGUE }
            97 -> npcl(friendly, "From the journal you received detailing the experiences of a follower of Zamorak, we know that Zamorak followers were living in the outpost at some point, and that they were being a nuisance to Ardougne").also { stage++ }
            98 -> npcl(friendly,"but the Journal ends abruptly, I think they were halted by someone, some force.").also { stage = END_DIALOGUE }
            99 -> npcl(friendly, "One of the survivors became King, which must be the current King Lathas' Great Grandfather and the other started the market place. But who stopped the Zamorakian followers?").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.JORRAL_2932)
    }
}
