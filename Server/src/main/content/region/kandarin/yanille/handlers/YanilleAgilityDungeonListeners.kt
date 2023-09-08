package content.region.kandarin.yanille.handlers

import config.Items
import content.global.skill.member.agility.AgilityHandler
import core.api.*
import core.game.global.action.ClimbActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.node.scenery.Scenery
import core.game.node.scenery.SceneryBuilder
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Direction
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBuilder
import core.game.world.update.flag.context.Animation
import core.tools.RandomFunction

/**
 * Represents the Yanille agility dungeon listener.
 */
class YanilleAgilityDungeonListeners : InteractionListener {

    // The herb items.
    val SINISTER_CHEST_HERBS = arrayOf(Item(205, 2), Item(207, 3), Item(209), Item(211), Item(213), Item(219))
    override fun defineListeners() {
        ZoneBuilder.configure(YanilleAgilityDungeon());
        on(1728, IntType.SCENERY, "climb-down") { player, _ ->
            teleport(player,Location(2620, 9565, 0))
            return@on true
        }
        on(1729, IntType.SCENERY, "climb-up") { player, _ ->
            teleport(player,Location(2620, 9496, 0))
            return@on true
        }
        on(2316, IntType.SCENERY, "climb-up") { player, _ ->
            teleport(player,Location.create(2569, 9525, 0))
            return@on true
        }
        on(intArrayOf(2318, 2317), IntType.SCENERY, "climb-up", "climb-down") { player, target ->
            if (getStatLevel(player,Skills.AGILITY) < 67) {
                sendDialogue(player,"You need an Agility level of at least 67 in order to do this.")
                return@on true
            }
            val loc = if (target.id == 2317) { Location(2615, 9503, 0) } else { Location(2617, 9572, 0) }
            ClimbActionHandler.climb(player, if(target.id == 2317) { ClimbActionHandler.CLIMB_UP } else { ClimbActionHandler.CLIMB_DOWN }, loc)
            sendMessage(player,"You climb " + if(target.id == 2317) { "up" } else { "down" } + " the pile of rubble...")
            player.skills.addExperience(Skills.AGILITY, 5.5, true)
            return@on true
        }
        on(intArrayOf(35969, 2303), IntType.SCENERY, "walk-across") { player, target ->
            handleBalancingLedge(player, target.asScenery())
            return@on true
        }
        on(377, IntType.SCENERY, "open") { player, target ->
            if (!inInventory(player, Items.SINISTER_KEY_993, 1)) {
                sendMessage(player,"The chest is locked.")
            } else {
                if (freeSlots(player) == 0) {
                    sendMessage(player,"You don't have enough inventory space.");
                    return@on true
                }
                lock(player,1)
                if(removeItem(player, Items.SINISTER_KEY_993)) {
                    sendMessage(player,"You unlock the chest with your key... A foul gas seeps from the chest");
                    applyPoison(player, player, 2)
                    for(item in SINISTER_CHEST_HERBS) {
                        addItemOrDrop(player, item.id, item.amount)
                    }
                    SceneryBuilder.replace(target.asScenery(), target.asScenery().transform(target.id + 1), 5);
                }
            }
            return@on true
        }
        on(378, IntType.SCENERY, "search") { player, _ ->
            sendMessage(player,"The chest is empty.")
            return@on true
        }
        on(378, IntType.SCENERY, "shut") { player, _ ->
            return@on true
        }

    }

	/**
	 * Handles the balancing ledge.
	 * @param player the player.
	 * @param object the object.
	 */
	fun handleBalancingLedge(player: Player, scenery: Scenery) {
		if (getStatLevel(player,Skills.AGILITY) < 40) {
			sendDialogue(player,"You need an Agility level of at least 40 in order to do this.");
			return
		}
		val dir = Direction.getLogicalDirection(player.location, scenery.location)
		val diff = if(player.location.y == 9512) { 0 } else { 1 }
		var end = scenery.location;
		var xp = 0.0;
		if (AgilityHandler.hasFailed(player, 40, 0.01)) {
			lock(player, 3)
			GameWorld.Pulser.submit(object : Pulse(2, player) {
                override fun pulse(): Boolean {
					AgilityHandler.fail(player, 1, Location(2572, 9570, 0), Animation.create(761 - diff), RandomFunction.random(1, 3), "You lost your balance!")
					return true
				}
			})
		} else {
			xp = 22.5;
			end = scenery.location.transform(dir.stepX * 7, dir.stepY * 7, 0);
		}
		AgilityHandler.walk(player, -1, player.location, end, Animation.create(157 - diff), xp, null);
	}

}
