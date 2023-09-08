package content.quest.member.holygrail.dialogue

import config.Items
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents the King Arthur dialogue file for Holy Grail quest.
 */
class KingArthurHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when (questStage) {
            0 -> {
                when (stage) {
                    0 -> playerl(FacialExpression.HALF_ASKING, "Now I am a knight of the round table, do you have any more quests for me?").also { stage++ }
                    1 -> npcl(FacialExpression.NEUTRAL, "Aha! I'm glad you are here! I am sending out various knights on an important quest. I was wondering if you too would like to take up this quest?").also { stage++ }
                    2 -> options("Tell me of this quest.", "I am weary of questing for the time being...").also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Tell me of this quest.").also { stage++ }
                        2 -> {
                            end()
                            stage = END_DIALOGUE
                        }
                    }
                    4 -> npcl(FacialExpression.NEUTRAL, "Well, we recently found out that the Holy Grail has passed into the Runescape world").also { stage++ }
                    5 -> npcl(FacialExpression.HAPPY, "This is most fortuitous!").also { stage++ }
                    6 -> npcl(FacialExpression.NEUTRAL, "None of my knights ever did return with it last time. Now we have the opportunity to give it another go, maybe this time we will have more luck!").also { stage++ }
                    7 -> options("I'd enjoy trying that.", "I may come back and try that later.").also { stage++ }
                    8 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I'd enjoy trying that.").also { stage++ }
                        2 -> {
                            end()
                            stage = END_DIALOGUE
                        }
                    }
                    9 -> npcl(FacialExpression.NEUTRAL, "Go speak to Merlin. He may be able to give a better clue as to where it is now you have freed him from that crystal.").also { stage++ }
                    10 -> npcl(FacialExpression.NEUTRAL, "He has set up his workshop in the room next to the library.").also {
                        end()
                        setQuestStage(player!!, "Holy Grail", 10)
                        stage = END_DIALOGUE
                    }
                }
            }

            10 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.ASKING, "How goes thy quest?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "I have made progress, but I have not recovered the Grail yet.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well, the Grail IS very elusive, it may take some perseverance. As I said before, speak to Merlin in the workshop by the library.").also { stage = END_DIALOGUE }
                }
            }

            20 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.ASKING, "How goes thy quest?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "I have made progress, but I have not recovered the Grail yet.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "My knight, Sir Ghalad, is in Mcgrubor woods. he may have information you need.").also { stage = END_DIALOGUE }
                }
            }

            30 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.ASKING, "How goes thy quest?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "I have made progress, but I have not recovered the Grail yet.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "This is a good time to seek the Fisher Realm.").also { stage = END_DIALOGUE }
                }
            }

            40 -> {
                when (stage) {
                    0 -> playerl(FacialExpression.ASKING, "Hello, do you have a knight named Sir Percival?").also { stage++ }
                    1 -> npcl(FacialExpression.HALF_THINKING, "Ah yes. I remember young Percival. He rode off on a quest a couple of months ago. We are getting a bit worried, he's not back yet...").also { stage++ }
                    2 -> npcl(FacialExpression.HALF_THINKING, "he was going to try and recover the golden boots of Arkaneeses.").also { stage++ }
                    3 -> playerl(FacialExpression.ASKING, "Any idea which way that would be?").also { stage++ }
                    4 -> npcl(FacialExpression.NEUTRAL, "Not exactly. We discovered some magic golden feathers that are said to point the way to the boots... ").also { stage++ }
                    5 -> npcl(FacialExpression.NEUTRAL, "They certainly point somewhere. Just blowing on them gently will supposedly show the way to go.").also { stage++ }
                    6 -> sendDialogue(player!!, "King Arthur gives you a feather.").also { stage++ }
                    7 -> addItem(player!!, 18, 1)
                }
            }

            50 -> {
                npcl(FacialExpression.ASKING, "How goes thy quest?")
                if (removeItem(player!!, Items.HOLY_GRAIL_19)) {
                    when (stage) {
                        0 -> playerl(FacialExpression.HAPPY, "I have retrieved the Grail!").also { stage++ }
                        1 -> npcl(FacialExpression.AMAZED, "Wow! Incredible! You truly are a splendid knight!").also {
                            end()
                            finishQuest(player!!, questName)
                            stage = END_DIALOGUE
                        }
                    }
                } else {
                    when (stage) {
                        0 -> playerl(FacialExpression.NEUTRAL, "I still need to get the Holy Grail.").also { stage++ }
                        1 -> playerl(FacialExpression.NEUTRAL, "I believe it is somewhere in the Fisher Realm.").also { stage++ }
                        2 -> npcl(FacialExpression.NEUTRAL, "Okay, carry on then.").also { stage = END_DIALOGUE }
                    }
                }
            }

            100 -> {
                npcl(FacialExpression.FRIENDLY, "Thank you for retrieving the Grail! You shall long be remembered as one of the greatest heroes amongst" + " the Knights of the Round Table!").also { stage = END_DIALOGUE }
            }

        }
    }

}
