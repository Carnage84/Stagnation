package content.region.misthalin.varrock.handlers;

import config.Items;
import core.cache.def.impl.SceneryDefinition;
import core.game.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.scenery.Scenery;
import core.game.world.map.Location;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle the brass key door plugin.
 */
@Initializable
public final class BrassKeyDoorPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (player.getInventory().contains(Items.BRASS_KEY_983, 1)) {
			DoorActionHandler.handleAutowalkDoor(player, (Scenery) node);
		} else {
			player.getPacketDispatch().sendMessage("This door is locked.");
			return true;
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		SceneryDefinition.forId(1804).getHandlers().put("option:open", this);
		return this;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return DoorActionHandler.getDestination(((Player) node), ((Scenery) n));
	}

}
