package content.global.skill.free.cooking.recipe.pie.impl;

import content.global.skill.free.cooking.recipe.pie.PieRecipe;
import core.game.node.item.Item;

/**
 * Represents the apple pie recipe. This recipe consists of cooking apples and a
 * pie shell.
 */
public class ApplePie extends PieRecipe {

	/**
	 * Represents the uncooked redberry pie.
	 */
	private static final Item UNCOOKED_PIE = new Item(2317);

	/**
	 * Represents the cooking apple item.
	 */
	private static final Item COOKING_APPLE = new Item(1955);

	@Override
	public Item getProduct() {
		return UNCOOKED_PIE;
	}

	@Override
	public Item[] getIngredients() {
		return new Item[] { COOKING_APPLE };
	}

}
