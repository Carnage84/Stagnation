package content.global.random.event.pinball.npc

import config.Components
import config.NPCs
import content.global.random.RandomEventNPC
import content.global.random.event.pinball.dialogue.MOMpinballDialogue
import content.global.random.event.pinball.util.PinballUtils
import core.api.*
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.link.TeleportManager
import core.game.system.task.Pulse
import core.net.packet.PacketRepository
import core.net.packet.context.MinimapStateContext
import core.net.packet.out.MinimapState
import core.tools.RandomFunction

/**
 * handles the start of Pinball Random event.
 */
class MOMpinballNPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.MYSTERIOUS_OLD_MAN_410) {
    override fun init() {
        super.init()
        submitWorldPulse(object : Pulse(1) {
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    0 -> sendChat("Good afternoon, ${player.username.capitalize()}, care for a quick game?")
                    3 -> {
                        teleport(player, PinballUtils.PINBALL_LOCATION, TeleportManager.TeleportType.NORMAL)
                        PinballUtils.teleport(player)
                    }
                    8 -> {
                        PacketRepository.send(MinimapState::class.java, MinimapStateContext(player, 2))
                        PinballUtils.handlePinballEvent(player)
                        setAttribute(player, PinballUtils.PINBALL_SCORE, 0)
                        setAttribute(player, PinballUtils.GET_PILLAR, RandomFunction.random(5))
                        player.interfaceManager.removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14)
                        openOverlay(player, Components.PINBALL_INTERFACE_263)
                        findNPC(NPCs.MYSTERIOUS_OLD_MAN_410)
                        face(player, findNPC(NPCs.MYSTERIOUS_OLD_MAN_410)!!)
                        openDialogue(player, MOMpinballDialogue())
                        return true
                    }
                }
                return false
            }
        })
    }

    override fun talkTo(npc: NPC) {
        player.dialogueInterpreter.open(MOMpinballDialogue(),npc)
    }
}