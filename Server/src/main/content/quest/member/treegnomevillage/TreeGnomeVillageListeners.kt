package content.quest.member.treegnomevillage

import config.Items
import config.NPCs
import content.global.skill.member.agility.AgilityHandler
import content.quest.member.treegnomevillage.TreeGnomeVillage.Companion.mazeEntrance
import content.quest.member.treegnomevillage.TreeGnomeVillage.Companion.mazeVillage
import content.quest.member.treegnomevillage.dialogue.*
import content.quest.member.treegnomevillage.dialogue.trackergnomes.TrackerGnome1Dialogue
import content.quest.member.treegnomevillage.dialogue.trackergnomes.TrackerGnome2Dialogue
import content.quest.member.treegnomevillage.dialogue.trackergnomes.TrackerGnome3Dialogue
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.scenery.Scenery
import core.game.node.scenery.SceneryBuilder
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Direction
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Animation

/**
 * Listeners for Tree Gnome Village quest.
 */
class TreeGnomeVillageListeners : InteractionListener {
    private val ballista = 2181
    private val crumbledWall = 12762
    private val closedChest = 2183
    private val looseRailing = 2186
    private val openedChest = 2182
    private val strongholdDoor = 2184

    override fun defineDestinationOverrides() {
        setDest(IntType.NPC, intArrayOf(NPCs.TRACKER_GNOME_2_482),"talk-to"){ _, _ ->
            return@setDest Location.create(2524, 3256, 0)
        }
    }

    override fun defineListeners() {
        on(NPCs.ELKOY_5179, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, ElkoyTGVDialogue(), npc)
            return@on true
        }
        on(NPCs.ELKOY_5182, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, ElkoyTGVDialogue(), npc)
            return@on true
        }
        on(NPCs.ELKOY_5182, IntType.NPC, "follow"){ player, _ ->
            ElkoyTGVDialogue().travelCutscene(player, if (player.location.y > 3161) mazeVillage else mazeEntrance)
            return@on true
        }
        on(NPCs.TRACKER_GNOME_1_481, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, TrackerGnome1Dialogue(), npc)
            return@on true
        }
        on(NPCs.TRACKER_GNOME_2_482, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, TrackerGnome2Dialogue(), npc)
            return@on true
        }
        on(NPCs.TRACKER_GNOME_3_483, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, TrackerGnome3Dialogue(), npc)
            return@on true
        }
        on(NPCs.KHAZARD_WARLORD_477, IntType.NPC, "talk-to"){ player, npc ->
            openDialogue(player, KhazardWarlordTGVDialogue(), npc)
            return@on true
        }
        on(ballista, IntType.SCENERY, "fire"){ player, _ ->
            openDialogue(player, BallistaTGVDialogue())
            return@on true
        }
        on(looseRailing, IntType.SCENERY, "squeeze-through"){ player, _ ->
            squeezeThrough(player)
            return@on true
        }
        on(crumbledWall, IntType.SCENERY, "climb-over"){ player, _ ->
            openDialogue(player, ClimbWall())
            return@on true
        }
        on(closedChest, IntType.SCENERY, "open"){ player, node ->
            SceneryBuilder.replace(node.asScenery(), Scenery(openedChest, node.location, node.asScenery().rotation),10)
            val upperGuard: NPC = RegionManager.getNpc(player.location, NPCs.KHAZARD_COMMANDER_478, 6) ?: return@on true
            upperGuard.sendChat("Oi. You! Get out of there.")
            upperGuard.attack(player)
            return@on true
        }
        on(openedChest, IntType.SCENERY, "search"){ player, _ ->
            if(!inInventory(player, Items.ORB_OF_PROTECTION_587)){
                sendDialogue(player,"You search the chest. Inside you find the gnomes' stolen orb of protection.")
                addItemOrDrop(player, Items.ORB_OF_PROTECTION_587)
            }
            return@on true
        }
        on(strongholdDoor, IntType.SCENERY, "open"){ player, node ->
            if(player.location.y >= 3251){
                core.game.global.action.DoorActionHandler.handleAutowalkDoor(player, node as Scenery)
            }
            return@on true
        }
    }

    fun squeezeThrough(player: Player){
        val squeezeAnim = Animation.create(3844)

        var dest = if (player.location.y >= 3161)
            player.location.transform(Direction.SOUTH, 1)
        else
            player.location.transform(Direction.NORTH, 1)

        forceMove(player, player.location, dest, 0, 80, anim = 3844)
    }

    private class ClimbWall : DialogueFile() {
        val climbAnimation = Animation(839)
        val wallLoc = Location(2509,3253,0)
        override fun handle(componentID: Int, buttonID: Int) {
            if(getQuestStage(player!!, TreeGnomeVillage.questName) > 30){
                val northSouth = if (player!!.location.y <= wallLoc.y) Direction.NORTH else Direction.SOUTH
                when(stage){
                    0 -> sendDialogue(player!!,"The wall has been reduced to rubble. It should be possible to climb over the remains").also{ stage++ }
                    1 -> AgilityHandler.forceWalk(player!!, -1, player!!.location, player!!.location.transform(northSouth, 2), climbAnimation, 20, 0.0, null).also {
                        end()
                        if(northSouth == Direction.SOUTH) return
                        val lowerGuard: NPC = RegionManager.getNpc(player!!.location, NPCs.KHAZARD_COMMANDER_478, 6) ?: return
                        GameWorld.Pulser.submit(object : Pulse(0) {
                            var count = 0
                            override fun pulse(): Boolean {
                                when (count) {
                                    0 -> {
                                        player!!.lock(4)
                                        lowerGuard.sendChat("What? How did you manage to get in here.")
                                    }
                                    2 -> {
                                        player!!.sendChat("I've come for the orb.")
                                    }
                                    3 -> {
                                        lowerGuard.sendChat("I'll never let you take it.")
                                        lowerGuard.attack(player)
                                        player!!.unlock()
                                        return true
                                    }
                                }
                                count++
                                return false
                            }
                        })
                    }
                }
            }
        }
    }
}
