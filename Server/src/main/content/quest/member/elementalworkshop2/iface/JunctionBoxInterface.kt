package content.quest.member.elementalworkshop2.iface

import content.quest.member.elementalworkshop2.util.EW2Utils
import core.api.*
import core.game.dialogue.FacialExpression
import core.game.interaction.InterfaceListener

/**
 * Handles attach/detach Pipe interface for Elemental Workshop II.
 */
class JunctionBoxInterface : InterfaceListener {

    private val junctionBox = 262
    override fun defineInterfaceListeners() {

        onOpen(junctionBox) { player, _ ->
            player.packetDispatch.sendLeftShiftedVarbit(898, 9, 13)
            return@onOpen true
        }

        onClose(junctionBox) { player, _ ->
            sendPlayerDialogue(player, "I hope I got that right.", FacialExpression.HAPPY)
            return@onClose true
        }

        on(junctionBox) { player, _, _, buttonID, _, _ ->
            val button1 = 24
            val button2 = 25
            val button3 = 20
            val button4 = 21
            val button5 = 22
            val button6 = 23

            if (buttonID == button2) {
                setAttribute(player, "ew2:press-2", true)
            }
            if (buttonID == button4) {
                setAttribute(player, "ew2:press-4", true)
            }
            if (buttonID == button1) {
                setAttribute(player, "ew2:press-1", true)
            }

            when (buttonID) {
                button5 -> if (getAttribute(player, "ew2:press-4", true)) {
                    player.packetDispatch.sendLeftShiftedVarbit(898, 9, 0)
                    player.packetDispatch.sendInterfaceConfig(junctionBox, 8, false)
                    player.incrementAttribute(EW2Utils.questProgress, 1)
                }

                button3 -> if (getAttribute(player, "ew2:press-2", true)) {
                    player.packetDispatch.sendLeftShiftedVarbit(898, 9, 2)
                    player.packetDispatch.sendInterfaceConfig(junctionBox, 17, false)
                    player.incrementAttribute(EW2Utils.questProgress, 1)
                }

                button6 -> if (getAttribute(player, "ew2:press-1", true)) {
                    player.packetDispatch.sendLeftShiftedVarbit(898, 9, 1)
                    player.packetDispatch.sendInterfaceConfig(junctionBox, 12, false)
                    player.incrementAttribute(EW2Utils.questProgress, 1)
                }
            }

            if (getAttribute(player, EW2Utils.questProgress, 0) == 3) {
                lock(player,3)
                runTask(player, 2) {
                    closeInterface(player)
                    sendPlayerDialogue(player, "I hope I got that right.", FacialExpression.HAPPY)
                }
            }
            return@on true
        }
    }
}