package content.quest.member.makinghistory.dialogue

import config.NPCs
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import content.quest.member.makinghistory.MakingHistoryListener.Companion.droalak
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Melina dialogue plugin for Making History quest.
 */
@Initializable
class MelinaMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getAttribute(player, droalak, 0) == 1 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet) && inInventory(player, MakingHistoryListener.sapphireAmulet)) {
            playerl(friendly, "If you don't mind me asking, are you Melina?").also { stage = 4 }
        } else if (getAttribute(player, droalak, 0) == 0 && inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            playerl(friendly, "Hi.").also { stage++ }
        } else if (!inEquipment(player, MakingHistoryListener.ghostSpeakAmulet)) {
            npcl(friendly, "wooo wooo").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc = NPC(NPCs.MELINA_2935)
        when (getQuestStage(player!!, MakingHistory.questName)) {
            in 1..2 -> when (stage) {
                1 -> npcl(friendly, "Oh why did he leave me? Did he truly love me?").also { stage++ }
                2 -> playerl(friendly, "Erm. I think you're talking about Droalak. I believe he did love you and he's very sorry for leaving you!").also { stage++ }
                3 -> npcl(friendly, "You're giving me empty words. That is all.").also { stage = END_DIALOGUE }
                4 -> npcl(friendly, "That I am. What's it to you?").also { stage++ }
                5 -> playerl(friendly, "I've been talking to Droalak. I believe he left you but never returned.").also { stage++ }
                6 -> npcl(friendly, "He did. I suppose he has asked you to tell me he's sorry. What an empty gesture!").also { stage++ }
                7 -> playerl(friendly, "Well actually he told me to give you this amulet.").also { stage++ }
                8 -> npcl(friendly, "A sapphire amulet! He remembers! It's just like the one he gave me before he left.").also { stage++ }
                9 -> playerl(friendly, "I honestly believe he's sorry.").also { stage++ }
                10 -> npcl(friendly, "I'm so glad. Please, tell him I forgive him!").also { stage++ }
                11 -> playerl(friendly, "I will.").also { stage++ }
                12 -> npcl(friendly, "At last I feel complete. Farewell.").also { stage++ }
                13 -> playerl(friendly, "Goodbye.").also { stage++ }
                14 -> {
                    end()
                    setAttribute(player, droalak, 2)
                    removeItem(player, MakingHistoryListener.sapphireAmulet)
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MELINA_2935)
    }

}

