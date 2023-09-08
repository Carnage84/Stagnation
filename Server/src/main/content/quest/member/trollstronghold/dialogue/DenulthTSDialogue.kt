package content.quest.member.trollstronghold.dialogue

import config.Items
import core.api.getQuestStage
import core.api.hasAnItem
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.tools.END_DIALOGUE

/**
 * Represents the Denulth dialogue file used in Troll Stronghold quest.
 */
class DenulthTSDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val hasBoots = hasAnItem(player!!, Items.CLIMBING_BOOTS_3105).container != null
        when {
            (getQuestStage(player!!, "Troll Stronghold") in 1..7) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "How are you getting on with rescuing Godric?").also { stage++ }
                    1 -> {
                        if (!hasBoots) {
                            playerl(FacialExpression.FRIENDLY, "I haven't found a way to climb up yet.").also {
                                stage = 3
                            }
                        } else {
                            playerl(FacialExpression.FRIENDLY, " I've got some climbing boots.").also { stage++ }
                        }
                    }
                    2 -> npcl(FacialExpression.FRIENDLY, "Hurry, friend! Who knows what they'll do with Godric?").also { stage = END_DIALOGUE }
                    3 -> npcl(FacialExpression.FRIENDLY, "Then hurry, friend! What are you still doing here?").also { stage = END_DIALOGUE }
                }
            }

            (getQuestStage(player!!, "Troll Stronghold") in 8..10) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Welcome back friend!").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I've found my way into the prison.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "...and?").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "That's all.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Hurry, friend. Find a way to free Godric!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            (getQuestStage(player!!, "Troll Stronghold") >= 11) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Welcome back friend!").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I have freed Godric!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Oh, what great news! You should hurry to tell Dunstan, he will be overjoyed!").also { stage = END_DIALOGUE }
                }
            }
        }
    }
}