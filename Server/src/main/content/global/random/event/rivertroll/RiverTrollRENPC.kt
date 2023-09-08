package content.global.random.event.rivertroll

import content.global.random.RandomEventNPC
import core.api.utils.WeightBasedTable
import core.game.node.entity.Entity
import core.game.node.entity.npc.NPC
import java.lang.Integer.max

val ids = 391..396

/**
 * Represents the River Troll random event NPC.
 */
class RiverTrollRENPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(391){
    override fun talkTo(npc: NPC) {}
    override fun init() {
        super.init()
        val index = max(0, (player.properties.combatLevel / 20) - 1)
        val id = ids.toList()[index]
        this.transform(id)
        this.attack(player)
        sendChat("Fishies be mine, leave dem fishies!")
        this.isRespawn = false
    }
    override fun finalizeDeath(killer: Entity?) {
        super.finalizeDeath(killer)
    }
    override fun tick() {
        if(!player.location.withinDistance(this.location,8)){
            this.terminate()
        }
        super.tick()
        if(!player.viewport.currentPlane.npcs.contains(this)) this.clear()
    }
}