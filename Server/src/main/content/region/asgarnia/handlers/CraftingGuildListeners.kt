package content.region.asgarnia.handlers

import config.Items
import config.NPCs
import config.Scenery
import content.global.skill.free.crafting.TanningProduct
import content.region.asgarnia.dialogue.TheDoorDialogues
import core.api.hasLevelStat
import core.api.inEquipment
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.skill.Skills
import core.game.world.map.Location

/**
 * Listeners for Crafting guild.
 */
class CraftingGuildListeners : InteractionListener {
    private val GUILD_DOOR = Scenery.GUILD_DOOR_2647
    private val APRON = Items.BROWN_APRON_1757
    private val CAPE = Items.CRAFTING_CAPE_9780
    private val TANNER = NPCs.TANNER_804

    override fun defineListeners() {
        on(GUILD_DOOR, IntType.SCENERY, "open") { player, door ->
            if (player.location == Location.create(2933, 3289, 0)) {
                if (hasLevelStat(player, Skills.CRAFTING, 40)) {
                    if (inEquipment(player, APRON)) {
                        openDialogue(player, TheDoorDialogues(0))
                        core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, door.asScenery())
                        return@on true
                    } else if (inEquipment(player, CAPE)) {
                        openDialogue(player, TheDoorDialogues(0))
                        core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, door.asScenery())
                        return@on true
                    } else {
                        openDialogue(player, TheDoorDialogues(1))
                        return@on false
                    }
                } else {
                    openDialogue(player, TheDoorDialogues(2))
                    return@on false
                }
            } else {
                core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, door.asScenery())
                return@on true
            }
        }

        on(TANNER, IntType.NPC, "trade") { player, _ ->
            TanningProduct.open(player, TANNER)
            return@on true
        }
    }
}
