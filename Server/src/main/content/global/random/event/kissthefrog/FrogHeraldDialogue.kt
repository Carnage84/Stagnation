package content.global.random.event.kissthefrog

import config.NPCs
import core.api.lock
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.system.timer.impl.AntiMacro
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Frog Herald dialogue file.
 */
class FrogHeraldDialogue(val isStarted: Boolean = false) : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.FROG_2471)
        if (isStarted) {
            when (stage) {
                0 -> {
                    lock(player!!, 5)
                    npcl(FacialExpression.OLD_NORMAL, "Welcome to the Land of the Frogs.").also { stage++ }
                }
                1 -> playerl("What am I doing here?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "The Frog " + (if (player!!.isMale) "Princess" else "Prince") + " sent for you.").also { stage++ }
                3 -> playerl("Who is the Frog " + (if (player!!.isMale) "Princess" else "Prince") + "?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "" + (if (player!!.isMale) "She" else "He") + " is the frog with the crown. Make sure you speak to " + (if (player!!.isMale) "Her" else "Him") + ", not the other frogs, or " + (if (player!!.isMale) "She" else "He") + "'ll be offended.").also { stage++ }
                5 -> {
                    end()
                    player!!.getAttribute(FrogUtils.frogTask, 0)
                    player!!.incrementAttribute(FrogUtils.frogTask)
                    stage = END_DIALOGUE
                }
            }
        } else if (AntiMacro.terminateEventNpc(player!!) == null) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "The Frog " + (if (player!!.isMale) "Princess" else "Prince") + " is still waiting. Make sure you speak to " + (if (player!!.isMale) "Her" else "Him") + ", not the other frogs, or " + (if (player!!.isMale) "She" else "He") + "'ll be offended.").also { stage = END_DIALOGUE }
            }
        } else {
            when(stage){
                START_DIALOGUE ->npcl(FacialExpression.OLD_NORMAL,"Hey, ${player!!.username}, The Frog " + (if (player!!.isMale) "Princess" else "Prince") + " needs your help!").also{stage++}
                1 ->options("Okay.","Sorry, I'm busy.").also{stage++}
                2 ->when(buttonID){
                    1 ->{
                        end()
                        FrogUtils.teleport(player!!)
                        player!!.interfaceManager.removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14)
                        player!!.dialogueInterpreter.open(FrogHeraldDialogue(true))
                        AntiMacro.terminateEventNpc(player!!)
                        stage = END_DIALOGUE
                    }
                    2 ->{
                        end()
                        AntiMacro.terminateEventNpc(player!!)
                        stage = END_DIALOGUE
                    }
                }
            }
        }
    }
}