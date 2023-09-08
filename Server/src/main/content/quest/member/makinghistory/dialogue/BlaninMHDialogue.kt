package content.quest.member.makinghistory.dialogue

import config.NPCs
import content.quest.member.makinghistory.MakingHistory
import content.quest.member.makinghistory.MakingHistoryListener
import core.api.getAttribute
import core.api.getQuestStage
import core.api.setAttribute
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Blanin dialogue plugin for Making History quest.
 */
@Initializable
class BlaninMHDialogue(player: Player? = null) : DialoguePlugin(player) {

    val friendly = FacialExpression.FRIENDLY

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (player.questRepository.hasStarted("Making History") && getAttribute(player, MakingHistoryListener.blanin, 0) == 1) {
            playerl(friendly, "Hello there. Are you the brother of Dron?").also { stage = 2 }
        } else if (getAttribute(player, MakingHistoryListener.blanin, 0) == 2) {
            playerl(friendly, "Excuse me.").also { stage = 14 }
        } else if (getAttribute(player, MakingHistoryListener.blanin, 0) == 3) {
            playerl(friendly, "That's one less thing to worry about.").also { stage = 22 }
        } else {
            playerl(friendly, "Excuse me.").also { stage++ }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc = NPC(NPCs.BLANIN_2940)
        when (getQuestStage(player!!, MakingHistory.questName)) {
            in 1..2 -> when (stage) {
                1 -> npcl(friendly, "Look, I don't have time for weaklings, if you want conversation, talk to my brother Blanin!").also { stage = END_DIALOGUE }
                2 -> npcl(friendly, "That I am. Why? Has he killed one of your family?").also { stage++ }
                3 -> playerl(friendly, "Not that I know of...").also { stage++ }
                4 -> npcl(friendly, "Oh good, how can I help you?").also { stage++ }
                5 -> playerl(friendly, "Well I'd like to talk to your brother Dron about the outpost north of Ardougne.").also { stage++ }
                6 -> npcl(friendly, "I'm afraid he's not easy to talk to, so it's good that you came to see me. You'll need to remember a few things when talking to him.").also { stage++ }
                7 -> playerl(friendly, "Like?").also { stage++ }
                8 -> npcl(friendly, "You must be firm with him and don't mention that I sent you. In case he asks, he wields an iron mace in battle, eats rats for breakfast, kittens for lunch and bunnies for tea!").also { stage++ }
                9 -> npcl(friendly, "His favorite drink is red spider blood, he's 36 years, 8 months and 21 days old, studies famous battles of the Fourth and Fifth ages,").also { stage++ }
                10 -> npcl(friendly, " lives on the northeast side of town and his, erm... pet cat is called Fluffy.").also { stage++ }
                11 -> playerl(friendly, "O...kay....").also { stage++ }
                12 -> npcl(friendly, "I know this sounds strange but Dron won't talk to anyone unless they know him well - he's a secretive guy.").also { stage++ }
                13 -> {
                    setAttribute(player, MakingHistoryListener.blanin, 2)
                    end()
                }
                14 -> npcl(friendly, "Making progress?").also { stage++ }
                15 -> playerl(friendly, "Not the best. Can you give me those hints again for speaking with Dron?").also { stage++ }
                16 -> npcl(friendly, "Sure. You must be firm with him and don't mention that I sent you. In case he asks, he wields an iron mace in battle, eats rats for breakfast,").also { stage++ }
                17 -> npcl(friendly, "kittens for lunch and bunnies for His favorite drink is red spider blood, he's 36 years, 8 months and 21 days old, he studies famous battles of the Fourth and Fifth ages,").also { stage++ }
                18 -> npcl(friendly, "lives on the North East side of town and his, erm... pet cat is called Fluffy.").also { stage++ }
                19 -> playerl(friendly, "I think I can remember all that.").also { stage++ }
                20 -> npcl(friendly, "Just remember those points when you speak to Dron. He's nearby, so you should still be able to find him.").also { stage++ }
                21 -> {
                    end()
                }
                22 -> npcl(friendly, "Glad I could help.").also { stage = END_DIALOGUE }

            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BLANIN_2940)
    }
}

