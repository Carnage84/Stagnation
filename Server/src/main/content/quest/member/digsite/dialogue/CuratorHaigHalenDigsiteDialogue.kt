package content.quest.member.digsite.dialogue

import config.Items
import core.api.addItem
import core.api.getQuestStage
import core.api.removeItem
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.plugin.Initializable
import core.tools.END_DIALOGUE


/**
 * Represents Curator Haig Halen extension dialogue file for The Dig Site quest.
 */
@Initializable
class CuratorHaigHalenDigsiteDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int){
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {

            // Start
            (questStage == 1) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to the museum of Varrock.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "I have been given this letter by an examiner at the The Dig Site. Can you stamp this for me?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "What have we here? A letter of recommendation indeed...").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "The letter here says your name is PLAYER. Well, PLAYER, I wouldn't normally do this for just anyone, but as you did us such a great service with the Shield of Arrav I don't see why not. Run this letter").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "back to the Examiner to begin your adventure into the world of Earth Sciences. Enjoy your studies, Student!").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "There you go, good luck student... Be sure to come back and show me your certificates. I would like to see how you get on.").also { stage++ }
                    6 -> playerl(FacialExpression.FRIENDLY, "Ok, I will. Thanks, see you later.").also { stage++ }
                    7 -> options(
                        "Have you any interesting news?",
                        "Do you know where I could find any treasure?"
                    ).also { stage++ }
                    8 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Have you any interesting news?").also { stage = 9 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Do you know where I could find any treasure?").also { stage = 15 }
                    }
                    9 -> npcl(FacialExpression.FRIENDLY, "Yes, we found a rather interesting island to the north of Morytania. We believe that it may be of archaeological significance.").also { stage++ }
                    10 -> playerl(FacialExpression.FRIENDLY, "Oh? That sounds interesting.").also { stage++ }
                    11 -> npcl(FacialExpression.FRIENDLY, "Indeed. I suspect we'll be looking for qualified archaeologists once we have built the transport we need to get there.").also { stage++ }
                    12 -> playerl(FacialExpression.FRIENDLY, "Would I qualify then?").also { stage++ }
                    13 -> npcl(FacialExpression.FRIENDLY, "You've certainly done a lot to help out Varrock Museum, so we'd be silly not to ask for your expertise.").also { stage++ }
                    14 -> playerl(FacialExpression.FRIENDLY, "Thank you. I'll look forward to it!").also { stage = END_DIALOGUE }
                    15 -> npcl(FacialExpression.FRIENDLY, "Look around you! This museum is full of treasures!").also { stage++ }
                    16 -> playerl(FacialExpression.FRIENDLY, "No, I meant treasures for ME.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Any treasures this museum knows about it goes to great lengths to acquire.").also {
                        stage = 18
                    }
                    18 -> {
                        end()
                        addItem(player!!, Items.SEALED_LETTER_683)
                        removeItem(player!!, Items.UNSTAMPED_LETTER_682)
                        setQuestStage(player!!, "The Dig Site", 2)
                    }
                }
            }

            //After passing the first exam WITH the first exam certificate in inventory
            (questStage == 2) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to the museum of Varrock.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Look what I have been awarded.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well that's great, well done. I'll take that for safe keeping. Come and tell me when you are the next level.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After passing the second exam WITH the second exam certificate in inventory
            (questStage == 3) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to the museum of Varrock.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Look what I have been awarded.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Excellent work! I'll take that for safe keeping, remember to come and see me when you have graduated.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After passing the third exam WITH the third exam certificate in inventory
            (questStage == 4) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to the museum of Varrock.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Look at this certificate, curator...").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well well, a level 3 graduate! I'll keep your certificate safe for you. I feel I must reward you for your work...").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "What would you prefer, something to eat or drink?").also { stage++ }
                    4 -> options("Something to eat please.", "Something to drink please.").also { stage++ }
                    5 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Something to eat please.").also { stage = 6 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Something to drink please.").also { stage = 8 }
                    }
                    6 -> npcl(FacialExpression.FRIENDLY, "Certainly, have this... It's a delicious chocolate cake. You'll like it, I'm sure!").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "A chocolate cake? Thanks!").also { stage = END_DIALOGUE }
                    8 -> npcl(FacialExpression.FRIENDLY, "Certainly, have this... It's a new recipe from the gnome kingdom. You'll like it, I'm sure!").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY, "A cocktail? Cheers!").also { stage = END_DIALOGUE }
                }
            }

            // After passing the third exam WITH all three exam certificates in inventory
            (questStage == 5) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Welcome to the museum of Varrock.").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Look what I have been awarded.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well that's great, well done. I'll take that for safe keeping. Come and tell me when you are the next level.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "Look what I have been awarded.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Excellent work! I'll take that for safe keeping, remember to come and see me when you have graduated.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Look at this certificate, curator...").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Well well, a level 3 graduate! I'll keep your certificate safe for you. I feel I must reward you for your work...").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "What would you prefer, something to eat or drink?").also { stage++ }
                    8 -> options("Something to eat please.", "Something to drink please.").also { stage++ }
                    9 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Something to eat please.").also { stage = 10 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Something to drink please.").also { stage = 12 }
                    }
                    10 -> npcl(FacialExpression.FRIENDLY, "Certainly, have this... It's a delicious chocolate cake. You'll like it, I'm sure!").also { stage++ }
                    11 -> playerl(FacialExpression.FRIENDLY, "A chocolate cake? Thanks!").also { stage = END_DIALOGUE }
                    12 -> npcl(FacialExpression.FRIENDLY, "Certainly, have this... It's a new recipe from the gnome kingdom. You'll like it, I'm sure!").also { stage++ }
                    13 -> playerl(FacialExpression.FRIENDLY, "A cocktail? Cheers!").also { stage = END_DIALOGUE }
                }
            }
        }
    }
}