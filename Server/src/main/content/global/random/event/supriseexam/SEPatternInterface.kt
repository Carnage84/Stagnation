package content.global.random.event.supriseexam

import config.Components
import config.NPCs
import core.game.interaction.InterfaceListener
import core.game.node.entity.npc.NPC

/**
 * Represents Suprise exam random event interface.
 */
class SEPatternInterface : InterfaceListener {

    val COMPONENT = Components.PATTERN_NEXT_103

    override fun defineInterfaceListeners() {

        on(COMPONENT){player, component, opcode, buttonID, slot, itemID ->
            val index = buttonID - 10
            val correctIndex = player.getAttribute(SupriseExamUtils.SE_KEY_INDEX,0)
            player.interfaceManager.close()

            if(index == correctIndex){
                player.incrementAttribute(SupriseExamUtils.SE_KEY_CORRECT)
                val done = player.getAttribute(SupriseExamUtils.SE_KEY_CORRECT,0) == 3
                player.dialogueInterpreter.open(MordautDialogue(examComplete = done,questionCorrect = true,fromInterface = true), NPC(
                    NPCs.MR_MORDAUT_6117))
            } else {
                player.dialogueInterpreter.open(MordautDialogue(examComplete = false,questionCorrect = false,fromInterface = true),NPC(
                    NPCs.MR_MORDAUT_6117))
            }
            return@on true
        }

        onOpen(COMPONENT){player, component ->
            SupriseExamUtils.generateInterface(player)
            return@onOpen true
        }

    }

}