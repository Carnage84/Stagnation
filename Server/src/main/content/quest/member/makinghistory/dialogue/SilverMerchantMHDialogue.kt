package content.quest.member.makinghistory.dialogue

import config.Items
import config.NPCs
import content.global.activity.enchantedkey.EnchantedKeyEvent.Companion.totalFindings
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import content.quest.member.makinghistory.MakingHistoryListener.Companion.merchant
import content.quest.member.makinghistory.MakingHistoryListener.Companion.merchantProgress
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.node.entity.npc.NPC
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Silver Merchant dialogue file for Making History quest.
 */
@Initializable
class SilverMerchantMHDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {

        val questName = "Making History"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.SILVER_MERCHANT_569)

        val hasKey = hasAnItem(player!!, Items.ENCHANTED_KEY_6754).container != null

        when (questStage) {
            in 1..2 -> when (stage) {
                0 -> if (getAttribute(player!!, merchant, 0) == 1) {
                    playerl("Are you Erin?").also { stage++ }
                } else if (getAttribute(player!!, merchantProgress, 0) == 1) {
                    playerl("Hello again.").also { stage = 25 }
                } else if (!inInventory(player!!, MakingHistoryListener.enchantedKey)) {
                    playerl("I haven't found anything yet.").also { stage = 14 }
                } else if (!inInventory(player!!, MakingHistoryListener.enchantedKey)) {
                    npcl("Silver! Silver! Best prices for buying and selling in all Kandarin!").also { stage = 16 }
                } else if (inInventory(player!!, MakingHistoryListener.journalBook)) {
                    playerl("Would you like to see the journal I have found?").also { stage = 19 }
                } else if (inInventory(player!!, MakingHistoryListener.merchantChest)) {
                    playerl("I found a chest!").also { stage = 21 }
                } else if (!inInventory(player!!, MakingHistoryListener.merchantChest)) {
                    playerl("I found a chest, but I lost it and any contents it had.").also { stage = 23 }
                } else {
                    playerl("Hello again.").also { stage++ }
                }

                1 -> npcl("That I am. What do you want? You realise I'm working here.").also { stage++ }
                2 -> playerl("This shouldn't take too long. I just wanted to ask you a bit about your great Grandfather, the one who lived in the outpost?").also { stage++ }
                3 -> npcl("What's it to you?").also { stage++ }
                4 -> playerl("I'm doing some research for a man called Jorral. Apparently the King is going to make the outpost into his very own alchemists' lab.").also { stage++ }
                5 -> npcl("That can only cause chaos! Well, my great-grandfather lived and died there according to my mother, but even she knows very little about him.").also { stage++ }
                6 -> playerl("I see.").also { stage++ }
                7 -> npcl("The only thing I have of his is a key. It's a strange key, it changes temperature by itself as you walk around. I'm afraid I don't know what it's for, though.").also { stage++ }
                8 -> playerl("No idea at all?").also { stage++ }
                9 -> npcl("Well, I imagine it's to some sort of chest of his belongings as it's too small for a door. Perhaps if you find some of his belongings you will discover some clues amongst them.").also { stage++ }
                10 -> playerl("It's better than nothing. Will you lend it to me then?").also { stage++ }
                11 -> npcl("Why not, I can't use it anyway. Try feeling its change in temperature as you walk around.").also { stage++ }
                12 -> {
                    end()
                    addItemOrDrop(player!!, MakingHistoryListener.enchantedKey, 1)
                    setAttribute(player!!, merchant, 1)
                    setQuestStage(player!!, MakingHistory.questName, 2)
                }
                14 -> npcl("Keep trying. Have you noticed that the key changes temperature as you walk around?").also { stage = END_DIALOGUE }
                16 -> playerl("That's kind of hard because I lost the key.").also { stage++ }
                17 -> npcl("I was waiting for you to say that...because I just found it! Take it and don't lose it!").also { stage++ }
                18 -> if (freeSlots(player!!) == 0) {
                    end()
                    npcl("Wait, look how much you're carrying! You can't carry this too.").also { stage = END_DIALOGUE }
                    addItemOrDrop(player!!, MakingHistoryListener.enchantedKey, 1)
                } else {
                    end()
                    addItemOrDrop(player!!, MakingHistoryListener.enchantedKey, 1).also { END_DIALOGUE }
                }
                19 -> npcl("Let's have a look.").also { stage++ }
                20 -> sendItemDialogue(player!!, MakingHistoryListener.journalBook, "Erin reads the journal.").also { stage++ }
                21 -> npcl("Very interesting. Best you show it to Jorral at the outpost.").also { stage = END_DIALOGUE }
                22 -> npcl("I wonder what is inside.").also { stage = END_DIALOGUE }
                23 -> npcl("Well I suggest you go back to where you found it.").also { stage++ }
                24 -> {
                    end()
                    setAttribute(player!!, merchant, 2)
                }
                25 -> npcl("Hello, I hope Jorral was pleased with that Journal.").also { stage++ }
                26 -> playerl("I'm sure it's been a valuable find.").also { stage = END_DIALOGUE }
            }

            // After quest, start the miniquest.
            100 -> when (stage) {
                0 -> npcl("Hello, I hope Jorral was pleased with that Journal.").also { stage++ }
                1 -> playerl("I'm sure it's been a valuable find.").also { stage++ }
                2 -> if (hasKey) {
                    end()
                    npcl("You know you can use that enchanted key you have on your keyring all over Gielinor. Who knows what you might find?")
                } else if (getAttribute(player!!, totalFindings, 0) == 11) {
                    playerl("Oh, You know that key you gave me?").also { stage = 8 }
                } else {
                    playerl("What I came to ask was: I lost that key you gave me.").also { stage++ }
                }
                3 -> npcl("Oh dear, luckily I found it, but it'll cost you 500gp as I know it's valuable.").also { stage++ }
                4 -> options("Yes please.", "No thanks.").also { stage++ }
                5 -> when (buttonID) {
                    1 -> playerl("Yes please.").also { stage++ }
                    2 -> playerl("No thanks.").also { stage = END_DIALOGUE }
                }
                6 -> if (!inInventory(player!!, MakingHistoryListener.coins, 500)) {
                    npcl("You don't have enough money, sorry.").also { stage = END_DIALOGUE }
                } else if (freeSlots(player!!) < 0) {
                    npcl("You don't have the space to carry it! Empty some space in your inventory.").also { stage = END_DIALOGUE }
                } else {
                    end()
                    npcl("Thank you, enjoy!")
                    // Enable attribute needed for Enchanted Key minigame.
                    setAttribute(player!!, totalFindings, 0)
                    removeItem(player!!, Item(MakingHistoryListener.coins, 500))
                    addItemOrDrop(player!!, MakingHistoryListener.enchantedKey, 1)
                }
                7 -> npcl("Yes?").also { stage++ }
                8 -> playerl("It dissolved!").also { stage++ }
                9 -> npcl("Really? I suppose it served its purpose.").also { stage = END_DIALOGUE }
            }
        }
    }
}