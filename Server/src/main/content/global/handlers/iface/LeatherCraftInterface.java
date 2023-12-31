package content.global.handlers.iface;

import content.global.skill.free.crafting.armour.LeatherCrafting;
import content.global.skill.free.crafting.armour.SoftCraftPulse;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Initializable;
import core.plugin.Plugin;
import kotlin.Unit;

import static core.api.ContentAPIKt.sendInputDialogue;
import static core.api.ContentAPIKt.submitIndividualPulse;

/**
 * Represents the leather crafting interface.
 */
@Initializable
public final class LeatherCraftInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(154, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		int amount = 0;
		final LeatherCrafting.SoftLeather soft = LeatherCrafting.SoftLeather.forButton(button);
		if (soft == null) {
			return true;
		}
		switch (opcode) {
		case 155:
			amount = 1;
			break;
		case 196:
			amount = 5;
			break;
		case 124:
			amount = player.getInventory().getAmount(new Item(LeatherCrafting.LEATHER));
			break;
		case 199:
			sendInputDialogue(player, true, "Enter the amount:", (value) -> {
				submitIndividualPulse(player, new SoftCraftPulse(player, new Item(LeatherCrafting.LEATHER), soft, (int) value));
				return Unit.INSTANCE;
			});
			return true;
		}
		player.getPulseManager().run(new SoftCraftPulse(player, null, soft, amount));
		return true;
	}
}
