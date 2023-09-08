package content.region.kandarin.yanille.npc

import core.game.node.entity.combat.BattleState
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.npc.AbstractNPC
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Handles the Salarin Twisted NPC.
 */
@Initializable
public class SalarinTwistedNPC : AbstractNPC {

    /**
     * The spell ids.
     */
    val SPELL_IDS = intArrayOf( 1, 4, 6, 8 )

    /**
     * Constructs a new {@Code SalarinTwistedNPC} {@Code
     * Object}
     */
    public constructor(): super(-1, null) {}

    /**
     * Constructs a new {@Code SalarinTwistedNPC} {@Code
     * Object}
     * @param id the id.
     * @param location the location.
     */
    public constructor(id: Int, location: Location) : super(id, location) {
        super.setAggressive(true)
    }

    override fun construct(id: Int, location: Location, vararg objects: Any?): AbstractNPC {
        return SalarinTwistedNPC(id, location)
    }

    override fun checkImpact(state: BattleState) {
        if (state.style != CombatStyle.MAGIC) {
            state.neutralizeHits()
            return
        }
        if (state.spell  == null) {
            state.neutralizeHits()
            return
        }
        val spell = state.spell
        for (id in SPELL_IDS) {
            if (id == spell.spellId) {
                state.estimatedHit = state.maximumHit
                return
            }
        }
        state.neutralizeHits()
    }

    public override fun getIds(): IntArray {
        return intArrayOf(205);
    }

}