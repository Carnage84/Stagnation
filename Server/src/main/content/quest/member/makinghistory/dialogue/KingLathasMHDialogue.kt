package content.quest.member.makinghistory.dialogue

import config.Items
import config.NPCs
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the King Lathas dialogue plugin for Making History quest.
 */
@Initializable
class KingLathasMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (inInventory(player, Items.LETTER_6756) && getQuestStage(player, MakingHistory.questName) >= 4) {
            npcl(friendly, "What would you like to talk about?").also { stage = 1 }
        } else if (inInventory(player, Items.LETTER_6757) && getQuestStage(player, MakingHistory.questName) >= 4) {
            npcl(friendly, "Have you taken that letter to Jorral yet?").also { stage = 11 }
        } else if (!inInventory(player, Items.LETTER_6757) && getQuestStage(player, MakingHistory.questName) >= 4) {
            playerl(friendly, "Excuse me sire, but I seem to have lost that letter you gave me.").also { stage = 14 }
        } else {
            sendMessage(player, "The King Lathas is not interested in talking.")
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc = NPC(NPCs.KING_LATHAS_364)
        when (stage) {
            1 -> options("Jorral and the outpost", "West Ardougne").also { stage++ }
            2 -> when (buttonId) {
                1 -> playerl(friendly, "Jorral and the outpost").also { stage++ }
                2 -> playerl(friendly, "West Ardougne").also { stage++ }
            }

            3 -> playerl(
                friendly,
                "Excuse me. I have been asked to hand you this from Jorral at the outpost."
            ).also { stage++ }

            4 -> npcl(friendly, "I see.").also { stage++ }
            5 -> sendItemDialogue(
                player,
                MakingHistoryListener.jorralLetter,
                "The King reads the letter."
            ).also { stage++ }

            6 -> npcl(
                friendly,
                "I had no idea that place had any value at all! All this about my great-grandfather and Jorral's plans to make it into a museum makes for a convincing case."
            ).also { stage++ }

            7 -> playerl(friendly, "I am sure he only wants what is best.").also { stage++ }
            8 -> npcl(
                friendly,
                "Very well, I will comply with his request. Take this letter back to him with my kind regards."
            ).also { stage++ }

            9 -> playerl(friendly, "Thank you.").also { stage++ }
            10 -> if (removeItem(player, MakingHistoryListener.jorralLetter)) {
                end()
                addItemOrDrop(player, MakingHistoryListener.kinglathasLetter)
                sendItemDialogue(player, MakingHistoryListener.kinglathasLetter, "Player receives Letter.")
                setQuestStage(player, MakingHistory.questName, 10)
            }

            11 -> playerl(friendly, "I'm working on it!").also { stage = END_DIALOGUE }
            // If King Lathas's letter has been lost
            14 -> npcl(friendly, "Very well, take another.").also { stage++ }
            // Player receives Letter (King Lathas).
            15 -> {
                end()
                addItemOrDrop(player, MakingHistoryListener.kinglathasLetter)
                sendItemDialogue(player, MakingHistoryListener.kinglathasLetter, "Player receives Letter.")
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.KING_LATHAS_364)
    }
}

