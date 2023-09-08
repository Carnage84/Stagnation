package content.quest.member.recruitmentdrive.npc

import config.NPCs
import core.api.getQuestStage
import core.api.setQuestStage
import core.api.setVarbit
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE

/**
 * Represents the Sir Amik Varze dialogue file for Recruitment Drive quest.
 */
class SirAmikVarzeAdditionalDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Recruitment Drive"
        val questStage = getQuestStage(player!!, questName)
        npc = NPC(NPCs.SIR_AMIK_VARZE_608)

        when {

            (questStage == 0) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Yes, please. Sign me up!").also { stage = 1 }
                    1 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Erm, well, this is a little embarrassing, I already HAVE put you forward as a potential member."
                    ).also { stage = 2 }
                    2 -> npcl(
                        FacialExpression.FRIENDLY,
                        "They are called the Temple Knights, and you are to meet Sir Tiffy Cashien in Falador park for testing immediately."
                    ).also { stage = 3 }
                    3 -> playerl(FacialExpression.FRIENDLY, "I will go do that then.").also { stage = 4 }
                    4 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 10)
                        setVarbit(player!!, 657, 1, true)
                        player!!.questRepository.syncronizeTab(player)
                    }
                }
            }

            (questStage == 10) -> {
                when (stage) {
                    0 -> playerl("Hello Sir Amik.").also { stage = 1 }
                    1 -> npcl("Hello, friend!").also { stage = 2 }
                    2 -> options(
                        "Who are you?",
                        "Can I just skip the test to become a Temple Knight?",
                        "Tell me about the Temple Knights.",
                        "Farewell."
                    ).also { stage = 3 }
                    3 -> when (buttonID) {
                        1 -> playerl("Who are you?").also { stage = 5 }
                        2 -> playerl("Can I just skip the test to become a Temple Knight?").also { stage = 6 }
                        3 -> playerl("Tell me about the Temple Knights.").also { stage = 7 }
                        4 -> playerl("Farewell.").also { stage = END_DIALOGUE }
                    }
                    5 -> npcl("I am the leader of the White Knights of Falador. Why do you seek my audience?").also {
                        stage = END_DIALOGUE
                    }
                    6 -> npcl("No, I am afraid not. I suggest you go meet Sir Tiffy in Falador Park, he will be expecting you.").also {
                        stage = END_DIALOGUE
                    }
                    7 -> npcl("I cannot tell you much... They are called the Temple Knights, and are an organisation that was founded by Saradomin personally many centuries ago.").also { stage++ }
                    8 -> npcl("There are many rumours and fables about their works and actions, but official records of their presence are non-existent.").also { stage++ }
                    9 -> npcl("It is a secret organisation of extraordinary power and resourcefulness... Let me put it this way: ").also { stage++ }
                    10 -> npcl("Should you decide to take them up on their generous offer to join, you will find yourself in an advantageous position that many in this world would envy, and that few are called to occupy.").also { stage++ }
                    11 -> playerl("Well, that wasn't quite as helpful as I thought it would be...but thanks anyway, I guess.").also {
                        stage = END_DIALOGUE
                    }
                }
            }
        }
    }
}