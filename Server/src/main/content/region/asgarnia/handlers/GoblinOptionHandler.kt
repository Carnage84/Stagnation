package content.region.asgarnia.handlers

import config.NPCs
import core.api.sendNPCDialogue
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Represents the goblin (talk-to) option.
 */
class GoblinOptionHandler : InteractionListener {

    // Location: Tree gnome stronghold entrance.
    val GOBLIN = NPCs.GOBLIN_444
    override fun defineListeners() {
        on(GOBLIN, IntType.NPC, "Talk-To"){ player, _ ->
            sendNPCDialogue(player, GOBLIN, "Go away, human!", FacialExpression.OLD_ANGRY1)
            return@on true
        }
    }
}