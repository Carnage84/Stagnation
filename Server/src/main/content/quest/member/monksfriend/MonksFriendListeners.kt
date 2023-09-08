package content.quest.member.monksfriend

import config.NPCs
import content.quest.member.monksfriend.npc.BrotherCedricMFDialogue
import content.quest.member.monksfriend.npc.BrotherOmadMFDialogue
import content.quest.member.monksfriend.npc.MonasteryMonkMFDialogue
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC

/**
 * Represents the listener for Monks Friend quest.
 */
class MonksFriendListeners : InteractionListener {
    companion object {
        const val CEDRIC = NPCs.BROTHER_CEDRIC_280
        const val OMAD = NPCs.BROTHER_OMAD_279
        const val MONK = NPCs.MONK_281
    }

    override fun defineListeners() {
        on(CEDRIC, IntType.NPC, "talk-to") { player, _ ->
            openDialogue(player, BrotherCedricMFDialogue(), NPC(NPCs.BROTHER_CEDRIC_280))
            return@on true
        }

        on(OMAD, IntType.NPC, "talk-to") { player, _ ->
            openDialogue(player, BrotherOmadMFDialogue(), NPC(NPCs.BROTHER_OMAD_279))
            return@on true
        }

        on(MONK, IntType.NPC, "talk-to") { player, _ ->
            openDialogue(player, MonasteryMonkMFDialogue(), NPC(NPCs.MONK_281))
            return@on true
        }
    }

}