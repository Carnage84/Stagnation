package content.quest.member.forgettabletale

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Forgettable Tale of a Drunken Dwarf quest.
 */
@Initializable
class ForgettableTale : Quest("Forgettable Tale of a Drunken Dwarf", 63, 62, 2,Vars.VARBIT_QUEST_FORGETTABLE_TALE_OF_A_DRUNKEN_DWARF_822,0,1,140) {
    companion object {
        const val ForgettableTale = "Forgettable Tale of a Drunken Dwarf"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.DWARVEN_STOUTM_5747, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "5,000 Cooking XP", ln++)
        drawReward(player, "5,000 Farming XP", ln++)
        rewardXP(player, Skills.COOKING, 5000.0)
        rewardXP(player, Skills.FARMING, 5000.0)
        addItemOrDrop(player, Items.DWARVEN_STOUTM_5748, 2)
        setVarbit(player, Vars.VARBIT_QUEST_FORGETTABLE_TALE_OF_A_DRUNKEN_DWARF_822, 140, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 Quest points
//Cooking 5,000 Cooking experience
//Farming 5,000 Farming experience
//2 Dwarven stout (m)
