package content.data.skill;

import config.Items;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.world.update.flag.context.Animation;

/**
 * Represents a skilling tool (such as knife, axe, needle, ...)
 */
public enum SkillingTool {

	/**
	 * Represents a woodcutting tools.
	 */
	BRONZE_AXE(Items.BRONZE_AXE_1351, 1, 0.05D, new Animation(879)),
	IRON_AXE(Items.IRON_AXE_1349, 1, 0.1D, new Animation(877)),
	STEEL_AXE(Items.STEEL_AXE_1353, 6, 0.2D, new Animation(875)),
	BLACK_AXE(Items.BLACK_AXE_1361, 6, 0.25D, new Animation(873)),
	MITHRIL_AXE(Items.MITHRIL_AXE_1355, 21, 0.30D, new Animation(871)),
	ADAMANT_AXE(Items.ADAMANT_AXE_1357, 31, 0.45D, new Animation(869)),
	RUNE_AXE(Items.RUNE_AXE_1359, 41, 0.65D, new Animation(867)),
	DRAGON_AXE(Items.DRAGON_AXE_6739, 61, 0.85D, new Animation(2846)),
	INFERNO_ADZE(Items.INFERNO_ADZE_13661, 61, 0.85D, new Animation(10251)),
	HATCHET_CLASS1(Items.HATCHET_CLASS_1_14132, 1, 0.1, new Animation(10603)),
	HATCHET_CLASS2(Items.HATCHET_CLASS_2_14134, 20, 0.3, new Animation(10604)),
	HATCHET_CLASS3(Items.HATCHET_CLASS_3_14136, 40, 0.65, new Animation(10605)),
	HATCHET_CLASS4(Items.HATCHET_CLASS_4_14138, 60, 0.85, new Animation(10606)),
	HATCHET_CLASS5(Items.HATCHET_CLASS_5_14140, 80, 1.0, new Animation(10607)),

	/**
	 * Represents a mining tools.
	 */
	BRONZE_PICKAXE(Items.BRONZE_PICKAXE_1265, 1, 0.05D, new Animation(625)),
	IRON_PICKAXE(Items.IRON_PICKAXE_1267, 1, 0.1D, new Animation(626)),
	STEEL_PICKAXE(Items.STEEL_PICKAXE_1269, 6, 0.2D, new Animation(627)),
	MITHRIL_PICKAXE(Items.MITHRIL_PICKAXE_1273, 21, 0.30D, new Animation(629)),
	ADAMANT_PICKAXE(Items.ADAMANT_PICKAXE_1271, 31, 0.45D, new Animation(628)),
	RUNE_PICKAXE(Items.RUNE_PICKAXE_1275, 41, 0.65D, new Animation(624)),
	INFERNO_ADZE2(Items.INFERNO_ADZE_13661, 61, 1.0D, new Animation(10222)),
    PICKAXE_CLASS1(Items.PICKAXE_CLASS_1_14122, 1, 0.1, new Animation(10608)),
    PICKAXE_CLASS2(Items.PICKAXE_CLASS_2_14124, 20, 0.3, new Animation(10609)),
    PICKAXE_CLASS3(Items.PICKAXE_CLASS_3_14126, 40, 0.65, new Animation(10610)),
    PICKAXE_CLASS4(Items.PICKAXE_CLASS_4_14128, 60, 0.85, new Animation(10611)),
    PICKAXE_CLASS5(Items.PICKAXE_CLASS_5_14130, 80, 1.0, new Animation(10612)),

	/**
	 * Represents a fishing tools.
	 */
	HARPOON_CLASS1(Items.HARPOON_CLASS_1_14142, 1, 0.1, new Animation(10613)),
    HARPOON_CLASS2(Items.HARPOON_CLASS_2_14144, 20, 0.3, new Animation(10614)),
    HARPOON_CLASS3(Items.HARPOON_CLASS_3_14146, 40, 0.65, new Animation(10615)),
    HARPOON_CLASS4(Items.HARPOON_CLASS_4_14148, 60, 0.85, new Animation(10616)),
    HARPOON_CLASS5(Items.HARPOON_CLASS_5_14150, 80, 1.0, new Animation(10617)),

	/**
	 * Represents a hunter tools.
	 */
    BUTTERFLY_NET_CLASS1(Items.BUTTERFLY_NET_CLASS_1_14152, 1, 0.1, new Animation(10618)),
    BUTTERFLY_NET_CLASS2(Items.BUTTERFLY_NET_CLASS_2_14154, 20, 0.3, new Animation(10619)),
    BUTTERFLY_NET_CLASS3(Items.BUTTERFLY_NET_CLASS_3_14156, 40, 0.65, new Animation(10620)),
    BUTTERFLY_NET_CLASS4(Items.BUTTERFLY_NET_CLASS_4_14158, 60, 0.85, new Animation(10621)),
    BUTTERFLY_NET_CLASS5(Items.BUTTERFLY_NET_CLASS_5_14160, 80, 1.0, new Animation(10622));

	/**
	 * The tool id.
	 */
	private final int id;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The ratio.
	 */
	private final double ratio;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * Constructs a new {@code SkillingTool} {@code Object}.
	 * @param id The tool item id.
	 * @param level The level required to use this.
	 * @param ratio The ratio.
	 * @param animation The animation.
	 */
	private SkillingTool(int id, int level, double ratio, Animation animation) {
		this.id = id;
		this.level = level;
		this.ratio = ratio;
		this.animation = animation;
	}

