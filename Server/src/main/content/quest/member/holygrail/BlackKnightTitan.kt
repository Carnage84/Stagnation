package content.quest.member.holygrail

import config.NPCs
import core.api.sendMessage
import core.game.interaction.InteractionListener
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.world.map.Location
import core.plugin.Initializable

@Initializable
class BlackKnightTitan : AbstractNPC, InteractionListener {
  constructor() : super(NPCs.BLACK_KNIGHT_TITAN_221, null, false)
  private constructor(id: Int, location: Location) : super(id, location) {}


  override fun construct(id: Int, location: Location, vararg objects: Any?): AbstractNPC {
    val npc = BlackKnightTitan(id, location)
    npc.isRespawn = false
    return npc
  }

  override fun getIds(): IntArray {
    return intArrayOf(NPCs.BLACK_KNIGHT_TITAN_221)
  }


  override fun finalizeDeath(killer: Entity) {
    sendMessage(killer.asPlayer(), "Well done, you have defeated the Black Knight Titan!")
  }

  override fun defineListeners() {
  }
}
