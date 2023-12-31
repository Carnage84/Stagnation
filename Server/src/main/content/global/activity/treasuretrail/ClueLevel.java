package content.global.activity.treasuretrail;

import config.Items;
import core.api.IfaceSettingsBuilder;
import core.game.component.Component;
import core.game.container.access.InterfaceContainer;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.config.ClueRewardParser;
import core.game.world.GameWorld;
import core.tools.RandomFunction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static core.api.ContentAPIKt.addItemOrDrop;
import static core.api.ContentAPIKt.sendMessage;
import static core.tools.GlobalsKt.DARK_RED;

/**
 * A clue scroll level.
 */
public enum ClueLevel {
	EASY(Items.CASKET_2714, 1, 5),
	MEDIUM(Items.CASKET_2717, 1, 6),
	HARD(Items.CASKET_2720, 1, 8),
	UNKNOWN(0, 0, 0)
	;

	public int casketId, minSteps, maxSteps;

	private ClueLevel(int casketId, int minSteps, int maxSteps) {
		this.casketId = casketId;
		this.minSteps = minSteps;
		this.maxSteps = maxSteps;
	}

	private static ClueLevel getLevelForCasket(Item casket) {
		switch (casket.getId()) {
			case Items.CASKET_2714:
				return ClueLevel.EASY;
			case Items.CASKET_2717:
				return ClueLevel.MEDIUM;
			case Items.CASKET_2720:
				return ClueLevel.HARD;
		}

		return ClueLevel.UNKNOWN;
	}

	public static void open(Player player, Item casket) {
		if (casket == null || !player.getInventory().containsItem(casket)) {
			return;
		}

		TreasureTrailManager playerTrails = TreasureTrailManager.getInstance(player);

		boolean trailCompleted = playerTrails.isCompleted();
		boolean isDevMode = GameWorld.getSettings().isDevMode();

		ClueLevel clueLevel = getLevelForCasket(casket);

		if (trailCompleted || isDevMode) {
			List<Item> rewards = rollLoot(player, clueLevel);

			player.getInterfaceManager().open(new Component(364));

			if (player.getInventory().remove(casket)) {
				long rewardValue = 0L;

				for (Item reward : rewards) {
					addItemOrDrop(player, reward.getId(), reward.getAmount());
					rewardValue += reward.getValue();
				}

				playerTrails.incrementClues(clueLevel);
				playerTrails.clearTrail();

				sendMessage(player,"Well done, you've completed the Treasure Trail!");
				sendMessage(player, getChatColor(clueLevel) + "You have completed " + playerTrails.getCompletedClues(clueLevel) + " " + clueLevel.getName().toLowerCase() + " clues.");
				sendMessage(player, DARK_RED+"Your clue is worth approximately " + NumberFormat.getInstance().format(rewardValue) + " coins!");

				int clueIfaceSettings = new IfaceSettingsBuilder().enableAllOptions().build();
				player.getPacketDispatch().sendIfaceSettings(clueIfaceSettings, 4, 364, 0, 6);
				InterfaceContainer.generateItems(player, rewards.toArray(new Item[] {}), new String[] {""}, 364, 4, 3, 3);
			}
			return;
		}

		Item newClue = ClueScrollPlugin.getClue(clueLevel);

		if (casket != null && player.getInventory().remove(casket, casket.getSlot(), true)) {
			player.getInventory().replace(newClue, casket.getSlot());
		} else {
			player.getInventory().add(newClue);
		}

		playerTrails.setClueId(newClue.getId());
		player.getDialogueInterpreter().sendItemMessage(newClue, "You've found another clue!");
	}

	public static List<Item> rollLoot(Player player, ClueLevel level) {
		ArrayList<Item> loot = new ArrayList();
		int itemCount = RandomFunction.random(1,6);

		if (level == ClueLevel.HARD) {
			itemCount = Math.max(itemCount, RandomFunction.random(4,6));
		}

		for (; itemCount > 0; itemCount--) {
			switch (level) {
				case EASY:
					loot.addAll(ClueRewardParser.getEasyTable().roll(player));
					break;
				case MEDIUM:
					loot.addAll(ClueRewardParser.getMedTable().roll(player));
					break;
				case HARD:
					loot.addAll(ClueRewardParser.getHardTable().roll(player));
					break;
			}
		}

		if (level == ClueLevel.HARD && RandomFunction.random(100) == 50) {
			loot.addAll(ClueRewardParser.getRareTable().roll(player));
		}

		return loot;
	}

	/**
	 * Gets the Chat color to send on completed clues.
	 * @param level The clue level.
	 * @return the chat color.
	 */
	public static String getChatColor(ClueLevel level) {
		if (level == ClueLevel.HARD) {
			return "<col=ff1a1a>";
		}
		if (level == ClueLevel.MEDIUM) {
			return "<col=b38f00>";
		}
		return "<col=00e673>";
	}
	/**
	 * Gets the maximum length.
	 *
	 * @return the length.
	 */
	public int getMaximumLength() {
		return maxSteps;
	}

	/**
	 * Gets the minimum length.
	 *
	 * @return the length.
	 */
	public int getMinimumLength() {
		return minSteps;
	}

	/**
	 * Gets the bcasket.
	 *
	 * @return the casket
	 */
	public Item getCasket() {
		return new Item(casketId);
	}

	/**
	 * Gets the name of the clue level.
	 * @return the name.
	 */
	public String getName() {
		return toString();
	}
}