	/**
	 * Gets the tool by the item id.
	 * @param itemId The item id.
	 * @return The skilling tool, or {@code null} if the tool wasn't found.
	 */
	public static SkillingTool forId(int itemId) {
		for (SkillingTool tool : SkillingTool.values()) {
			if (tool.id == itemId) {
				return tool;
			}
		}
		return null;
	}

	/**
	 * Gets the hatchet used by the player.
	 * @param player The player.
	 * @return The hatchet.
	 */
	public static SkillingTool getHatchet(Player player) {
		SkillingTool tool = null;
        SkillingTool[] hatchetPriority = new SkillingTool[] {
            SkillingTool.HATCHET_CLASS5,
            SkillingTool.HATCHET_CLASS4,
            SkillingTool.DRAGON_AXE,
            SkillingTool.HATCHET_CLASS3,
            SkillingTool.RUNE_AXE,
            SkillingTool.ADAMANT_AXE,
            SkillingTool.HATCHET_CLASS2,
            SkillingTool.MITHRIL_AXE,
            SkillingTool.BLACK_AXE,
            SkillingTool.STEEL_AXE,
            SkillingTool.HATCHET_CLASS1,
            SkillingTool.IRON_AXE,
            SkillingTool.BRONZE_AXE
        };
        for(SkillingTool hatchet : hatchetPriority) {
            if (checkTool(player, Skills.WOODCUTTING, hatchet)) {
                tool = hatchet;
                break;
            }
        }
		if (checkTool(player, Skills.WOODCUTTING, SkillingTool.INFERNO_ADZE)) {
			if(player.getSkills().getLevel(Skills.FIREMAKING) >= 92) {
				tool = SkillingTool.INFERNO_ADZE;
			}
		}
		return tool;
	}

	/**
	 * Gets the pickaxe used by the player.
	 * @param player The player.
	 * @return The hatchet.
	 */
	public static SkillingTool getPickaxe(Player player) {
		SkillingTool tool = null;
        SkillingTool[] pickaxePriority = new SkillingTool[] {
            SkillingTool.PICKAXE_CLASS5,
            SkillingTool.PICKAXE_CLASS4,
            SkillingTool.RUNE_PICKAXE,
            SkillingTool.PICKAXE_CLASS3,
            SkillingTool.ADAMANT_PICKAXE,
            SkillingTool.PICKAXE_CLASS2,
            SkillingTool.MITHRIL_PICKAXE,
            SkillingTool.STEEL_PICKAXE,
            SkillingTool.PICKAXE_CLASS1,
            SkillingTool.IRON_PICKAXE,
            SkillingTool.BRONZE_PICKAXE,
        };
        for(SkillingTool pickaxe : pickaxePriority) {
            if (checkTool(player, Skills.MINING, pickaxe)) {
                tool = pickaxe;
                break;
            }
        }
		if (checkTool(player, Skills.MINING, SkillingTool.INFERNO_ADZE2)) {
			if (player.getSkills().getLevel(Skills.FIREMAKING) >= 92) {
				tool = SkillingTool.INFERNO_ADZE2;
			}
		} 
		return tool;
	}

	public static SkillingTool getHarpoon(Player player) {
		SkillingTool tool = null;
        SkillingTool[] harpoonPriority = new SkillingTool[] {
            SkillingTool.BUTTERFLY_NET_CLASS5,
            SkillingTool.BUTTERFLY_NET_CLASS4,
            SkillingTool.BUTTERFLY_NET_CLASS3,
            SkillingTool.BUTTERFLY_NET_CLASS2,
            SkillingTool.BUTTERFLY_NET_CLASS1,
        };
        for(SkillingTool harpoon : harpoonPriority) {
            if (checkTool(player, Skills.FISHING, harpoon)) {
                tool = harpoon;
                break;
            }
        }
		return tool;
	}

	public static SkillingTool getButterflyNet(Player player) {
		SkillingTool tool = null;
        SkillingTool[] butterflyNetPriority = new SkillingTool[] {
            SkillingTool.BUTTERFLY_NET_CLASS5,
            SkillingTool.BUTTERFLY_NET_CLASS4,
            SkillingTool.BUTTERFLY_NET_CLASS3,
            SkillingTool.BUTTERFLY_NET_CLASS2,
            SkillingTool.BUTTERFLY_NET_CLASS1,
        };
        for(SkillingTool butterflyNet : butterflyNetPriority) {
            if (checkTool(player, Skills.HUNTER, butterflyNet)) {
                tool = butterflyNet;
                break;
            }
        }
		return tool;
	}

    public static SkillingTool getToolForSkill(Player player, int skill) {
        switch(skill) {
            case Skills.MINING:
                return getPickaxe(player);
            case Skills.WOODCUTTING:
                return getHatchet(player);
            case Skills.FISHING:
                return getHarpoon(player);
            case Skills.HUNTER:
                return getButterflyNet(player);
            default:
                return null;
        }
    }

	/**
	 * Checks if the player has a tool and if he can use it.
	 * @param tool The tool.
	 * @return {@code True} if the tool is usable.
	 */
	public static boolean checkTool(Player player, int skillId, SkillingTool tool) {
		if (player.getSkills().getStaticLevel(skillId) < tool.getLevel()) {
			return false;
		}
		if (player.getEquipment().getNew(3).getId() == tool.getId()) {
			return true;
		}
		return player.getInventory().contains(tool.getId(), 1);
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the ratio.
	 * @return The ratio.
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}
}
