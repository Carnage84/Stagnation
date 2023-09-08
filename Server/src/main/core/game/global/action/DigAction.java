package core.game.global.action;

import core.game.node.entity.player.Player;

/**
 * Handles a digging reward.
 */
public interface DigAction {

	/**
	 * Runs the digging reward.
	 * @param player The player.
	 */
	void run(Player player);

}