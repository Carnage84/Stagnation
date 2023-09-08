package content.global.random.event

import content.global.random.event.supriseexam.SupriseExamUtils
import core.game.dialogue.DialogueFile
import core.game.node.entity.player.Player
import core.game.system.timer.impl.AntiMacro

/**
 * Represents the Mysterious Old Man dialogue plugin used in "Suprise Exam" random event.
 */
class MysteriousOldManDialogue(val type: String) : DialogueFile() {

    val CHOICE_STAGE = 50000

    override fun handle(componentID: Int, buttonID: Int) {

        if(type == "sexam" && stage < CHOICE_STAGE){
            npc("Would you like to come do a surprise exam?")
            stage = CHOICE_STAGE
        }

        else if(stage >= CHOICE_STAGE){
            when(stage) {
                CHOICE_STAGE -> options("Yeah, sure!", "No, thanks.").also { stage++ }
                CHOICE_STAGE.substage(1) -> when(buttonID){
                    1 -> {
                        end()
                        teleport(player!!,type)
                        AntiMacro.terminateEventNpc(player!!)
                    }
                    2 -> {
                        end()
                        AntiMacro.terminateEventNpc(player!!)
                    }
                }
            }
        }
    }

    fun teleport(player: Player,type: String){
        when(type){
            "sexam" -> SupriseExamUtils.teleport(player)
        }
    }
}