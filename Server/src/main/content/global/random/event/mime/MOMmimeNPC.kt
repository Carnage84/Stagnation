package content.global.random.event.mime

import config.NPCs
import content.global.random.RandomEventNPC
import core.api.*
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.tools.RandomFunction

/**
 * Mime Event.
 */
class MOMmimeNPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.MYSTERIOUS_OLD_MAN_410) {
    override fun init(){
        super.init()
        lock(player, 100000)
        submitWorldPulse(object : Pulse(){
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    0 -> sendChat("Aha, you're required, ${player.username.capitalize()}!")
                    4 -> MimeUtils.teleport(player)
                    9 -> sendMessage(player, "You need to copy the mime's performance, then you'll be returned to where you were.")
                    15 -> forceMove(player, MimeUtils.mimeStageLocation, MimeUtils.mimeEndStageLocation, 0, 60,null, MimeUtils.defaultWalkAnim)
                    20 ->{
                        setAttribute(player, MimeUtils.copyEmote, 0)
                        setAttribute(player, MimeUtils.mimeEmote, RandomFunction.random(2,9))
                        MimeUtils.getEmote(player)
                    }
                }
                return false
            }
        })
    }

    override fun talkTo(npc: NPC) {
        MimeUtils.teleport(player)
    }
}