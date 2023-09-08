package content.global.handlers.iface;

import content.region.island.tutorialisland.handlers.iface.CharacterDesign;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents a component plugin to handle the character design interface.
 */
@Initializable
public final class CharacterDesignInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(771, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (opcode) {
		case 155:
			CharacterDesign.handleButtons(player, button);
			break;
		}
		return true;
	}
}
