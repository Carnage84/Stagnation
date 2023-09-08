package content.quest.member.digsite.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Researcher dialogue file for The Dig Site quest.
 */
@Initializable
class ResearcherDigsiteDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            // Start
            (questStage == 1) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Hello there. What are you doing here?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "I'm going to pass the Earth Sciences exams so I can dig at the site north of here.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "A good goal to work towards, and it should prove rewarding to you. So where are you starting your studies?").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "I'm not quite sure.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Well, you could try looking in the Earth Sciences section of the shelves over there, although the other students may have beaten you to it. You could even ask the students themselves. I'm sure I saw a group of them on their way to the digsite earlier today.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Anything else?").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "If you need something identified or are not sure about something, give it to Terry; he's the archaeologist expert in the next room.").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "Of course! Let's see now... Rock picks are for splitting rocks or scraping away soil; you can get one from a cupboard in the Education Centre.").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY, "What about sampling jars?").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "I think you'll find them scattered about pretty much everywhere, but I know you can get one from a cupboard somewhere in the Education Centre, just like the rock pick!").also { stage++ }
                    11 -> playerl(FacialExpression.FRIENDLY, "Okay, what about a specimen brush?").also { stage++ }
                    12 -> npcl(FacialExpression.FRIENDLY, "We have a bit of a shortage of those at the moment. You could try borrowing one from a woman on the site... but I don't think they'd give it willingly.").also { stage++ }
                    13 -> playerl(FacialExpression.FRIENDLY, "Sounds like I'll need to be sneaky to get one of those, then... Okay – trowel?").also { stage++ }
                    14 -> npcl(FacialExpression.FRIENDLY, "Ahh... that you must earn by passing your exams! The examiner holds those.").also { stage++ }
                    15 -> playerl(FacialExpression.FRIENDLY, "Anything else?").also { stage++ }
                    16 -> npcl(FacialExpression.FRIENDLY, "If you need something identified or are not sure about something, give it to Terry; he's the archaeologist expert in the next room.").also { stage++ }
                    17 -> playerl(FacialExpression.FRIENDLY, "Ahh, ok thanks.").also { stage = END_DIALOGUE }
                }
            }
        }
    }
}