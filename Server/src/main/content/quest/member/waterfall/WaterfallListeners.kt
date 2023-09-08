package content.quest.member.waterfall

import config.NPCs
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

class WaterfallListeners : InteractionListener {

    val HUDON = NPCs.HUDON_305
    override fun defineListeners() {
        on(HUDON, IntType.NPC, "talk-to"){ player, node ->
            player.dialogueInterpreter.open(HUDON,node.asNpc())
            return@on true
        }
    }

    override fun defineDestinationOverrides() {
        setDest(IntType.NPC,HUDON){ _, _ ->
            return@setDest Location.create(2512, 3481, 0)
        }
    }
}