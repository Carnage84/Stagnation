package content.quest.member.murdermystery.dialogue

import config.NPCs
import content.quest.member.murdermystery.MurderMystery
import core.api.getQuestStage
import core.api.sendMessage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Stanford dialogue plugin for Murder Mystery quest.
 */
@Initializable
class StanfordMMDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (!player.questRepository.hasStarted("Murder Mystery")) {
            sendMessage(player!!, "He is ignoring you.").also { stage = END_DIALOGUE }
        } else {
            playerl(FacialExpression.FRIENDLY, "I'm here to help the guards with their investigation.").also { stage++ }
        }
        return true
    }

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, MurderMystery.MurderMystery)) {
            1 -> when (stage) {
                0 -> playerl(FacialExpression.SUSPICIOUS, "I'm here to help the guards with their investigation.").also { stage = 1 }
                1 -> npcl(FacialExpression.FRIENDLY, "How can I help?").also { stage = 2 }
                2 -> if(player!!.questRepository.getStage("Murder Mystery") == 3) {
                    options("Who do you think is responsible?", "Where were you when the murder happened?", "Did you hear any suspicious noises at all?", "Why'd you buy poison the other day?").also { stage = 3 }
                } else {
                    options("Who do you think is responsible?", "Where were you when the murder happened?", "Did you hear any suspicious noises at all?").also { stage = 3 }
                }
                3 -> if(player!!.questRepository.getStage("Murder Mystery") == 3) {
                    when (buttonID) {
                        1 -> playerl(FacialExpression.SUSPICIOUS, "Who do you think is responsible?").also { stage = 4 }
                        2 -> playerl(FacialExpression.SUSPICIOUS, "Where were you when the murder happened?").also { stage = 5 }
                        3 -> playerl(FacialExpression.SUSPICIOUS, "Did you hear any suspicious noises at all?").also { stage = 6 }
                    }
                } else {
                    when (buttonID) {
                        1 -> playerl(FacialExpression.SUSPICIOUS, "Who do you think is responsible?").also { stage = 4 }
                        2 -> playerl(FacialExpression.SUSPICIOUS, "Where were you when the murder happened?").also { stage = 5 }
                        3 -> playerl(FacialExpression.SUSPICIOUS, "Did you hear any suspicious noises at all?").also { stage = 6 }
                        4 -> playerl(FacialExpression.SUSPICIOUS, "Why'd you buy poison the other day?").also { stage = 11 }
                    }
                }
                4 -> npcl(FacialExpression.NEUTRAL, "It was Anna. She is seriously unbalanced. She trashed the garden once then tried to blame it on me! I bet it was her. It's just the kind of thing she'd do! She really hates me and was arguing with Lord Sinclair about trashing the garden a few days ago.").also { stage = END_DIALOGUE }
                5 -> npcl(FacialExpression.NEUTRAL, "Right here, by my little shed. It's very cosy to sit and think in.").also { stage = END_DIALOGUE }
                6 -> npcl(FacialExpression.NEUTRAL, "Not that I remember.").also { stage = 7 }
                7 -> playerl(FacialExpression.ASKING, "So no sounds of a struggle between Lord Sinclair and an intruder?").also { stage = 8 }
                8 -> npcl(FacialExpression.NEUTRAL, "Not to the best of my recollection.").also { stage = 9 }
                9 -> playerl(FacialExpression.ASKING, "How about the guard dog barking?").also { stage = 10 }
                10 -> npcl(FacialExpression.NEUTRAL, "Not that I can recall.").also { stage = END_DIALOGUE }
                11 -> npcl(FacialExpression.NEUTRAL, "Well, Bob mentioned to me the other day he wanted to get rid of the bees in that hive over there. I think I saw him buying poison").also { stage = 12 }
                12 -> npcl(FacialExpression.NEUTRAL, "from that poison salesman the other day. I assume it was to sort out those bees. You'd really have to ask him though.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STANFORD_811)
    }
}