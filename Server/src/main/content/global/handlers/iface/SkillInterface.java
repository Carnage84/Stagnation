package content.global.handlers.iface;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

import static core.api.ContentAPIKt.setVarbit;

/**
 * Represents the plugin used for the skilling interface.
 */
@Initializable
public final class SkillInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(499, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
                setVarbit(player, 3288, player.getAttribute("skillMenu", -1));
                setVarbit(player, 3289, button - 10);
		return true;
	}
}
