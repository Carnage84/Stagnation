package content.region.misthalin.lumbridge.handlers

import core.api.MapArea
import core.game.world.map.zone.ZoneBorders
import core.game.world.map.zone.ZoneRestriction

/**
 * Represents the Tormented Demon Map Area.
 */
class TormentedDemonArea : MapArea {
    override fun defineAreaBorders() : Array<ZoneBorders> { return arrayOf (ZoneBorders.forRegion(10329)) }
    override fun getRestrictions() : Array<ZoneRestriction> { return arrayOf (ZoneRestriction.CANNON) }
}
