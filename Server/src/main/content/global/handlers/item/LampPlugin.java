package content.global.handlers.item;

import content.data.Lamps;
import core.game.component.Component;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.node.item.Item;
import core.plugin.Initializable;
import core.plugin.Plugin;

import static core.api.ContentAPIKt.removeAttribute;
import static core.api.ContentAPIKt.setAttribute;
import static core.tools.GlobalsKt.BLUE;

/**
 * Represents the plugin used for an experience lamp.
 */
@Initializable
public final class LampPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Lamps lamp : Lamps.values()) {
			lamp.getItem().getDefinition().getHandlers().put("option:rub", this);
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		setAttribute(player, "lamp", node);
		setAttribute(player, "caller",this);
		player.getInterfaceManager().open(new Component(134).setCloseEvent((player1, c) -> {
			player.getInterfaceManager().openDefaultTabs();
			removeAttribute(player, "lamp");
			player.unlock();
			return true;
		}));
		player.getInterfaceManager().removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);
		return true;
	}
	@Override
	public void handleSelectionCallback(int skill, Player player){
		Lamps lamp = Lamps.forItem(player.getAttribute("lamp",new Item(2528)));
		if(player.getSkills().getStaticLevel(skill) < lamp.getLevelRequirement()){
			player.sendMessage("You need at least " + lamp.getLevelRequirement()  + " " + Skills.SKILL_NAME[skill]  + " to do this.");
			return;
		} else {
			if(player.getInventory().remove((Item) player.getAttribute("lamp"))) {
				if (lamp == Lamps.GENIE_LAMP) {
					player.getSkills().addExperience(skill, player.getSkills().getStaticLevel(skill) * 10);
				} else {
					player.getSkills().addExperience(skill, lamp.getExp());
					player.getDialogueInterpreter().sendPlainMessage(false, BLUE + "Your wish has been granted!", "You have been awarded " + lamp.getExp() + " " + Skills.SKILL_NAME[skill] + " experience!");
				}
			}
		}
	}

	@Override
	public boolean isWalk() {
		return false;
	}

}
