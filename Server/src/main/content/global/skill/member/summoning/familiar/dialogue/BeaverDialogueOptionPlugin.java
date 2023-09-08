package content.global.skill.member.summoning.familiar.dialogue;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles the beaver options.
 */
@Initializable
public final class BeaverDialogueOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(6808).getHandlers().put("option:interact", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		player.getDialogueInterpreter().open(6808, node.asNpc());
		return true;
	}
}
