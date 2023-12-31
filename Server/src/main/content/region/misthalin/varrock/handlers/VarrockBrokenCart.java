package content.region.misthalin.varrock.handlers;

import core.cache.def.impl.SceneryDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Listener for Varrock broken cart.
 */
@Initializable
public class VarrockBrokenCart extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(70099, "You search the cart but are surprised to find very little there. It's a", "little odd for a travelling trader not to have anything to trade.");
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		SceneryDefinition.forId(23055).getHandlers().put("option:search", this);
		return this;
	}

}
