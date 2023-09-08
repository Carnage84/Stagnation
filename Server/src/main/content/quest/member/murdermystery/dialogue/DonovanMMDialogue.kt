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
 * Represents the Donovan dialogue plugin for Murder Mystery quest.
 */
@Initializable
class DonovanMMDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (player.questRepository.hasStarted("Murder Mystery")) {
            playerl(FacialExpression.FRIENDLY, "I'm here to help the guards with their investigation.").also { stage++ }
        } else {
            sendMessage(player!!, "He is ignoring you.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, MurderMystery.MurderMystery)) {
            in 1..3 -> when (stage) {
                1 -> npcl(FacialExpression.NEUTRAL, "How can I help?").also { stage = 2 }
                2 -> if (player!!.questRepository.getStage("Murder Mystery") == 3) {
                    options("Who do you think is responsible?", "Where were you when the murder happened?", "Did you hear any suspicious noises at all?", "Why'd you buy poison the other day?").also { stage = 3 }
                } else {
                    options("Who do you think is responsible?", "Where were you when the murder happened?", "Did you hear any suspicious noises at all?").also { stage = 3 }
                }
                3 -> if (player!!.questRepository.getStage("Murder Mystery") == 3) {
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
                        4 -> playerl(FacialExpression.SUSPICIOUS, "Why'd you buy poison the other day?").also { stage = 7 }
                    }
                }
                4 -> npcl(FacialExpression.NEUTRAL, "Oh... I really couldn't say. I wouldn't really want to point any fingers at anybody. If I had to make a guess I'd have to say it was probably Bob though. I saw him arguing with Lord Sinclair about some missing silverware from the Kitchen. It was a very heated argument.").also { stage = END_DIALOGUE }
                5 -> npcl(FacialExpression.NEUTRAL, "Me? I was sound asleep here in the servants Quarters. It's very hard work as a handyman around here. There's always something to do!").also { stage = END_DIALOGUE }
                6 -> npcl(FacialExpression.NEUTRAL, "No. Can I go yet? Your face irritates me.").also { stage = END_DIALOGUE }
                7 -> npcl(FacialExpression.NEUTRAL, "Well, I do know Frank bought some poison recently to clean the family crest that's outside.").also { stage = 8 }
                8 -> npcl(FacialExpression.NEUTRAL, "It's very old and rusty, and I couldn't clean it myself, so he said he would buy some cleaner and clean it himself. He probably just got some from that Poison Salesman who came to the door the other day...").also { stage = 9 }
                9 -> npcl(FacialExpression.NEUTRAL, "You'd really have to ask him though.").also { stage = 10 }
                10 -> {
                    end()
                    //    setQuestStage(player!!, "Murder Mystery", 4)
                }
            }
            100 -> when (stage) {
                1 -> npcl(FacialExpression.FRIENDLY, "Thank you for all your help in solving the murder.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DONOVAN_THE_FAMILY_HANDYMAN_806)
    }
}