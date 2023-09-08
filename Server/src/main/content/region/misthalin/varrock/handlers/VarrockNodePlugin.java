package content.region.misthalin.varrock.handlers;

import core.cache.def.impl.SceneryDefinition;
import core.game.component.Component;
import core.game.dialogue.FacialExpression;
import core.game.global.action.ClimbActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.scenery.Scenery;
import core.game.node.scenery.SceneryBuilder;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle node interactions in Varrock.
 */
@Initializable
public final class VarrockNodePlugin extends OptionHandler {

	/**
	 * Represents the bronze axe item.
	 */
	private static final Item BRONZE_AXE = new Item(1351);

	/**
	 * Represents the spade item.
	 */
	private static final Item SPADE = new Item(952);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		SceneryDefinition.forId(24357).getHandlers().put("option:climb-up", this);
		SceneryDefinition.forId(24359).getHandlers().put("option:climb-down", this);
		SceneryDefinition.forId(5581).getHandlers().put("option:take-axe", this);
		SceneryDefinition.forId(36974).getHandlers().put("option:take-axe", this);
		SceneryDefinition.forId(24427).getHandlers().put("option:walk-up", this);
		SceneryDefinition.forId(24428).getHandlers().put("option:walk-down", this);
		SceneryDefinition.forId(1749).getHandlers().put("option:climb-down", this);
		SceneryDefinition.forId(23636).getHandlers().put("option:read", this);
		SceneryDefinition.forId(9662).getHandlers().put("option:take", this);
		SceneryDefinition.forId(29534).getHandlers().put("option:enter", this);
		SceneryDefinition.forId(17985).getHandlers().put("option:climb-down", this);
		SceneryDefinition.forId(24366).getHandlers().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final int id = node instanceof Scenery ? ((Scenery) node).getId() : ((Item) node).getId();
		switch (id) {
			case 24366:
				ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(3237, 3459));
				return true;
			case 29534:
				player.getDialogueInterpreter().open(543543);
				return true;
			case 17985:
				ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_DOWN, new Location(3204, 9910), "You enter the murky sewers.");
				return true;
			case 28094:
				player.getDialogueInterpreter().sendDialogues(player, FacialExpression.THINKING, "I don't think I should go inside.");
				break;
			case 23636:
				player.getInterfaceManager().open(new Component(531));
				break;
			case 1749:
				if (player.getLocation().getZ() == 2 && player.getLocation().getDistance(new Location(3096, 3433, 2)) < 4) {
					ClimbActionHandler.climb(player, new Animation(827), Location.create(3097, 3432, 1));
					return true;
				} else if (player.getLocation().getZ() == 1 && player.getLocation().getDistance(new Location(3095, 3433, 1)) < 4) {
					ClimbActionHandler.climb(player, new Animation(827), Location.create(3096, 3432, 0));
					return true;
				}
				ClimbActionHandler.climbLadder(player, (Scenery) node, option);
				return true;
			case 5581:
			case 36974:
				if (!player.getInventory().add(BRONZE_AXE)) {
					player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
					return true;
				}
				SceneryBuilder.replace(((Scenery) node), ((Scenery) node).transform(5582), 5000);
				break;
			case 24357:
				if (player.getLocation().getDistance(Location.create(3188, 3358, 0)) < 3) {
					ClimbActionHandler.climb(player, new Animation(828), Location.create(3188, 3354, 1));
					return true;
				}
				if (((Scenery) node).getLocation().equals(new Location(3156, 3435, 0))) {
					ClimbActionHandler.climb(player, new Animation(828), Location.create(3155, 3435, 1));
					return true;
				}
				ClimbActionHandler.climbLadder(player, (Scenery) node, option);
				return true;

			case 24359:
				if (player.getLocation().getDistance(Location.create(3231, 3382, 1)) < 3) {
					ClimbActionHandler.climb(player, null, Location.create(3231, 3386, 0));
					return true;
				}
				ClimbActionHandler.climbLadder(player, (Scenery) node, option);
				return true;

			case 24427: //varrock museum stairs that lead upstairs
				if (player.getLocation().getDistance(Location.create(1758, 4959, 0)) < 3) {
					ClimbActionHandler.climb(player, new Animation(-1), Location.create(3258, 3452, 0));
					return true;
				}
				return true;

			case 24428: //varrock museum stairs that lead downstairs
				if (player.getLocation().getDistance(Location.create(3255, 3451, 0)) < 4) {
					ClimbActionHandler.climb(player, new Animation(-1), Location.create(1759, 4958, 0));
					return true;
				}
				return true;
			case 9662:
				if (!player.getInventory().hasSpaceFor(SPADE)) {
					player.getPacketDispatch().sendMessage("Not enough inventory space.");
					return true;
				}
				player.getInventory().add(SPADE);
				SceneryBuilder.replace((Scenery) node, ((Scenery) node).transform(0), 250);
				return true;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, final Node node) {
		return !(node instanceof Item);
	}

}
