package content.region.kandarin.seers.handlers;

import core.cache.def.impl.SceneryDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.shops.Shops;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the buy crate option plugin for the seers village city.
 */
@Initializable
public final class BuyCrateOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		Shops.openId(player, 93);
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		SceneryDefinition.forId(6839).getHandlers().put("option:buy", this);
		return this;
	}

}
