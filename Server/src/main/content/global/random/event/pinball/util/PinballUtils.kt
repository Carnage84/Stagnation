package content.global.random.event.pinball.util

import config.Components
import config.Items
import config.NPCs
import content.global.random.event.pinball.dialogue.MOMpinballDialogue
import core.api.*
import core.game.component.Component
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.scenery.Scenery
import core.game.system.timer.impl.AntiMacro
import core.game.world.map.Location
import core.net.packet.PacketRepository
import core.net.packet.context.MinimapStateContext
import core.net.packet.out.MinimapState
import core.tools.RandomFunction

/**
 * Utils for Pinball random event.
 */
object PinballUtils {
    const val PINBALL_LOGOUT = "pinball-logout"
    const val PINBALL_SAVE_LOCATION = "pinball-location"
    const val PINBALL_EVENT_START = "pinball-event-start"
    const val PILLAR_TOUCHED = "pinball-clicked"
    const val PINBALL_SCORE = "pinball-event-score"
    const val GET_PILLAR = "pinball-pillars"
    const val GET_REWARD = "pinball-reward"

    val wrongPillars = intArrayOf(
        config.Scenery.PINBALL_POST_15001,
        config.Scenery.PINBALL_POST_15003,
        config.Scenery.PINBALL_POST_15005,
        config.Scenery.PINBALL_POST_15007,
        config.Scenery.PINBALL_POST_15009,
    )
    val correctPillars = intArrayOf(
        config.Scenery.PINBALL_POST_15000,
        config.Scenery.PINBALL_POST_15002,
        config.Scenery.PINBALL_POST_15004,
        config.Scenery.PINBALL_POST_15006,
        config.Scenery.PINBALL_POST_15008
    )

    const val CAVE_EXIT = config.Scenery.CAVE_EXIT_15010
    val oldMan = NPC(NPCs.MYSTERIOUS_OLD_MAN_410, Location.create(1971, 5046, 0))
    val PINBALL_LOCATION = Location.create(1972, 5046, 0)

    fun teleport(player: Player){
        setAttribute(player, PINBALL_SAVE_LOCATION, player.location)
        registerLogoutListener(player, PINBALL_LOGOUT){ p ->
            p.location = getAttribute(p, PINBALL_SAVE_LOCATION, player.location)
        }

        oldMan.init()
        oldMan.properties.teleportLocation = oldMan.properties.spawnLocation

        AntiMacro.terminateEventNpc(player)
    }

    fun cleanup(player: Player){
        player.properties.teleportLocation = getAttribute(player, PINBALL_SAVE_LOCATION, null)
        clearLogoutListener(player, PINBALL_LOGOUT)
        removeAttributes(player, PINBALL_LOGOUT, PINBALL_SAVE_LOCATION, PINBALL_EVENT_START, PINBALL_SCORE, GET_PILLAR, PILLAR_TOUCHED, GET_REWARD)
        player.interfaceManager.restoreTabs()
        player.interfaceManager.close(Component(Components.PINBALL_INTERFACE_263))
        PacketRepository.send(MinimapState::class.java, MinimapStateContext(player, 1))
    }

    fun handlePinballEvent(player: Player): Boolean{
        if (inBorders(player, 1965, 5038, 1979, 5048)){
            openDialogue(player, MOMpinballDialogue())
        }
        return true
    }

    fun getPillars(player: Player){
        if(getAttribute(player, GET_PILLAR, 0) == 0){
           replaceScenery(Scenery(15001, Location(1967, 5046, 0)), 15000, -1, Location(1967, 5046, 0))
           setAttribute(player, PILLAR_TOUCHED, 0)
        }
        if(getAttribute(player, GET_PILLAR, 0) == 1){
           replaceScenery(Scenery(15003, Location(1969, 5049, 0)), 15002, -1, Location(1969, 5049, 0))
           setAttribute(player, PILLAR_TOUCHED, 1)
        }
        if(getAttribute(player, GET_PILLAR, 0) == 2){
           replaceScenery(Scenery(15005, Location(1972, 5050, 0)), 15004, -1, Location(1972, 5050, 0))
           setAttribute(player, PILLAR_TOUCHED, 2)
        }
        if(getAttribute(player, GET_PILLAR, 0) == 3){
           replaceScenery(Scenery(15007, Location(1975, 5049, 0)), 15006, -1, Location(1975, 5049, 0))
           setAttribute(player, PILLAR_TOUCHED, 3)
        }
        if(getAttribute(player, GET_PILLAR, 0) == 4){
           replaceScenery(Scenery(15009, Location(1977, 5046, 0)), 15008, -1, Location(1977, 5046, 0))
           setAttribute(player, PILLAR_TOUCHED, 4)
        }
        if(getAttribute(player, GET_PILLAR, 0) == 5){
           replaceScenery(Scenery(15007, Location(1967, 5046, 0)), 15006, -1, Location(1967, 5046, 0))
           setAttribute(player, PILLAR_TOUCHED, 5)
        }
    }

    fun replacePillars(player: Player){
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 0){
           replaceScenery(Scenery(15000, Location(1967, 5046, 0)), 15001, -1, Location(1967, 5046, 0))
        }
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 1){
           replaceScenery(Scenery(15002, Location(1969, 5049, 0)), 15003, -1, Location(1969, 5049, 0))
        }
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 2){
           replaceScenery(Scenery(15004, Location(1972, 5050, 0)), 15005, -1, Location(1972, 5050, 0))
        }
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 3){
           replaceScenery(Scenery(15006, Location(1975, 5049, 0)), 15007, -1, Location(1975, 5049, 0))
        }
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 4){
           replaceScenery(Scenery(15008, Location(1977, 5046, 0)), 15009, -1, Location(1977, 5046, 0))
        }
        if(getAttribute(player, PILLAR_TOUCHED, 0) == 5){
           replaceScenery(Scenery(15006, Location(1967, 5046, 0)), 15007, -1, Location(1967, 5046, 0))
        }
    }

    fun reward(player: Player){
        val randomReward = getAttribute(player,GET_REWARD,RandomFunction.getRandom(3))
        when(randomReward){
            1 -> addItemOrDrop(player, Items.SAPPHIRE_1608, 5)
            2 -> addItemOrDrop(player, Items.EMERALD_1606, 5)
            3 -> addItemOrDrop(player, Items.RUBY_1604, 5)
            4 -> addItemOrDrop(player, Items.DIAMOND_1602, 2)
        }
    }
}


