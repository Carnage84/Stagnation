package content.quest.member.trollstronghold.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.sendMessage
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.dialogue.Topic
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Dad dialogue plugin used in Troll Stronghold quest.
 */
@Initializable
class DadTSDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when {

            getQuestStage(player!!, "Troll Stronghold") in 3..4 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.OLD_HAPPY, "What tiny human do in troll arena? Dad challenge human to fight!").also { stage++ }
                    1 -> options("Why are you called Dad?", "I accept your challenge!", "Eek! No thanks.").also { stage++ }
                    2 -> when (buttonId) {
                        1 -> playerl(FacialExpression.THINKING, "Why are you called Dad?").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "I accept your challenge!").also { stage = 4 }
                        3 -> playerl(FacialExpression.SCARED, "Eek! No thanks.").also { stage = END_DIALOGUE }
                    }

                    3 -> npcl(FacialExpression.OLD_HAPPY, "Troll named after first thing try to eat!").also { stage = 1 }
                    4 -> npcl(FacialExpression.OLD_HAPPY, "Tiny human brave. Dad squish!").also { stage++ }
                    5 -> {
                        npc!!.attack(player).also {
                            npc!!.skills.lifepoints = npc!!.skills.maximumLifepoints
                            setQuestStage(player!!, "Troll Stronghold", 4)
                            stage = END_DIALOGUE
                        }
                    }
                }
            }

            getQuestStage(player!!, "Troll Stronghold") in 5..100 -> sendMessage(player!!, "Thormac is not interested in talking.")
        }

        return true
    }
    override fun newInstance(player: Player?): DialoguePlugin {
        return DadTSDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DAD_1125)
    }
}

/**
 * Represents the Dad dialogue file used in Troll Stronghold quest.
 */
class DadTSExtentedDialogue(private val dialogueNum: Int = 0) : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(dialogueNum) {
            1 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_HAPPY, "No human pass through arena without defeating Dad!").also {
                    stage = END_DIALOGUE
                    setQuestStage(player!!, "Troll Stronghold", 3)
                }
            }
            2 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Tiny human brave. Dad squish!").also { stage++ }
                1 -> {
                    npc!!.attack(player).also {
                        npc!!.skills.lifepoints = npc!!.skills.maximumLifepoints
                        setQuestStage(player!!, "Troll Stronghold", 4)
                        stage = END_DIALOGUE
                    }
                }
            }
            3 -> when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Stop! You win. Not hurt Dad.").also { stage++ }
                1 -> showTopics(Topic(FacialExpression.FRIENDLY, "I'll be going now.", END_DIALOGUE), Topic(FacialExpression.ANGRY_WITH_SMILE, "I'm not done yet! Prepare to die!", 2))
                2 -> {
                    player!!.attack(npc).also {
                        setQuestStage(player!!, "Troll Stronghold", 5)
                        stage = END_DIALOGUE
                    }
                }
            }
        }
    }
}