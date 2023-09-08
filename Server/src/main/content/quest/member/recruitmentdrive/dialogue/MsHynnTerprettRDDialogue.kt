package content.quest.member.recruitmentdrive.dialogue

import config.NPCs
import core.api.sendDialogueOptions
import core.api.sendInputDialogue
import core.api.sendNPCDialogue
import core.api.setQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Ms Hynn Terprett dialogue plugin for Recruitment Drive quest.
 */
@Initializable
class MsHynnTerprettRDDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun newInstance(player: Player): DialoguePlugin {
        return MsHynnTerprettRDDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (player.questRepository.getStage("Recruitment Drive") >= 75) {
            if (RandomFunction.getRandom(5) == 1) {
                sendNPCDialogue(player, NPCs.MS_HYNN_TERPRETT_2289, "Counting the creatures and humans in Gielinor you get about a million inhabitants. If you multiply the fingers on their left hand, how many would you get?").also { stage = 1 }
            } else if (RandomFunction.getRandom(5) == 2) {
                sendNPCDialogue(player, NPCs.MS_HYNN_TERPRETT_2289, "Which of the following statements is true?").also { stage = 2 }
            } else if (RandomFunction.getRandom(5) == 3) {
                sendNPCDialogue(player, NPCs.MS_HYNN_TERPRETT_2289, "I have a husband and a daughter, my husband is 4 times older than my daughter and in 20 years my husband will be twice as old as my daughter, how old is my daughter.").also { stage = 3 }
            } else if (RandomFunction.getRandom(5) == 4) {
                sendNPCDialogue(player, NPCs.MS_HYNN_TERPRETT_2289, "If you were sentenced to death, what would you rather choose, being thrown off a castle turret or fed to wolves that haven't eaten in 30 days?").also { stage = 4 }
            } else {
                sendNPCDialogue(player, NPCs.MS_HYNN_TERPRETT_2289, "I dropped four identical stones, into four identical buckets, each containing an identical amount of water.").also { stage = 5 }
            }
        } else {
            npcl(FacialExpression.FRIENDLY, "You certainly have the wits to be a Temple Knight. Pass on through the portal to find your next challenge.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> sendInputDialogue(player!!, true, "Enter amount:") { value ->
                if (value == 0) {
                    setQuestStage(player!!, "Recruitment Drive", 85)
                    npcl(FacialExpression.HAPPY, "Excellent work, Player. Please step through the portal to meet your next challenge.").also { stage = END_DIALOGUE }
                //} else {
                //    runTask(player!!, 3) {
                //        npcl(FacialExpression.SAD, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also { END_DIALOGUE }
                //        teleport(player!!, Location.create(2996, 3375, 0))
                //    }
                }
            }
            2 -> sendDialogueOptions(player, "Select option:", "The number of false statements here is one.", "The number of false statements here is two.", "The number of false statements here is three.", "The number of false statements here is four.").also {
                when (buttonId) {
                    1 -> player(FacialExpression.HALF_GUILTY, "The number of false statements here is one.").also { stage = 2 }
                    2 -> player(FacialExpression.HALF_GUILTY, "The number of false statements here is two.").also { stage = 2 }
                    3 -> player(FacialExpression.HALF_GUILTY, "The number of false statements here is three.").also { stage = 3 }
                    4 -> player(FacialExpression.HALF_GUILTY, "The number of false statements here is four.").also { stage = 2 }
                }
                when (stage) {
                    //2 -> npcl(FacialExpression.SAD, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also {
                    //    runTask(player!!, 3) {
                    //        teleport(player!!, Location.create(2996, 3375, 0))
                    //    }
                    //}
                    3 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 85)
                        npcl(FacialExpression.HAPPY, "Excellent work, Player. Please step through the portal to meet your next challenge.").also { END_DIALOGUE }
                    }
                }
            }
            3 -> sendInputDialogue(player!!, true, "Enter amount:") { value ->
                if (value == 10) {
                    setQuestStage(player!!, "Recruitment Drive", 85)
                    npcl(FacialExpression.HAPPY, "Excellent work, Player. Please step through the portal to meet your next challenge.").also { stage = END_DIALOGUE }
               //} else {
               //     npcl(FacialExpression.SAD, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also { stage = END_DIALOGUE }
               //     runTask(player!!, 3) {
               //         teleport(player!!, Location.create(2996, 3375, 0))
               //     }
                }
            }
            4 -> sendDialogueOptions(
                player,
                "Select option:",
                "The lake of acid.",
                "The large fire.",
                "The wolves.",
                "The castle walls."
            ).also {
                when (buttonId) {
                    1 -> player("The lake of acid.").also { stage = 2 }
                    2 -> player("The large fire.").also { stage = 2 }
                    3 -> player("The wolves.").also { stage = 3 }
                    4 -> player("The castle walls.").also { stage = 2 }
                }
                when (stage) {
                    //2 -> npcl(FacialExpression.SAD, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also {
                    //    runTask(player!!, 3) {
                    //        teleport(player!!, Location.create(2996, 3375, 0))
                    //    }
                    //}
                    3 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 85)
                        npcl(FacialExpression.HAPPY, "Excellent work, Player. Please step through the portal to meet your next challenge.").also { END_DIALOGUE }
                    }
                }
            }
            5 -> npcl(FacialExpression.HALF_ASKING, "The first bucket was at 32 degrees Fahrenheit, the second was at 33 degrees, the third was at 34 and the fourth was at 35 degrees.").also { stage = 6 }
            6 -> npcl(FacialExpression.ASKING, "Which bucket's stone dropped to the bottom of the bucket last?").also { stage = 7 }
            7 -> sendDialogueOptions(player, "Select option:", "Bucket A (32 degrees)", "Bucket B (33 degrees)", "Bucket C (34 degrees)", "Bucket D (35 degrees)").also {
                when (buttonId) {
                    1 -> player("Bucket A").also { stage = 3 }
                    2 -> player("Bucket B").also { stage = 2 }
                    3 -> player("Bucket C").also { stage = 2 }
                    4 -> player("Bucket D").also { stage = 2 }
                }
                when(stage) {
                    //2 -> npcl(FacialExpression.SAD, "No... I am very sorry. Apparently you are not up to the challenge. I will return you where you came from, better luck in the future.").also {
                    //    runTask(player!!, 3) {
                    //        teleport(player!!, Location.create(2996, 3375, 0))
                    //    }
                    //}
                    3 -> {
                        end()
                        setQuestStage(player!!, "Recruitment Drive", 85)
                        npcl(FacialExpression.HAPPY, "Excellent work, Player. Please step through the portal to meet your next challenge.").also { END_DIALOGUE }
                    }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MS_HYNN_TERPRETT_2289)
    }
}





