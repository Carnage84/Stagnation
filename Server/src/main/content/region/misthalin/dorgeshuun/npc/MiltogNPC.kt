package content.region.misthalin.dorgeshuun.npc

import config.NPCs
import core.api.sendChat
import core.game.node.entity.npc.AbstractNPC
import core.game.world.map.Location
import core.plugin.Initializable
import core.tools.RandomFunction

/**
 * Handles shouts for Miltog NPC.
 */
@Initializable
class MiltogNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {

    val shout = arrayOf("Lamps!","Lanterns!"," Tinderboxes!","Torches!","Lamp oil!")

    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return MiltogNPC(id, location)
    }

    override fun init() {
        super.init()
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MILTOG_5781)
    }

    override fun handleTickActions() {
        super.handleTickActions()
    }


    override fun tick() {
        if (RandomFunction.random(1, 50) == 25) {
            sendChat(this.asNpc(), shout.random())
        }
        super.tick()
    }

}