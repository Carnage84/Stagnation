package content.global.random.event.pinball.dialogue

import config.NPCs
import content.global.random.event.pinball.util.PinballUtils
import core.api.getAttribute
import core.api.lock
import core.api.submitWorldPulse
import core.api.unlock
import core.game.component.Component
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.tools.BLUE

/**
 * Represents the Tilt dialogue for Pinball random event.
 */
class TiltDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.TILT_3913)
        when (stage) {
            0 -> if(getAttribute(player!!, PinballUtils.PINBALL_SCORE, 0) == 10) {
                playerl(FacialExpression.HALF_GUILTY, "So... I'm free to go now right?")
                stage = 10
            } else {
                lock(player!!, 1000000)
                npcl(FacialExpression.OLD_NORMAL, "You poke 10 flashing pillars, right? You NOT poke other pillars, right? Okay, you go play now.")
                stage = 100
            }
            10 -> {
                npcl(FacialExpression.OLD_NORMAL, "Yer, get going. We get break now.")
                stage = 11
            }
            11 -> {
                end()
                PinballUtils.cleanup(player!!)
                submitWorldPulse(object : Pulse(2) {
                    override fun pulse(): Boolean {
                        PinballUtils.reward(player!!)
                        return true
                    }
                })
            }
            100 -> {
                end()
                Component.setUnclosable(player, player!!.dialogueInterpreter.sendPlainMessage(true, "", "Tag the post with the " + BLUE + "flashing rings.",))
                unlock(player!!)
            }
        }
    }
}