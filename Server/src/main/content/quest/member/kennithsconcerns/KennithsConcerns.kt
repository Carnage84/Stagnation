package content.quest.member.kennithsconcerns

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Kennith's Concerns quest.
 */
@Initializable
class KennithsConcerns : Quest("Kennith's Concerns", 149, 148, 1,Vars.VARBIT_QUEST_KENNITHS_CONCERNS_4505,0,1,100) {
    companion object {
        const val KennithsConcerns = "Kennith's Concerns"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.FISHING_EXPLOSIVE_6660, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "12,000 Mining XP", ln++)
        drawReward(player, "5,000 Agility XP", ln++)

        rewardXP(player, Skills.MINING, 12000.0)
        rewardXP(player, Skills.AGILITY, 5000.0)
        setVarbit(player, Vars.VARBIT_QUEST_KENNITHS_CONCERNS_4505, 100, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//12,000 Mining experience
//5,000 Agility experience
//Access to the mines under Witchaven
//Ability to mine and sell a volatile new ore rubium to Ezekial Lovecraft for 10 coins each
//Access to a repeatable mini-task
//Ability to make Super Fishing Explosive.
//2 Treasure Hunter keys (Ironman accounts will not receive these)