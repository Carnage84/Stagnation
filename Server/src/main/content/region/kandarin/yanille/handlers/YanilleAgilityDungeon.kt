package content.region.kandarin.yanille.handlers

import core.game.world.map.zone.MapZone
import core.game.world.map.zone.ZoneBorders

/**
 * Handles the Yanille agility dungeon.
 */
public class YanilleAgilityDungeon : MapZone("Yanille agility", true) {
    override fun configure() {
        register(ZoneBorders(2544, 9481, 2631, 9587))
    }
}