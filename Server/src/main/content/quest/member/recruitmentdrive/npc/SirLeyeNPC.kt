package content.quest.member.recruitmentdrive.npc

import config.NPCs
import core.api.*
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Handles the Sir Leye NPC in Recruitment Drive quest.
 */
@Initializable
class SirLeyeNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return SirLeyeNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SIR_LEYE_2285)

    }

    companion object {
        fun spawnSirLeye(player: Player) {
            val leye = SirLeyeNPC(NPCs.SIR_LEYE_2285)
            leye.location = location(2457, 4966, 0)
            leye.isWalks = true
            leye.isAggressive = true
            leye.isActive = false

            if (leye.asNpc() != null && leye.isActive) {
                leye.properties.teleportLocation = leye.properties.spawnLocation
            }
            leye.isActive = true
            GameWorld.Pulser.submit(object : Pulse(2, leye) {
                override fun pulse(): Boolean {
                    leye.init()
                    registerHintIcon(player, leye)
                    leye.attack(player)
                    runTask(player, 0) {
                        sendChat(leye, "No man may defeat me!")
                    }
                    return true
                }
            })
        }
    }

    override fun finalizeDeath(killer: Entity?) {
        if (killer is Player) {
            setQuestStage(killer, "Recruitment Drive", 95)
            openDialogue(killer, SirKuamFerentseDialogue())
        }
        clear()
        super.finalizeDeath(killer)
    }
}