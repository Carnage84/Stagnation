package content.quest.member.merlinsquest

import config.Items
import core.api.*
import core.game.global.action.DropListener
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.impl.ForceMovement
import core.game.node.entity.npc.NPC
import core.game.world.map.Direction
import core.game.world.map.Location

/**
 * Merlin quest Listeners.
 */
class MerlinCrystalListeners : InteractionListener {
    private val BONE_DROP_LOCATION = Location(2780, 3515, 0)

    override fun defineListeners() {
        on (Items.BAT_BONES_530, IntType.ITEM, "drop") { player, node ->
            val merlinStage = getQuestStage(player, "Merlin's Crystal")
            var doingQuest = player.location == BONE_DROP_LOCATION && merlinStage == 80
            var hasAuxiliaryRequirements = inInventory(player, Items.LIT_BLACK_CANDLE_32) && getAttribute<NPC?>(player, "thrantax_npc", null) == null

            if (doingQuest && hasAuxiliaryRequirements) {
                ForceMovement.run(player, player.location.transform(-2, 0, 0)).direction = Direction.EAST
                var npc = ThrantaxNPC(
                    player,
                    Location(2780, 3515, 0)
                )
                npc.player = player
                npc.init()
                npc.isRespawn = false
                setAttribute(player, "thrantax_npc", npc)
                setAttribute(npc, "thrantax_owner", player.username)
                player.dialogueInterpreter.open("thrantax_dialogue", 34, 248)
                return@on true
            } else if (doingQuest) {
                sendDialogue(player, "You'll need to light the black candle before the spirit will appear.")
                return@on true
            }

            DropListener.drop(player, node.asItem())
            return@on true
        }
    }
}
