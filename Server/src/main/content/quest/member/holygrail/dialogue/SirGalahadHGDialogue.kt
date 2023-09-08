package content.quest.member.holygrail.dialogue

import config.Items
import core.api.addItemOrDrop
import core.api.getQuestStage
import core.api.sendDialogue
import core.api.sendMessage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents the Sir Galahad dialogue file for Holy Grail quest.
 */
class SirGalahadHGDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val questName = "Holy Grail"
        val questStage = getQuestStage(player!!, questName)
        when {
            (questStage == 10) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to my home. It's rare for me to have guests! Would you like a cup of tea? I'll just put the kettle on.").also { stage++ }
                    1 -> sendDialogue(player!!, "Brother Galahad hangs a kettle over the fire.").also { stage++ }
                    2 -> options("I'm looking for Sir Galahad.", "Do you get lonely out here on your own?", "I'm on a quest to find the Holy Grail!", "I seek an item from the realm of the Fisher King.").also { stage++ }
                    3 -> when(buttonID){
                        1 -> playerl(FacialExpression.FRIENDLY,"I'm looking for Sir Galahad.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY,"Do you get lonely out here on your own?").also { stage = 5 }
                        3 -> playerl(FacialExpression.FRIENDLY,"I'm on a quest to find the Holy Grail!").also { stage = 13 }
                        4 -> playerl(FacialExpression.FRIENDLY,"I seek an item from the realm of the Fisher King.").also { stage= 6 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY,"I AM Sir Galahad. Although I've retired as a Knight, and now live as a solitary monk. Also, I prefer to be known as Brother rather than Sir now.").also { stage = 2 }
                    5 -> npcl(FacialExpression.FRIENDLY,"Sometimes I do, yes. Still not many people to share my solidarity with, as most of the religious men around here are worshippers of Saradomin.").also { stage = 2 }
                    6 -> npcl(FacialExpression.FRIENDLY,"Funny you should mention that, but when I left there I took a small cloth from the table as a keepsake.").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "I don't suppose I could borrow that? It could come in useful on my quest.").also { stage++ }
                    8 -> sendDialogue(player!!, "Galahad reluctantly passes you a small cloth.").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY, "I'd better get on with the quest.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY,"Half a moment, your cup of tea is ready.").also { stage++ }
                    11 -> sendDialogue(player!!, "Brother Galahad gives you a cup of tea.").also {
                        addItemOrDrop(player!!, Items.CUP_OF_TEA_1978)
                        stage++
                    }
                    12 -> npcl(FacialExpression.FRIENDLY,"If you do come across any particularly difficult obstacles on your quest, do not hesitate to ask my advice. I know more about the realm of the Grail than many, and I have a feeling you may need to come back and speak to me anyway...").also { stage = END_DIALOGUE }
                    13 -> npcl(FacialExpression.FRIENDLY,"Ah... the Grail... yes... that did fill me with wonder! Oh, that I could have stayed forever! The spear, the food, the people...").also { stage++ }
                    14 -> options("What are you talking about?", "So how can I find it?", "Why did you leave?", "Why didn't you bring the Grail with you?", "I'd better get on with the quest.").also { stage++ }
                    15 -> when(buttonID){
                        1 -> playerl(FacialExpression.HALF_ASKING,"What are you talking about?").also { stage++ }
                        2 -> playerl(FacialExpression.HALF_ASKING,"So how can I find it?").also { stage = 17 }
                        3 -> playerl(FacialExpression.HALF_ASKING,"Why did you leave?").also { stage = 18 }
                        4 -> playerl(FacialExpression.HALF_ASKING,"Why didn't you bring the Grail with you?").also { stage = 19 }
                        5 -> playerl(FacialExpression.FRIENDLY,"I'd better get on with the quest.").also { stage = 20 }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "The Grail castle... It's... hard to describe with words. It mostly felt like a dream!").also { stage = 14 }
                    17 -> npcl(FacialExpression.FRIENDLY, "I did not find it through looking – though admittedly I looked long and hard – eventually, it found me.").also { stage = 14 }
                    18 -> npcl(FacialExpression.FRIENDLY, "Apparently the time is getting close when the world will need Arthur and his knights of the round table again. And that includes me. Leaving was tough for me, so I took a small cloth from the table as a keepsake.").also { stage = END_DIALOGUE }
                    19 -> npcl(FacialExpression.FRIENDLY, "...I'm not sure. Because... it seemed to be... NEEDED in the Grail castle?").also { stage = 14 }
                    20 -> npcl(FacialExpression.FRIENDLY, "If you do come across any particularly difficult obstacles on your quest, do not hesitate to ask my advice. I know more about the realm of the Grail than many, and I have a feeling you may need to come back and speak to me anyway...").also { stage = END_DIALOGUE }
                }
            }

            (questStage == 100) -> {
                when (stage) {
                    0 -> options("I returned the Holy Grail to Camelot!", "It's very peaceful here.").also { stage++ }
                    1 -> when(buttonID){
                        1 -> player("I returned the Holy Grail to Camelot!").also { stage++ }
                        2 -> player("It's very peaceful here.").also { stage = 5 }
                    }
                    2 -> playerl(FacialExpression.FRIENDLY, "I returned the Holy Grail to Camelot!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"I'm impressed! That's something I was never able to do. Half a moment, your cup of tea is ready.").also { stage++ }
                    4 -> {
                        end()
                        addItemOrDrop(player!!, Items.CUP_OF_TEA_1978)
                        sendMessage(player!!, "Brother Galahad gives you a cup of tea.")
                    }
                    5 -> npcl(FacialExpression.FRIENDLY, "Yes, I find the tranquility helps me come ever closer to understanding the true meaning of life.").also { stage++ }
                    6 -> playerl(FacialExpression.HALF_ASKING,"The true meaning of life? Can you tell me what it is?").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY,"Half a moment, your cup of tea is ready.").also { stage++ }
                    8 -> {
                        end()
                        addItemOrDrop(player!!, Items.CUP_OF_TEA_1978)
                        sendMessage(player!!, "Brother Galahad gives you a cup of tea.")
                    }
                }
            }
        }
    }
}