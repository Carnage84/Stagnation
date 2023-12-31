package content.region.asgarnia.goblinvillage.handlers;

import core.cache.def.impl.SceneryDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.RegionManager;
import core.plugin.Initializable;
import core.plugin.Plugin;

import java.util.List;

/**
 * Represents the goblin village population plugin.
 */
@Initializable
public class GoblinVillagePopulationPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		SceneryDefinition.forId(31301).getHandlers().put("option:read", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		int population = 2;
		final List<NPC> npcs = RegionManager.getLocalNpcs(player);
		for (NPC n : npcs) {
			if (n.getName().equals("Goblin")) {
				population++;
				player.getDialogueInterpreter().sendPlainMessage(false, "Welcome to Goblin Village.", "Current population: " + population);
			}
		}
		return true;
	}

}
