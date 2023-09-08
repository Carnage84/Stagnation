package content.quest.member.digsite.dialogue

import config.NPCs
import core.api.getQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Doug Deeping Expert dialogue plugin for The Dig Site quest.
 */
@Initializable
class DougDeepingExpertDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "Digsite"
        val questStage = getQuestStage(player!!, questName)

        when {
            // Start without the chest key in inventory
            (questStage == 0) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Well, well... I have a visitor. What are you doing here?").also { stage++ }
                    2 -> options(
                        "I have been invited to research here.",
                        "I'm not really sure.",
                        "I'm here to get rich, rich, rich!",
                        "How could I move a large pile of rocks?"
                    ).also { stage++ }

                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I have been invited to research here.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I'm not really sure.").also { stage = 9 }
                        3 -> playerl(FacialExpression.FRIENDLY, "I'm here to get rich, rich, rich!").also { stage = 10 }
                        4 -> playerl(FacialExpression.FRIENDLY, "How could I move a large pile of rocks?").also { stage = 11 }
                    }

                    4 -> npcl(FacialExpression.FRIENDLY, "Indeed, you must be someone special to be allowed down here.").also { stage++ }
                    5 -> options("Do you know where to find a specimen jar?", "I have things to do...").also { stage++ }
                    6 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Do you know where to find a specimen jar?").also { stage = 7 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I have things to do...").also { stage = 8 }
                        7 -> npcl(FacialExpression.FRIENDLY, "Hmmm, let me think... Nope, can't help you there, I'm afraid. Try asking at the Exam Centre.").also {
                            stage = END_DIALOGUE
                        }

                        8 -> npcl(FacialExpression.FRIENDLY, "Of course, don't let me keep you.").also { stage = END_DIALOGUE }
                        9 -> npcl(FacialExpression.FRIENDLY, "A miner without a clue - how funny!").also { stage = END_DIALOGUE }
                        10 -> npcl(FacialExpression.FRIENDLY, "Oh, well, don't forget that wealth and riches aren't everything.").also {
                            stage = END_DIALOGUE
                        }

                        11 -> npcl(FacialExpression.FRIENDLY, "There used to be this chap that worked in the other shaft. He was working on an explosive chemical mixture to be used for clearing blocked areas underground.").also { stage++ }
                        12 -> npcl(FacialExpression.FRIENDLY, "He left in a hurry one day. Apparently, something in the shaft scared him to death, but he didn't say what.").also { stage++ }
                        13 -> playerl(FacialExpression.FRIENDLY, "Oh?").also { stage++ }
                        14 -> npcl(FacialExpression.FRIENDLY, "Rumour has it he'd been writing a book on his chemical mixture. I'm not sure what goes in it, but I'm sure you'll find the stuff he was using scattered around the digsite. He left so quickly, he didn't take anything with").also { stage++ }
                        15 -> npcl(FacialExpression.FRIENDLY, "him. In fact, I still have a chest key he gave me to look after - perhaps it's more useful to you.").also { stage++ }
                        16 -> npcl(FacialExpression.FRIENDLY, "Doug hands you a key").also { stage = END_DIALOGUE }
                    }
                }
            }

            // WITH the chest key in inventory
            (questStage == 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Well, well... I have a visitor. What are you doing here?").also { stage++ }
                    2 -> options(
                        "I have been invited to research here.",
                        "I'm not really sure.",
                        "I'm here to get rich, rich, rich!",
                        "How could I move a large pile of rocks?"
                    ).also { stage++ }

                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I have been invited to research here.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I'm not really sure.").also { stage = 9 }
                        3 -> playerl(FacialExpression.FRIENDLY, "I'm here to get rich, rich, rich!").also { stage = 10 }
                        4 -> playerl(FacialExpression.FRIENDLY, "How could I move a large pile of rocks?").also { stage = 11 }
                    }

                    4 -> npcl(FacialExpression.FRIENDLY, "Indeed, you must be someone special to be allowed down here.").also { stage++ }
                    5 -> options("Do you know where to find a specimen jar?", "I have things to do...").also { stage++ }
                    6 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Do you know where to find a specimen jar?").also { stage = 7 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I have things to do...").also { stage = 8 }
                        7 -> npcl(FacialExpression.FRIENDLY, "Hmmm, let me think... Nope, can't help you there, I'm afraid. Try asking at the Exam Centre.").also {
                            stage = END_DIALOGUE
                        }

                        8 -> npcl(FacialExpression.FRIENDLY, "Of course, don't let me keep you.").also { stage = END_DIALOGUE }
                        9 -> npcl(FacialExpression.FRIENDLY, "A miner without a clue - how funny!").also { stage = END_DIALOGUE }
                        10 -> npcl(FacialExpression.FRIENDLY, "Oh, well, don't forget that wealth and riches aren't everything.").also {
                            stage = END_DIALOGUE
                        }

                        11 -> npcl(FacialExpression.FRIENDLY, "There used to be this chap that worked in the other shaft. He was working on an explosive chemical mixture to be used for clearing blocked areas underground.").also { stage++ }
                        12 -> npcl(FacialExpression.FRIENDLY, "He left in a hurry one day. Apparently, something in the shaft scared him to death, but he didn't say what.").also { stage++ }
                        13 -> playerl(FacialExpression.FRIENDLY, "Oh?").also { stage++ }
                        14 -> npcl(FacialExpression.FRIENDLY, "Rumour has it he'd been writing a book on his chemical mixture. I'm not sure what goes in it, but I'm sure you'll find the stuff he was using scattered around the digsite. He left so quickly, he didn't take anything with").also { stage++ }
                        15 -> npcl(FacialExpression.FRIENDLY, "him. In fact, I still have a chest key he gave me to look after - perhaps it's more useful to you.").also { stage++ }
                        16 -> npcl(FacialExpression.FRIENDLY, "It's okay, I already have one.").also {
                            stage = END_DIALOGUE
                        }
                    }
                }
            }

            // Attempting to pickpocket
            (questStage == 2) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Hey! Trying to steal from me, are you? What do you think I am, stupid or something?!").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Err... Sorry.").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DOUG_DEEPING_614)
    }
}


