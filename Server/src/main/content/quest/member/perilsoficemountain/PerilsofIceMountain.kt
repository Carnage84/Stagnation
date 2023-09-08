package content.quest.member.perilsoficemountain

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Perils of Ice Mountain quest.
 */
@Initializable
class PerilsofIceMountain : Quest("Perils of Ice Mountain", 151, 150, 1,Vars.VARBIT_QUEST_PERILS_OF_ICE_MOUNTAIN_4684,0,1,150) {
    companion object {
        const val PerilsofIceMountain = "Perils of Ice Mountain"
    }

    override fun drawJournal(player: Player, stage: Int) {
        super.drawJournal(player, stage)

        var ln = 11

        if (stage == 100) {
            ln++
            line(player, "!!QUEST COMPLETE!??", ln++, true)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.ARMADYL_PENDANT_87, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "500 Farming XP", ln++)
        drawReward(player, "500 Hunter XP", ln++)
        drawReward(player, "500 Construction XP", ln++)
        drawReward(player, "500 Thieving XP", ln++)
        rewardXP(player, Skills.FARMING, 500.0)
        rewardXP(player, Skills.HUNTER, 500.0)
        rewardXP(player, Skills.CONSTRUCTION, 500.0)
        rewardXP(player, Skills.THIEVING, 500.0)
        setVarbit(player, Vars.VARBIT_QUEST_PERILS_OF_ICE_MOUNTAIN_4684, 150, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//Knowledge of Ice Mountain, granting 500 experience in Farming, Hunter, Construction and Thieving
//Permission to use the power station ladder (allows you to quickly access the mine carts and Nurmof)
//Brother Bordiss will attach sigils to blessed spirit shields for a price.
//2 Treasure Hunter keys (Ironman accounts will not receive these)
//Music unlocked
//Icy Trouble Ahead (Unlocked during the third cutscene)
//Icy a Worried Gnome (Unlocked after the second cutscene)