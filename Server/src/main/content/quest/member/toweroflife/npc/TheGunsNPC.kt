package content.quest.member.toweroflife.npc

import config.NPCs
import core.api.sendChat
import core.game.node.entity.npc.AbstractNPC
import core.game.world.map.Location
import core.plugin.Initializable
import core.tools.RandomFunction

/**
 * Handles countdown for The Guns NPC.
 */
@Initializable
class TheGunsNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {

    var count = 4000

    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return TheGunsNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.THE_GUNS_5592)
    }

    override fun init() {
        super.init()
    }

    override fun handleTickActions() {
        super.handleTickActions()
    }


    override fun tick() {
        if (RandomFunction.random(1, 3) == 3) {
            sendChat(this.asNpc(), count++.toString())
        }
        super.tick()
    }
}