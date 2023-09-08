package content.quest.member.makinghistory.dialogue

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
 * Represents the Droalak dialogue plugin for Making History quest.
 */
@Initializable
class DroalakMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getAttribute(player, MakingHistoryListener.droalak, 0) == 0 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            playerl(friendly, "Hello. Are you Droalak?").also { stage++ }
        } else if (getAttribute(player, MakingHistoryListener.droalak, 0) == 1 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            playerl(friendly, "What do you want me to do again?").also { stage = 17 }
        } else if (getAttribute(player, MakingHistoryListener.droalak, 0) == 1 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet) && inInventory(player, MakingHistoryListener.sapphireAmulet)) {
            playerl(friendly, "I have a sapphire amulet!").also { stage = 16 }
        } else if (getAttribute(player, MakingHistoryListener.droalak, 0) == 2 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            playerl(friendly, "I've given her the amulet. She was very pleased and said she just wanted to know you still cared.").also { stage = 19 }
        } else if (getAttribute(player, MakingHistoryListener.droalak, 0) == 3 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet) && inInventory(player, MakingHistoryListener.droalakScroll)) {
            npcl(friendly, "Take that scroll to Jorral in the outpost.").also { stage = END_DIALOGUE }
        } else if (getAttribute(player, MakingHistoryListener.droalak, 0) == 2 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet) && !inInventory(player, MakingHistoryListener.droalakScroll)) {
            playerl(friendly, "Thanks for the scroll, but I seem to have lost it.").also { stage = 25 }
        } else if (getAttribute(player, MakingHistoryListener.droalakProgress, 0) == 1 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            playerl(friendly, "I have delivered the scroll; you can rest in peace now.").also { stage = 27 }
        } else if (inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            npcl(friendly, "Please, leave me alone.").also { stage = 24 }
        } else {
            npcl(friendly, "wooo wooo").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc = NPC(NPCs.DROALAK_2938)
        when (getQuestStage(player!!, MakingHistory.questName)) {
            in 1..2 -> when (stage) {
                1 -> npcl(friendly, "Wow. I haven't spoken to the living for... for... I don't remember how long.").also { stage++ }
                2 -> playerl(friendly, "So your name IS Droalak?").also { stage++ }
                3 -> npcl(friendly, "Sorry, yes. I am he.").also { stage++ }
                4 -> playerl(friendly, "Great. Do you know anything about the outpost north of Ardougne?").also { stage++ }
                5 -> npcl(friendly, "I don't really like to talk about it, but I died there.").also { stage++ }
                6 -> playerl(friendly, "Oh dear.").also { stage++ }
                7 -> npcl(friendly, "I do have a scroll which might interest you that describes the timeline of the outpost. But first I wonder if I could ask you to tie up a problem?").also { stage++ }
                8 -> playerl(friendly, "Like what?").also { stage++ }
                9 -> npcl(friendly, "Well, I left to go to the outpost against the wishes of my wife. I promised I would return to her, but obviously I did not as I died there. She's a ghost nearby, but won't listen to my apologies.").also { stage++ }
                10 -> playerl(friendly, "You want me to patch things up?").also { stage++ }
                11 -> npcl(friendly, "Yes, how'd you guess?").also { stage++ }
                12 -> playerl(friendly, "Call it 'traveller's intuition'.").also { stage++ }
                13 -> npcl(friendly, "OK, well perhaps you could give her a strung sapphire amulet, because this is what I gave her the day I left. Her name is Melina by the way.").also { stage++ }
                14 -> playerl(friendly, "No problem.").also { stage++ }
                15 -> {
                    end()
                    setAttribute(player, MakingHistoryListener.droalak, 1)
                    setQuestStage(player!!, MakingHistory.questName, 1)
                }
                16 -> npcl(friendly, "Good work. Just give it to Melina, who's wandering somewhere nearby.").also { stage = END_DIALOGUE }
                17 -> npcl(friendly, "Make a strung sapphire amulet and give it to Melina!").also { stage++ }
                18 -> playerl(friendly, "Ok. Ok.").also { stage = END_DIALOGUE }
                19 -> npcl(friendly, "Excellent! I am so glad she believes me. I can finally rest in peace.").also { stage++ }
                20 -> playerl(friendly, "Could I have that scroll you mentioned first?").also { stage++ }
                21 -> npcl(friendly, "Of course. Let me know if it was of any use and then I can be forever free.").also { stage++ }
                22 -> playerl(friendly, "Thank you.").also { stage++ }
                23 -> {
                    end()
                    addItemOrDrop(player, MakingHistoryListener.droalakScroll)
                    setAttribute(player, MakingHistoryListener.droalak, 3)
                    setQuestStage(player, MakingHistory.questName, 2)
                }
                24 -> playerl(friendly, "Your loss!").also { stage = END_DIALOGUE }
                25 -> npcl(friendly, "It's a good job I stuck around then, isn't it! Have another copy.").also { stage++ }
                26 -> {
                    end()
                    addItemOrDrop(player, MakingHistoryListener.droalakScroll)
                }
                27 -> npcl(friendly, "Thanks for telling me! I've been waiting for ages!").also { stage++ }
                28 -> playerl(friendly, "Goodbye.").also { stage++ }
                29 -> npcl(friendly, "Bye!").also { stage++ }
                30 -> {
                    end()
                    transformNpc(NPC(NPCs.DROALAK_2938), NPCs.DROALAK_2937, 10)
                    stage = END_DIALOGUE
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DROALAK_2938)
    }
}

