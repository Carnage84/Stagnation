package content.quest.member.scorpioncatcher

import config.Items
import config.NPCs
import config.Scenery
import content.quest.member.scorpioncatcher.util.SCUtils
import core.api.*
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Listener for Scorpion Catcher quest.
 */
class ScorpionCatcherListener : InteractionListener {
    override fun defineListeners() {

        // Taverley secret wall.
        on(Scenery.OLD_WALL_2117, IntType.SCENERY, "search"){ player, node ->
            if(getAttribute(player, SCUtils.SECRET_ROOM_ATTR, false) && player.location.y == 9799){
                sendMessage(player, "You've found a secret door.")
                DoorActionHandler.handleDoor(player, node.asScenery())
            } else if(player.location.y == 9798) {
                DoorActionHandler.handleDoor(player, node.asScenery())
            } else {
                sendMessage(player, "Looks like a wall...")
            }
            return@on true
        }

        // Black knight's base.
        on(Scenery.DOOR_31838, IntType.SCENERY, "open") { player, node ->
            if(inInventory(player, Items.JAIL_KEY_1591) && player.location.y == 9690){
                sendMessage(player, "You unlock the door.")
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
            } else if(player.location.y < 9690){
                sendMessage(player, "The door locks shut behind you.")
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
            } else {
                sendMessage(player, "The door is locked.")
            }
            return@on true
        }

        // Traverley Dungeon.
        onUseWith(IntType.NPC, Items.SCORPION_CAGE_456, NPCs.KHARID_SCORPION_386){ player, _, with ->
            if(getAttribute(player, SCUtils.PROGRESS_SC_ATTR_1, false)){
                sendMessage(player, "You've already caught this scorpion")
                return@onUseWith false
            }

            if(removeItem(player, Items.SCORPION_CAGE_456)){
                sendMessage(player, "You catch a scorpion!")
                setAttribute(player, SCUtils.PROGRESS_SC_ATTR_1, true)
                addItemOrDrop(player, Items.SCORPION_CAGE_457)
                runTask(player, 0){
                    teleport(with.asNpc(), Location(2877,9797, 1))
                    runTask(player,5){
                        teleport(with.asNpc()!!, Location(2877,9797, 0))
                    }
                }
            }
            return@onUseWith true
        }

        // Barbarian Outpost.
        onUseWith(IntType.NPC, Items.SCORPION_CAGE_457, NPCs.KHARID_SCORPION_385){ player, _, with ->
            if(getAttribute(player, SCUtils.PROGRESS_SC_ATTR_2, false)){
                sendMessage(player, "You've already caught this scorpion")
                return@onUseWith false
            }

            if(removeItem(player, Items.SCORPION_CAGE_457)){
                sendMessage(player, "You catch a scorpion!")
                setAttribute(player, SCUtils.PROGRESS_SC_ATTR_2, true)
                addItemOrDrop(player, Items.SCORPION_CAGE_458)
                runTask(player, 0){
                    teleport(with.asNpc(), Location(2553,3571, 1))
                    runTask(player,5){
                        teleport(with.asNpc()!!, Location(2553,3571, 0))
                    }
                }
            }

            return@onUseWith true
        }

        // Edgeville Monastery.
        onUseWith(IntType.NPC, Items.SCORPION_CAGE_458, NPCs.KHARID_SCORPION_387){ player, used, with ->
            if(getAttribute(player, SCUtils.PROGRESS_SC_ATTR_3, false)){
                sendMessage(player, "You've already caught this scorpion")
                return@onUseWith false
            }

            if(removeItem(player, Items.SCORPION_CAGE_458)){
                sendMessage(player, "You catch a scorpion!")
                setAttribute(player, SCUtils.PROGRESS_SC_ATTR_3, true)
                addItemOrDrop(player, Items.SCORPION_CAGE_459)
                runTask(player, 0){
                    teleport(with.asNpc(), Location(3058,3488, 2))
                    runTask(player,5){
                        teleport(with.asNpc()!!, Location(3058,3488, 1))
                    }
                }
            }
            return@onUseWith true
        }
    }

}
