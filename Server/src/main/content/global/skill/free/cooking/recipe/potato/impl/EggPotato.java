package content.global.skill.free.cooking.recipe.potato.impl;

import content.global.skill.free.cooking.recipe.potato.PotatoRecipe;
import core.game.node.item.Item;

/**
 * Represents the egg potato recipe. This recipe consists of mixing a egg and a
 * tomato.
 */
public class EggPotato extends PotatoRecipe {

	/**
	 * Represents the egg potato.
	 */
	private static final Item EGG_POTATO = new Item(7056);

	/**
	 * Represents the topping item.
	 */
	private static final Item TOPPING = new Item(7064);

	@Override
	public Item getProduct() {
		return EGG_POTATO;
	}

	@Override
	public Item[] getIngredients() {
		return new Item[] { TOPPING };
	}

	@Override
	public boolean isTopping() {
		return true;
	}

	@Override
	public int getLevel() {
		return 51;
	}

	@Override
	public double getExperience() {
		return 10;
	}

}
